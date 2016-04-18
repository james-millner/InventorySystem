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

    <title>Inventory</title>

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
          <li role="presentation" class="active"><a href="/inventory">Inventory.</a></li>
          <li role="presentation"><a href="/assets">Assets.</a></li>
          <li role="presentation"><a href="/users">Users.</a></li>
        </ul>
      </div>
      <div id="title">
        <h2>Assets</h2>
        <div class="row">
          <ol class="breadcrumb">
            <li><a href="/inventory">Home</a></li>
            <li><a href="/inventory/addInventory"><span class="glyphicon glyphicon-plus"></span>Add Entry</a></li>
            <li><a href="/inventory/viewAll">View Inventory</a></li>
            <li class="active">View ${inventory.iname}</li>
          </ol>
        </div>
      </div>
      <div style="background-color: #E8E8E8; border-radius: 10px;" class="container-fluid">
        <div class="col-md-12">
          <form method="post" action="" command="inventory">
          <div class="col-md-4">
            <h2><input type="text" style="width: 320px;" value="${inventory.iname}" name="iname"/> </h2><label><b>Created On:</b></label> <input disabled type="text" name="dateCreated" value="${inventory.dateCreated}"/>
            <b>Description: </b><br><p></p><textarea type="text" id="desc" class="form-control" name="description" style="height: 100px; width: 320px;">${inventory.description}</textarea><br>
            <b>Quantity: </b><input id="quantity" type="number" name="qty" min="0" max="3000" value="${inventory.qty}"/>

          </div>
          <div class="col-md-4">
            <h3><b>Crate Information:</b></h3>
            <select multiple class="form-control" id="cid" name="cid">
                <option value="">Free</option>
              <c:forEach var="crate" items="${crateList}">
                <option value="${crate._id}">${crate.cName}</option>
              </c:forEach>
            </select>
            <div class="input-group">
              <label for="cid">Type: 	&nbsp; </label>
              <select multiple class="form-control" id="type" name="type">
                <option>Active</option>
                <option>InActive</option>
                <option>Hold</option>
                <option>Permanent</option>
              </select>
            </div>
          </div>
          <div class="col-md-4">
            <div class="input-group">
              <br>
              <label>Additional (Not Required: </label>
              <textarea type="text" id="additional" class="form-control" name="extraInfo" style="height: 60px; width: 280px;"></textarea>
            </div>
          </div>
          <div style="margin: 10px" class="col-md-12" align="right">
            <h3>Actions:</h3>
            <button type="submit" class="btn btn-md btn-primary">Save.</button>
          </div>
          </form>
        </div>

      </div>
      <p>Use <a href="../sticky-footer-navbar">links as so</a> if needed.</p>
    </div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted">Footer Content here.</p>
      </div>
    </footer>

  </body>
</html>
