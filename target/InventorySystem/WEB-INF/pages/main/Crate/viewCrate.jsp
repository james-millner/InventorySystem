<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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

    <title>Crates</title>

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
        <a href="/signin" class="text">Log out.</a>
    </div>
    <div class="container">
        <ul class="nav nav-tabs">
            <li role="presentation"><a href="/homepage">Home.</a></li>
            <li role="presentation"><a href="/storehouses">Storehouses.</a></li>
            <li role="presentation" class="active"><a href="/crates">Crates.</a></li>
            <li role="presentation"><a href="/inventory">Inventory.</a></li>
            <li role="presentation"><a href="/assets">Assets.</a></li>
            <li role="presentation"><a href="/users">Users.</a></li>
        </ul>
    </div>
    <br>
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="/crates">Home</a></li>
            <li class="active">View ${crate.cName}</li>
        </ol>
    </div>
    <div class="container-fluid">
        <c:if test="${bool == 'true'}">
            <div class="alert alert-danger alert-dismissible" role="alert">
                <p><strong>Warning!</strong> You cannot delete this crate as it contains Inventory and/or Asset Items. <strong>In order to delete this crate</strong> you must first
                move any entries you have stored.</p>
            </div>
        </c:if>
    </div>
    <div id="crateInfo" style="background-color: #E8E8E8; border-radius: 10px;" class="container">
        <div class="col-md-4">
            <h2><b>Crate Name:</b></h2>
            <h3>${crate.cName}</h3>
            <label>Dimensions: </label>
            <p><b>Height (cm):   </b>${crate.height}</p>
            <p><b>Width (cm):   </b>${crate.width}</p>
            <label>Date Created: </label>
            <p>${crate.dateCreated.toString()}</p>
        </div>
        <div class="col-md-4">
            <h2><b>Storehouse Information: </b></h2>
            <h3>${storehouse.name}</h3>
            <label>Address</label>
            <p>${storehouse.address}</p>
            <label>Access: </label>
            <p>${storehouse.access}</p>
            <c:if test="${storehouse.active == true}">
                <p>Active: <span class="glyphicon glyphicon-ok"></span></p>
            </c:if>
            <c:if test="${storehouse.owned == true}">
                <p>Owned: <span class="glyphicon glyphicon-ok"></span></p>
            </c:if>
            <c:if test="${storehouse.rented == true}">
                <p>Rented: <span class="glyphicon glyphicon-ok"></span></p>
            </c:if>
            <c:if test="${storehouse.active == false}">
                <p>Active: <span class="glyphicon glyphicon-remove"></span></p>
            </c:if>
            <c:if test="${storehouse.owned == false}">
                <p>Owned: <span class="glyphicon glyphicon-remove"></span></p>
            </c:if>
            <c:if test="${storehouse.rented == false}">
                <p>Rented: <span class="glyphicon glyphicon-remove"></span></p>
            </c:if>
            </div>
        </div>
        <div class="col-md-4" style="margin-top: 10px;">
            <a href="/crates/delCrate?_id=${crate._id}" class="btn btn-danger">Delete <span class="glyphicon glyphicon-trash"></span></a>
        </div>
    </div>
    <hr>
</div>


</body>
</html>
