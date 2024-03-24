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

@WebServlet("/SearchTrainServlet")
public class SearchTrainServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC database URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/dbs";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    

        // Get parameters from the form
        String sourceLoc = request.getParameter("sourceLoc");
        String destination = request.getParameter("destination");

        // Fetch train details from the database based on source location and destination
        String trainDetails = getTrainDetailsBySourceAndDestination(sourceLoc, destination);

        // Set the result in the request attribute
        request.setAttribute("trainDetails", trainDetails);

        // Forward the request to the JSP page
        RequestDispatcher dispatcher = request.getRequestDispatcher("searchTrain.jsp");
        dispatcher.forward(request, response);
    }

    // Fetch train details from the database based on source location and destination
    public String getTrainDetailsBySourceAndDestination(String sourceLoc, String destination) {
        StringBuilder result = new StringBuilder();

        try (Connection connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD)) {
            // Prepare SQL query
            String sql = "SELECT trainid, trainname, day, source_loc, destination, seats FROM trains WHERE source_loc = ? AND destination = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set the parameters
                preparedStatement.setString(1, sourceLoc);
                preparedStatement.setString(2, destination);

                // Execute the query
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    boolean firstRow = false;

                    while (resultSet.next()) {
                        if (firstRow) {
                            // Append the header row only for the first record
                            result.append("<tr>")
                                .append("<th>Train ID</th>")
                                .append("<th>Train Name</th>")
                                .append("<th>Date</th>")
                                .append("<th>Source Location</th>")
                                .append("<th>Destination</th>")
                                .append("<th>Seats</th>")
                                .append("</tr>");

                            firstRow = false;
                        }

                        int trainId = resultSet.getInt("trainid");
                        String trainName = resultSet.getString("trainname");
                        String date = resultSet.getDate("day").toString(); // Fetch date as string
                        String sourceLocation = resultSet.getString("source_loc");
                        String dest = resultSet.getString("destination");
                        int seats = resultSet.getInt("seats");

                        // Append the result to the StringBuilder in table format
                        result.append("<tr>")
                            .append("<td>").append(trainId).append("</td>")
                            .append("<td>").append(trainName).append("</td>")
                            .append("<td>").append(date).append("</td>")
                            .append("<td>").append(sourceLocation).append("</td>")
                            .append("<td>").append(dest).append("</td>")
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
