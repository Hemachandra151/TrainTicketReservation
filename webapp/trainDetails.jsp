<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Train Details</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles1.css">
</head>
<body>

    <div class="card-container">
        <!-- Display the form to input the date -->
        <h2>Enter Date to Get Train Details:</h2>
        <form action="TrainDetailsServlet" method="post">
            <div class="form-group">
                <label for="date">Date:</label>
                <input type="date" class="form-control" id="date" name="date" required>
            </div>
            <button type="submit" class="btn btn-primary">Get Train Details</button>
        </form>

        <!-- Display the train details table if available -->
        <c:if test="${not empty requestScope.trainDetails}">
            <h2>Train Details for Date: ${param.date}</h2>
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th>Train ID</th>
                        <th>Train Name</th>
                        <th>Source Location</th>
                        <th>Destination</th>
                        <th>Seats</th>
                    </tr>
                </thead>
                <tbody>
                    ${requestScope.trainDetails}
                </tbody>
            </table>
        </c:if>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

