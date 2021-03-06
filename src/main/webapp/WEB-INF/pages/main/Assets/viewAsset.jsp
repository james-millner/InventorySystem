<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page pageEncoding="UTF-8" %>

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

    <title>Assets</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <!-- Bootstrap -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>

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
          <li role="presentation"><a href="/crates">Crates.</a></li>
          <li role="presentation"><a href="/inventory">Inventory.</a></li>
          <li role="presentation" class="active"><a href="/assets">Assets.</a></li>
          <li role="presentation"><a href="/users">Users.</a></li>
        </ul>
      </div>
      <div id="title">
        <h2>Assets</h2>
        <div class="row">
          <ol class="breadcrumb">
            <li><a href="/assets">Home</a></li>
            <li><a href="/assets/addAsset"><span class="glyphicon glyphicon-plus"></span>Add Asset</a></li>
            <li><a href="/assets/settings"><span class="glyphicon glyphicon-th-list"></span>&nbsp;Asset Settings</a></li>
            <li><a href="/assets/viewAll">View Assets</a></li>
            <li class="active">View ${asset.aname}</li>
          </ol>
        </div>
      </div>
      <div style="background-color: #E8E8E8; border-radius: 10px;" class="container-fluid">
        <div class="col-md-12">
          <div class="col-md-4">
            <h2><b>${asset.aname}</b></h2><p><b>Created On:</b> ${asset.dateCreated}</p>
            <b>Description: </b><br><p></p><p>${asset.description}</p>
            <b>Quantity: </b><input type="number" value="${asset.qty}" disabled/>
            <br>
            <p></p>
            <label>Asset Type: </label>
            <p>${asset.type}</p>
            <b>Value:</b><p style="font-size: 22px;">£${asset.value}</p>
          </div>
          <div class="col-md-4">
            <h3><b>Storehouse Information:</b></h3>
            <c:if test="${empty storehouse}">
              ${na}
            </c:if>
            <c:if test="${not empty storehouse}">
              <label>Name: </label>
              <p>${storehouse.name}</p>
              <label>Address: </label>
              <p>${storehouse.address}</p>
            </c:if>
          </div>
          <div class="col-md-4">
            <h3><b>Crate Information:</b></h3>
            <c:if test="${empty crate}">
              ${na}
            </c:if>
            <c:if test="${not empty crate}">
              <label>Name: </label>
              <p>${crate.cName}</p>
              <p><b>Width: </b>${crate.width} 	&nbsp; <b>Height: </b>${crate.height}</p>
            </c:if>
          </div>
          <div style="margin: 10px" class="col-md-12" align="right">
            <h3>Actions:</h3>
            <a class="btn btn-primary" href="/assets/viewAsset/update?_id=${asset._id}">Update</a>
          </div>
        </div>

      </div>
    </div>

  </body>
</html>
