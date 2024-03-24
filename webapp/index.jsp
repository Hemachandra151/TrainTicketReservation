<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="styles.css">
  <title>Jackdaw Railway</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">
    <img src="images/logo.jpg" alt="Company Logo" class="navbar-logo">
  </a>
  
  <div class="collapse navbar-collapse">
    <ul class="navbar-nav ml-auto">
     <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Search Trains
        </a>
        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="trainDetails.jsp">By Date</a>
          <a class="dropdown-item" href="searchTrain.jsp">By Location</a>
        </div>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="Book.jsp">Book Now</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="adminDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Admin
        </a>
        <div class="dropdown-menu" aria-labelledby="adminDropdown">
          <a class="dropdown-item" href="adminlog.jsp">Log in</a>
        </div>
      </li>
    </ul>
  </div>
</nav>

<div class="center-cards">
  <div class="card" style="width: 18rem;">
    <a href="register.jsp">
      <img src="images/register.jpg" class="card-img-top" alt="Register Image">
    </a>
  </div>

  <div class="card" style="width: 18rem;">
    <a href="login.jsp">
      <img src="images/login.jpg" class="card-img-top" alt="Login Image">
    </a>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</body>
</html>
