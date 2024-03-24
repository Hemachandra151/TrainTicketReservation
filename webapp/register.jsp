<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <title>Registration Page</title>
    <style>
        /* Add custom styles if needed */
        body {
            display: flex;
            align-items: center;
            justify-content: center;
            height: 100vh;
        }
        .card {
            width: 400px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="card">
        <form action="RegisterServlet" method="post">
            <div class="form-group">
                <label for="fname">First Name:</label>
                <input type="text" class="form-control" id="fname" name="fname" required>
            </div>

            <div class="form-group">
                <label for="lname">Last Name:</label>
                <input type="text" class="form-control" id="lname" name="lname" required>
            </div>

            <div class="form-group">
                <label for="age">Age:</label>
                <input type="number" class="form-control" id="age" name="age" required>
            </div>

            <div class="form-group">
                <label>Gender:</label><br>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="male" name="gender" value="male">
                    <label class="form-check-label" for="male">Male</label>
                </div>
                <div class="form-check form-check-inline">
                    <input type="radio" class="form-check-input" id="female" name="gender" value="female">
                    <label class="form-check-label" for="female">Female</label>
                </div>
            </div>

            <div class="form-group">
                <label for="phone">Phone Number:</label>
                <input type="text" class="form-control" id="phone" name="phone" required>
            </div>

            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" class="form-control" id="username" name="username" required>
            </div>

            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <button type="submit" class="btn btn-primary">Register</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

