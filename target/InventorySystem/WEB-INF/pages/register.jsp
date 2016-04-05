<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>

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
    <div class="container" align="left">

      <form class="form-signin" method="POST" action="" style="width: 500px" command="userDetails" modelAttribute="userModel">
        <div>
  			<h1>Register</h1>
  			</div>
       		<p class="lead"  align="left">Please enter all fields marked *.</p>
        <div class="form-group">
			<p>First Name: </p>
     		<input type="text" style="width: 200px" id="inputFName" class="form-control" placeholder="First Name *"  name="fName" required/>
  			</div>
  		<div class="form-group">
			<p>Surname: </p>
  			<input type="text" style="width: 300px" id="inputSName" class="form-control" placeholder="Surname *"  name="sName" required/>
  			</div>
  		<div class="form-group">
			<p>Username: </p>
			<h6>*Between 5 - 20 Characters. </h6>
 			<input type="text" style="width: 220px" id="inputUsername" class="form-control" placeholder="Username *" name="username" required/>
		</div>
		<div class="form-group">
			<p>Password: </p>
			<h6>*Between 8 - 16 Characters. </h6>
  			<input type="text" style="width: 350px"id="inputPassword" class="form-control" placeholder="Password *" name="password" required/>
  		</div>
		  <h5>Please note: Your access rights are set at a default level. Please contact an admin to gain higher access.</h5>
		<div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" style="width: 100px" type="submit">Register</button>
        </div>


      </form>

      
    </div> <!-- /container -->
</body>
</html>