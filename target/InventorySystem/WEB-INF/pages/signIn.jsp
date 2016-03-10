<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Sign In</title>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>


</head>
<body>

    <div class="container" align="center">

      <form class="form-signin" method="POST" action="" style="width: 500px" command="userDetails">
        <h2 class="form-signin-heading">Sign In</h2>
        <p class="lead">Please enter all fields marked *.</p>
          <div class="form-group">
     	<input type="text" id="inputEmail" class="form-control" placeholder="Username *"type="text" name="username" required/>
  		</div>
          <div class="form-group">
  		<input type="password" id="inputPassword" class="form-control" placeholder="Password *" type="text" name="password" required/>
          </div>
        <div class="button-box col-lg-12">
        <button class="btn btn-primary " type="submit">Sign in</button>
        <a href="/register" class="btn btn-primary " type="submit">Register</a>
        </div>
      </form>



    </div> <!-- /container -->

</body>
</html>