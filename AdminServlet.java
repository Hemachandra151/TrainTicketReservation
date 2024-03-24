import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "insert":
                    insertTrain(request, response);
                    break;
                case "delete":
                    deleteTrain(request, response);
                    break;
                default:
                    response.sendRedirect("adminlog.jsp");
            }
        } else {
            response.sendRedirect("adminlog.jsp");
        }
    }

    private void insertTrain(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract train details from the request
        String trainId = request.getParameter("trainId");
        String trainName = request.getParameter("trainName");
        // Extract other train details as needed
        String date = request.getParameter("date");
        String sourceLoc = request.getParameter("sourceLoc");
        String destination = request.getParameter("destination");
        int seats = Integer.parseInt(request.getParameter("seats"));

        // Perform the database insertion
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs", "root", "");
                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO trains (trainid, trainname, day, source_loc, destination, seats) VALUES (?, ?, ?, ?, ?, ?)")) {

            ps.setString(1, trainId);
            ps.setString(2, trainName);
            ps.setString(3, date);
            ps.setString(4, sourceLoc);
            ps.setString(5, destination);
            ps.setInt(6, seats);

            ps.executeUpdate();

            response.sendRedirect("adminedit.jsp"); // Redirect back to admin page
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error inserting train data");
        }
    }

    private void deleteTrain(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Extract train ID from the request
        String trainId = request.getParameter("trainId");

        // Perform the database deletion
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs", "root", "");
                PreparedStatement ps = con.prepareStatement("DELETE FROM trains WHERE trainid = ?")) {

            ps.setString(1, trainId);
            ps.executeUpdate();

            response.sendRedirect("adminedit.jsp"); // Redirect back to admin page
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error deleting train data");
        }
    }
}
