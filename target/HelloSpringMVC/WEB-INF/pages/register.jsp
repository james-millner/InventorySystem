<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
<head>
<%-- JQuery. --%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<%-- Bootstrap. --%>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<%-- Link bootstrap CSS. --%>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
</script>


</head>
</head>
<body>
    
    <div class="container" align="center">

      <form class="form-signin" method="POST" action="" style="width: 500px" command="userDetails">
        <div class="jumbotron">
  			<h1>Register</h1>
  			</div>
        <p class="lead"  align="left">Please enter all fields marked *.</p>
        <div class="form-group">
     		<input type="text" id="inputFName" class="form-control" placeholder="First Name *" type="text" name="fName"/>
  			</div>
  		<div class="form-group">
  			<input type="text" id="inputSName" class="form-control" placeholder="Surname *" type="text" name="sName"/>
  			</div>
  		<div class="form-group">
			<input type="text" id="inputUsername" class="form-control" placeholder="Username *" type="text" name="username"/>
		</div>
		<div class="form-group">
  			<input type="text" id="inputPassword" class="form-control" placeholder="Password *" type="text" name="password"/>
  		</div>
		<div class="form-group">
			<input type="text" id="inputType" class="form-control" placeholder="Type *" type="text" name="type"/>
		</div>
		<div class="form-group">
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </div>
      </form>
      
     
      
    </div> <!-- /container -->

</body>
</html>