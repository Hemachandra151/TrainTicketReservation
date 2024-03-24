<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Panel</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            text-align: center;
            padding: 50px;
        }
        .form-container {
            max-width: 400px;
            margin: auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        input {
            width: 100%;
            padding: 10px;
            margin: 8px 0;
            box-sizing: border-box;
        }
        button {
            background-color: #4caf50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <h2>Welcome Admin!</h2>

    <!-- Insert Form -->
    <div class="form-container">
        <form action="AdminServlet" method="post">
            <input type="hidden" name="action" value="insert">
            <label for="trainId">Train ID:</label>
            <input type="text" id="trainId" name="trainId" required>
            <br>
            <label for="trainName">Train Name:</label>
            <input type="text" id="trainName" name="trainName" required>
            <br>
            <label for="date">Date:</label>
            <input type="text" id="date" name="date" required>
            <br>
            <label for="sourceLoc">Source Location:</label>
            <input type="text" id="sourceLoc" name="sourceLoc" required>
            <br>
            <label for="destination">Destination:</label>
            <input type="text" id="destination" name="destination" required>
            <br>
            <label for="seats">Seats:</label>
            <input type="number" id="seats" name="seats" required>
            <br>
            <button type="submit">Insert Train</button>
        </form>
    </div>

    <!-- Delete Form -->
    <div class="form-container">
        <form action="AdminServlet" method="post">
            <input type="hidden" name="action" value="delete">
            <label for="trainIdToDelete">Train ID to Delete:</label>
            <input type="text" id="trainIdToDelete" name="trainId" required>
            <button type="submit">Delete Train</button>
        </form>
    </div>
</body>
</html>
