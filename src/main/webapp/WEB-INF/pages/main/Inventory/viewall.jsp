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
                          <li><a href="/inventory/addInventory"><span class="glyphicon glyphicon-plus"></span>&nbsp;Add Entry</a></li>
                          <li><a href="/inventory/settings"><span class="glyphicon glyphicon-th-list"></span>&nbsp;Inventory Settings</a></li>
                          <li class="active">View Inventory</li>
                      </ol>
                  </div>
                <h4>View All.</h4>
      </div>
      <div class="container-fluid">
        <h5><b>Quick View!</b></h5>
        <div id="table">
            <div id="table-scroll">
            <table id="inventoryTable" class="table table-condensed table-striped display">
                <script>
                $(document).ready( function () {
                $('#inventoryTable').DataTable();
                } );
                </script>
                <thead>
            <tr>
              <th><span class="text"> Item Name</span></th>
              <th><span class="text"> Quantity</span></th>
              <th><span class="text"> Type</span></th>
              <th><span class="text"> Description</span></th>
              <th><span class="text"> Edit</span></th>
              <th><span class="text"> Delete</span></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="item" items="${inventoryList}">
              <c:url value="/inventory/viewInventory?_id=${item._id}" var="viewURL"></c:url>
              <c:url value="/inventory/del?_id=${item._id}" var="deleteURL"></c:url>
              <tr>
                <td>${item.iname}</td>
                <td>${item.qty}</td>
                <td>${item.type}</td>
                <td>${item.description}</td>
                <td><a href='<c:out value="${viewURL}" escapeXml="true"></c:out>'>View <span class="glyphicon glyphicon-edit"></span></a></td>
                <td><a href='<c:out value="${deleteURL}" escapeXml="true"></c:out>'>Delete<span class="glyphicon glyphicon-trash"></span></a></td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          </div>
       </div>
      </div>
    </div>
  </body>
</html>
