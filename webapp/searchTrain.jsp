<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Trains</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles1.css">
</head>
<body>

    <div class="card-container">
        <!-- Display the form to input source location and destination -->
        <h2>Search Trains by Source and Destination:</h2>
        <form action="SearchTrainServlet" method="post">
            <div class="form-group">
                <label for="sourceLoc">Source Location:</label>
                <input type="text" class="form-control" id="sourceLoc" name="sourceLoc" required>
            </div>
            <div class="form-group">
                <label for="destination">Destination:</label>
                <input type="text" class="form-control" id="destination" name="destination" required>
            </div>
            <button type="submit" class="btn btn-primary">Search Trains</button>
        </form>

        <!-- Display the train details table if available -->
        <c:if test="${not empty requestScope.trainDetails}">
            <h2>Search Results:</h2>
            <table class="table">
                <thead class="thead-light">
                    <tr>
                        <th>Train ID</th>
                        <th>Train Name</th>
                        <th>Date</th>
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

