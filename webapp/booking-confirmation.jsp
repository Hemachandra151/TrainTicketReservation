<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Booking Confirmation</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="styles1.css">
</head>
<body>

    <div class="card-container">
        <h2>Booking Confirmation:</h2>

        <c:if test="${not empty requestScope.confirmationMsg}">
            <p>${requestScope.confirmationMsg}</p>
        </c:if>

        <c:if test="${not empty requestScope.ticketNumber}">
            <p>Ticket Number: ${requestScope.ticketNumber}</p>
        </c:if>

        <a href="home.jsp">Back to Home</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
