import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TrainDetailsServlet")
public class TrainDetailsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC database URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbs";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the date parameter from the form
        String date = request.getParameter("date");

        // Fetch train details from the database based on the date
        String trainDetails = getTrainDetailsByDate(date);

        // Set the result in the request attribute
        request.setAttribute("trainDetails", trainDetails);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("trainDetails.jsp");
        dispatcher.forward(request, response);
    }

    // Fetch train details from the database based on the date
    private String getTrainDetailsByDate(String date) {
        StringBuilder result = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Prepare SQL query
            String sql = "SELECT trainid, trainname, source_loc, destination, seats FROM trains WHERE day = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set the date parameter
                preparedStatement.setString(1, date);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                	boolean firstRow = false;
                    while (resultSet.next()) {
                    	if (firstRow) {
                            // Append the header row only for the first record
                            result.append("<tr>")
                                  .append("<th>Train ID</th>")
                                  .append("<th>Train Name</th>")
                                  .append("<th>Source Location</th>")
                                  .append("<th>Destination</th>")
                                  .append("<th>Seats</th>")
                                  .append("</tr>");

                            firstRow = false;
                        }
                        int trainId = resultSet.getInt("trainid");
                        String trainName = resultSet.getString("trainname");
                        String sourceLoc = resultSet.getString("source_loc");
                        String destination = resultSet.getString("destination");
                        int seats = resultSet.getInt("seats");

                        // Append the result to the StringBuilder in table format
                        result.append("<tr>")
                              .append("<td>").append(trainId).append("</td>")
                              .append("<td>").append(trainName).append("</td>")
                              .append("<td>").append(sourceLoc).append("</td>")
                              .append("<td>").append(destination).append("</td>")
                              .append("<td>").append(seats).append("</td>")
                              .append("</tr>");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately in a real-world scenario
        }

        return result.toString();
    }
}
