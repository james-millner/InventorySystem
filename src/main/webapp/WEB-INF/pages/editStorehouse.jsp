<%--
  Created by IntelliJ IDEA.
  User: James
  Date: 05/03/2016
  Time: 20:23
  To change this template use File | Settings | File Templates.
--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>Homepage</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

</head>
<body>

<!-- Begin page content -->
<div class="container">
    <div class="page-header">
        <h1>InventorySys</h1>
    </div>
    <div>
        <h2>Edit Record.</h2>
    </div>
    <div class="container">
        <div class="row">
            <form method="POST" action="" command="house">
                <div class="col-md-3">
                    <h3>Add new storehouse:</h3>
                    <div class="input-group">
                        <label id="namelbl">Storehouse Name: </label>
                        <input type="text" id="storehname" class="form-control" placeholder="Storehouse Name" name="name" value="${houseM.name}" required/>
                    </div>
                    <div class="input-group">
                        <label id="sizelbl">Size:</label>
                        <input type="text" id="size" class="form-control" placeholder="Size (SQ Ft.)" name="size" value="${houseM.size}" required/>
                    </div>
                    <div class="input-group">
                        <label id="accesslbl">Access:</label>
                        <input type="text" id="access" class="form-control" placeholder="Access" name="access" value="${houseM.access}" required/>
                    </div>
                </div>
                <div class="col-md-3">
                    <label>Address:</label>
                        <input class="form-control input-lg" type="text" id="address" name="address" value="${houseM.address}"/>
                    <div class="checkbox">
                        <label><input type="checkbox" name="owned" value="true">Self Owned.</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="rented" value="true">Rented.</label>
                    </div>
                    <div class="checkbox">
                        <label><input type="checkbox" name="active" value="true">Active.</label>
                    </div>
                    <button class="btn btn-md btn-primary btn-block" type="submit">Add Storehouse.</button>
                </div>

            </form>
        </div>
        <hr>
    </div>
</div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">Footer Content here.</p>
    </div>
</footer>

</body>
</html>

