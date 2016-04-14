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

    <title>Assets</title>

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
		  <li role="presentation"><a href="homepage">Home.</a></li>
		  <li role="presentation"><a href="storehouses">Storehouses.</a></li>
          <li role="presentation"><a href="crates">Crates.</a></li>
          <li role="presentation"><a href="inventory">Inventory.</a></li>
          <li role="presentation" class="active"><a href="assets">Assets.</a></li>
          <li role="presentation"><a href="users">Users.</a></li>
		</ul>
      </div>
      <div>
        <h2>Assets</h2>
      </div>
      <div class="container-fluid">
        <form method="post" action="" command="asset">
          <div class="col-md-3">
            <div class="form-group">
              <div class="input-group">
                <label>Name: </label>
                <input type="text" id="inventoryName" class="form-control" placeholder="Item name" name="aname" required/>
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <label for="quantity">Quantity: 	&nbsp; </label>
                <input id="quantity" type="number" name="qty" min="0" max="3000" required/>
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <label for="cid">Crate: 	&nbsp; </label>
                <select multiple class="form-control" id="cid" name="cid">
                  <c:forEach var="crate" items="${crateList}">
                    <option value="${crate.id}">${crate.cName}</option>
                  </c:forEach>
                </select>
              </div>
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <div class="input-group">
                <label>Description: </label>
                <textarea type="text" id="desc" class="form-control" name="description" style="height: 200px; width: 300px;" required></textarea>
              </div>
            </div>
          </div>
          <div class="col-md-3">
            <div class="form-group">
              <label>Value: </label>
              <div class="input-group">
                <span class="input-group-addon" id="sizing-addon2"><span class="glyphicon glyphicon-gbp"></span> </span>
                <input style="width: 100px" type="text" class="form-control" name="value"/>
              </div>
            </div>
            <div class="form-group">
              <div class="input-group">
                <label>Date Purchased: </label>
                <input type="date" name="purchased">
              </div>
            </div>
            <div class="input-group">
              <button type="submit" class="btn btn-md btn-primary btn-block">Add Asset.</button>
            </div>
          </div>
        </form>
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
