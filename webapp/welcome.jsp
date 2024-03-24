<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.*, javax.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.*"%>
<%@ page import="javax.servlet.http.*"%>

<%
    String username = (String) session.getAttribute("username");
    int userId = 0; // Default value, or handle the case where the ID is not found
    
    try {
        // Establish a connection to your database
                	Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/dbs", "root",
                    "");
            
        // Execute a query to fetch the user ID from the users table based on the username
        String query = "SELECT id FROM users WHERE username=?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            // Handle the SQL exception (print, log, or throw)
            e.printStackTrace();
        } finally {
            // Close the database connection
            if (con != null) {
                con.close();
            }
        }
    } catch (Exception e) {
        // Handle any other exceptions that might occur during database access
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <title>Welcome Page</title>
</head>
<body>
    <h2>Welcome, <%= session.getAttribute("username") %></h2>
    <p>User ID: <%= userId %></p>
</body>
</html>
