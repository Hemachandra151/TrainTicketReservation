import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BookTrainServlet")
public class BookTrainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC database URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbs";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get parameters from the form
        String userId = request.getParameter("userId");
        String trainId = request.getParameter("trainId");
        int seatsToBook = Integer.parseInt(request.getParameter("seats"));

        // Authenticate the user using the password
        if (!authenticateUser(userId, request.getParameter("password"))) {
            response.sendRedirect("authentication-failed.jsp");
            return;
        }

        // Generate a unique 4-digit ticket number
        int ticketNumber = generateTicketNumber();

        // Check seat availability and book the train
        if (checkSeatAvailability(trainId, seatsToBook)) {
            bookTrain(userId, trainId, seatsToBook, ticketNumber);
            request.setAttribute("ticketNumber", ticketNumber);
            request.setAttribute("confirmationMsg", "Booking successful!");
            request.getRequestDispatcher("booking-confirmation.jsp").forward(request, response);
        } else {
            request.setAttribute("confirmationMsg", "Seats not available. Please choose a different number of seats.");
            request.getRequestDispatcher("booking-confirmation.jsp").forward(request, response);
        }
    }

    private boolean authenticateUser(String userId, String password) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE id = ? AND password = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, userId);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return resultSet.next(); // If the result set has any rows, the user is authenticated
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real-world scenario
            return false;
        }
    }

    private int generateTicketNumber() {
        // Generate a random 4-digit ticket number
        Random random = new Random();
        return 1000 + random.nextInt(9000);
    }

    private boolean checkSeatAvailability(String trainId, int seatsToBook) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            String sql = "SELECT seats FROM trains WHERE trainid = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, trainId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        int availableSeats = resultSet.getInt("seats");
                        return availableSeats >= seatsToBook;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real-world scenario
            return false;
        }
        return false;
    }

    private void bookTrain(String userId, String trainId, int seatsToBook, int ticketNumber) {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Update the seats in the trains table
            String updateSeatsSQL = "UPDATE trains SET seats = seats - ? WHERE trainid = ?";
            try (PreparedStatement updateSeatsStatement = connection.prepareStatement(updateSeatsSQL)) {
                updateSeatsStatement.setInt(1, seatsToBook);
                updateSeatsStatement.setString(2, trainId);
                updateSeatsStatement.executeUpdate();
            }

            // Insert booking details into the bookings table
            String insertBookingSQL = "INSERT INTO bookings (ticketno, trainid, userid, no_of_seats) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertBookingStatement = connection.prepareStatement(insertBookingSQL)) {
                insertBookingStatement.setInt(1, ticketNumber);
                insertBookingStatement.setString(2, trainId);
                insertBookingStatement.setString(3, userId);
                insertBookingStatement.setInt(4, seatsToBook);
                insertBookingStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real-world scenario
        }
    }
}
