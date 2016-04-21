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
          <li role="presentation" class="active"><a href="/crates">Crates.</a></li>
          <li role="presentation"><a href="/inventory">Inventory.</a></li>
          <li role="presentation"><a href="/assets">Assets.</a></li>
          <li role="presentation"><a href="/users">Users.</a></li>
        </ul>
      </div>
      <br>
      <div class="row">
        <ol class="breadcrumb">
          <li class="active">Home</li>
        </ol>
      </div>
      <div id="crateInfo" class="container">
        <h2 style="margin-top: 5px;">Crates</h2>
        <div class="col-md-3">
          <form method="post" action="" command="crate">
            <h3>Add Crate:</h3>
            <div class="input-group">
              <label id="namelbl">Crate Name: </label>
              <input type="text" id="crateName" class="form-control" placeholder="Crate Name" name="cName" required/>
            </div>
            <div class="input-group">
              <label id="heightLbl">Height (cm): </label>
              <input type="text" id="height" class="form-control" placeholder="Height" name="height" required/>
            </div>
            <div class="input-group">
              <label id="weightLbl">Width (cm): </label>
              <input type="text" id="weight" class="form-control" placeholder="Weight" name="width" required/>
            </div>
            <div class="input-group">
              <label id="storehouseLbl">Select a storehouse.</label>
              <select multiple class="form-control" name="sid">
                <c:forEach var="storehouse" items="${shList}">
                  <option value="${storehouse._id}">${storehouse.name}</option>
                </c:forEach>
              </select>
            </div>
            <div style="padding-top: 10px" class="input-group">
              <button class="btn btn-md btn-primary btn-block" type="submit">Add Crate.</button>
              </div>
          </form>
        </div>
        <div id="allCrates" class="col-md-9">
          <h3>Current Crates: </h3>
          <table id="crates" class="table table-condensed display">
            <script>
              $(document).ready( function () {
                $('#crates').DataTable();
              } );
            </script>
            <thead>
            <tr>
              <th><span class="text"> Crate Name</span></th>
              <th><span class="text"> Width (cm)</span></th>
              <th><span class="text"> Height (cm)</span></th>
              <th><span class="text"> Date Created</span></th>
              <th><span class="text"> View</span></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="crate" items="${cList}">
              <c:url value="/crates/viewCrate?_id=${crate._id}" var="viewURL"></c:url>
                <tr>
                  <td>${crate.cName}</td>
                  <td>${crate.width}</td>
                  <td>${crate.height}</td>
                  <td>${crate.dateCreated}</td>
                  <td><a href='<c:out value="${viewURL}" escapeXml="true"></c:out>'>View <span class="glyphicon glyphicon-edit"></span></a></td>
                </tr>
            </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
      <hr>
      <p>Use <a href="../sticky-footer-navbar">links as so</a> if needed.</p>
    </div>

    <footer class="footer">
      <div class="container">
        <p class="text-muted">Footer Content here.</p>
      </div>
    </footer>

  </body>
</html>
