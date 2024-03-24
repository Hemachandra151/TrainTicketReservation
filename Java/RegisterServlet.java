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

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	 String fname = request.getParameter("fname");
         String lname = request.getParameter("lname");
         String phone = request.getParameter("phone");
         String sex = request.getParameter("gender");
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         String age = request.getParameter("age");


        if (registerUser(fname, lname, username, password, phone, age, sex)) {
            response.sendRedirect("registration_success.jsp");
        } else {
            response.sendRedirect("registration_failure.jsp");
        }
    }

    private boolean registerUser(String fname, String lname, String username, String password, String phone, String age, String sex) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dbs", "root",
                    "");

            String query = "INSERT INTO users (fname, lname, username, password, phone, age, gender) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, lname);
            ps.setString(3, username);
            ps.setString(4, password);
            ps.setString(5, phone);
            ps.setString(6, age);
            ps.setString(7, sex);

            int rowsAffected = ps.executeUpdate();

            ps.close();
            con.close();

            return rowsAffected > 0;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
         
        }
		return true;
    }
}
