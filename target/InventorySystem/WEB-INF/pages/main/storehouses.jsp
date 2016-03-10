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

    <title>Storehouses</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>

    <link rel="stylesheet" type="text/css" href="/resources/css/Storehouses.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

  <body>

    <!-- Begin page content -->
    <div class="container">
      <div class="page-header">
        <h1>InventorySys</h1>
      </div>
      <div class="container">
      	<ul class="nav nav-tabs">
		  <li role="presentation"><a href="homepage">Home.</a></li>
		  <li role="presentation" class="active"><a href="storehouses">Storehouses.</a></li>
          <li role="presentation"><a href="crates">Crates.</a></li>
          <li role="presentation"><a href="inventory">Inventory.</a></li>
          <li role="presentation"><a href="assets">Assets.</a></li>
          <li role="presentation"><a href="users">Users.</a></li>
		</ul>
      </div>

     <h2>Storehouses</h2>

      <hr>
      <div class="container">
        <div class="row">
         <form method="POST" action="" command="sh">
          <div class="col-md-3">
            <h3>Add new storehouse:</h3>
            <div class="input-group">
              <label id="namelbl">Storehouse Name: </label>
                <input type="text" id="storehname" class="form-control" placeholder="Storehouse Name" name="name" required/>
              </div>
            <div class="input-group">
              <label id="sizelbl">Size:</label>
                <input type="text" id="size" class="form-control" placeholder="Size (SQ Ft.)" name="size" required/>
            </div>
            <div class="input-group">
              <label id="accesslbl">Access:</label>
              <input type="text" id="access" class="form-control" placeholder="Access" name="access" required/>
            </div>
          </div>
          <div class="col-md-3">
            <label>Address:</label>
              <input class="form-control input-lg" type="text" id="address" name="address"/>
          </div>
          <div id="options" class="col-md-6">

              <label>Options:</label>
              <div class="row">
                  <div class="col-md-3">
                      <div class="checkbox">
                        <label><input type="checkbox" name="owned" value="true">Self Owned.</label>
                      </div>
                  </div>
                  <div class="col-md-3">
                      <div class="checkbox">
                      <label><input type="checkbox" name="rented" value="true">Rented.</label>
                  </div>
              </div>
              </div>
              <div class="row">
                  <div class="col-md-3">
                      <label>Active:</label>
                      <div class="checkbox">
                          <label><input type="checkbox" name="active" value="true">Active.</label>
                      </div>
                  </div>
              <button class="btn btn-md btn-primary btn-block" type="submit">Add Storehouse.</button>

            </div>
              </div>
            </form>
        </div>
    <hr>
      </div>
        <div class="row">
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Storehouse Name</th>
                        <th>Address</th>
                        <th>Size</th>
                        <th>Access</th>
                        <th>IsActive?</th>
                        <th>IsOwned?</th>
                        <th>IsRented?</th>
                        <th>Edit</th>
                        <th>Delete</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="storehouse" items="${storehouseList}">
                    <c:url value="/editStorehouse?_id=${storehouse.id}" var="editURL"></c:url>
                    <c:url value="/deleteStorehouse?_id=${storehouse.id}" var="deleteURL"></c:url>
                    <tr>
                        <td>${storehouse.name}</td>
                        <td>${storehouse.address}</td>
                        <td>${storehouse.size}</td>
                        <td>${storehouse.access}</td>
                        <td>${storehouse.active}</td>
                        <td>${storehouse.owned}</td>
                        <td>${storehouse.rented}</td>
                        <td><a href='<c:out value="${editURL}" escapeXml="true"></c:out>'>Edit</a></td>
                        <td><a href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        </div>
        <div class="row">
            <div class="col-lg-12" >Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque sed porttitor nisi.
                Mauris sed consectetur neque. Proin mattis quam ac magna ultrices pretium. Duis sit amet nibh iaculis erat vestibulum vehicula sed non felis.
                Duis nec ante ut leo sodales.</div>
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
