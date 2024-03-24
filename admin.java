import java.io.IOException;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class admin
 */
@WebServlet("/admin")
public class admin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        String username = "admin";
        String pwd = request.getParameter("password");

        if (isAdmin(username, pwd) != 0) {
        	
          response.sendRedirect("adminedit.jsp");
        } else {
            out.println("<html><head><title>Login Failed</title></head><body> Failed to login as admin</body></html>");
        }
    }

    protected int isAdmin(String un, String password) {
        int result = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbs", "root", "");
            String sql = "select * from users where username=? and password=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, un);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = 1; // Admin found
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
