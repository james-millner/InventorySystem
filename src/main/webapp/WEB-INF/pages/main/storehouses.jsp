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
    <link rel="stylesheet" href="/resources/css/tabs.css">
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

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
                <li role="presentation" class="active"><a href="/storehouses">Storehouses.</a></li>
                <li role="presentation"><a href="/crates">Crates.</a></li>
                <li role="presentation"><a href="/inventory">Inventory.</a></li>
                <li role="presentation"><a href="/assets">Assets.</a></li>
                <li role="presentation"><a href="/users">Users.</a></li>
            </ul>
        </div>

     <h2>Storehouses</h2>

      <hr>
      <div class="container">
          <script>
              $(document).ready(function(){

                  $('ul.tabs li').click(function(){
                      var tab_id = $(this).attr('data-tab');

                      $('ul.tabs li').removeClass('current');
                      $('.tab-content').removeClass('current');

                      $(this).addClass('current');
                      $("#"+tab_id).addClass('current');
                  })

              })
          </script>
        <div>
            <ul class="tabs">
                <li class="tab-link current" data-tab="tab-1">Add Storehouse!</li>
                <li class="tab-link" data-tab="tab-2">View Storehouses.</li>
            </ul>

            <div id="tab-1" class="tab-content current col-md-12">
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
                  <textarea class="form-control input-lg" type="text" id="address" name="address"></textarea>
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
            <div id="tab-2" class="tab-content">
                <table id="storehouseTable" class="table table-striped display">
                    <script>
                        $(document).ready( function () {
                            $('#storehouseTable').DataTable();
                        } );
                    </script>
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
                        <c:url value="/editStorehouse?_id=${storehouse._id}" var="editURL"></c:url>
                        <c:url value="/deleteStorehouse?_id=${storehouse._id}" var="deleteURL"></c:url>
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
        </div>
        <hr>
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
