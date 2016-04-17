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

    <title>Inventory</title>

    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <!-- Bootstrap -->
    <link href="/resources/css/viewall.css" rel="stylesheet">
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
          <li role="presentation"class="active"><a href="/inventory">Inventory.</a></li>
          <li role="presentation"><a href="/assets">Assets.</a></li>
          <li role="presentation"><a href="/users">Users.</a></li>
		</ul>
      </div>
      <div id="title">
                <h2>Inventory</h2>
                <div class="row">
                  <ol class="breadcrumb">
                    <li><a href="/inventory">Home</a></li>
                    <li><a href="/inventory/addInventory"><span class="glyphicon glyphicon-plus"></span>Add Entry</a></li>
                    <li class="active"><a href="/inventory/viewAll"> View Inventory</a></li>
                    <li class="active">View ${item.iname}</li>
                  </ol>
                </div>
                <h4>View All.</h4>
                <h5>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</h5>
      </div>
      <div class="container-fluid">
        <h5><b>Quick View!</b></h5>
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
