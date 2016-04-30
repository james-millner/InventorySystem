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
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
    <script type="text/javascript">

      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      google.charts.setOnLoadCallback(drawValueChart);

      function drawChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Type');
        data.addColumn('number', 'Amount');
        <c:forEach var="type" items="${stats}">
        data.addRows([
          ['${type.name}', ${type.value}]
        ]);
        </c:forEach>

        var options = {
          title: 'Total Types: ',
          'width': '100%',
          'height': '100%'};


        var chart = new google.visualization.PieChart(document.getElementById('chart'));
        chart.draw(data, options);
      }

      function drawValueChart() {
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'AssetType');
        data.addColumn('number', 'Total');
        <c:forEach var="assetStat" items="${valueStats}">
        data.addRows([
          ['${assetStat.name}', ${assetStat.value}]
        ]);
        </c:forEach>

        var optionsValue = {
          title: ' Value by Type: ',
          'width': '100%',
          'height': '100%'};

        var chartValue = new google.visualization.PieChart(document.getElementById('chartValue'));
        chartValue.draw(data, optionsValue)
      }
    </script>


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
            <li class="active">Home</li>
            <li><a href="/assets/addAsset"><span class="glyphicon glyphicon-plus"></span>Add Asset</a></li>
            <li><a href="/assets/settings"><span class="glyphicon glyphicon-th-list"></span>&nbsp;Asset Settings</a></li>
            <li><a href="/assets/viewAll">View Assets</a></li>
          </ol>
        </div>
      </div>
      <div class="container">
        <div class="col-md-6" id="chart" style=" height: 400px"></div>
        <div class="col-md-6">
          <h2>Sticky Assets: </h2>
          <div id="table">
            <div id="table-scroll">
              <table id="stickyStock" class="table table-condensed table-striped display">
                <script>
                  $(document).ready( function () {
                    $('#stickyStock').DataTable( {
                      "order": [[ 1, 'asc' ]]
                    } );
                  } );
                </script>
                <thead>
                <tr>
                  <th><span class="text"> Item Name:</span></th>
                  <th><span class="text"> DateCreated:</span></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${asstByDate}">
                  <tr>
                    <td><a href="/assets/viewAsset?_id=${item._id}"> ${item.aname}</a></td>
                    <td>${item.dateCreated}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <hr>
      <div class="container-fluid">
        <div class="col-md-5" id="chartValue" style="height: 400px; width: 400px"></div>
        <div class="col-md-7">
          <h2>Quick Value View Assets: </h2>
          <div id="tableValue">
            <div id="tableValue-scroll">
              <table id="values" class="table table-condensed table-striped display">
                <script>
                  $(document).ready( function () {
                    $('#values').DataTable( {
                      "order": [[ 1, 'desc' ]]
                    } );
                  } );
                </script>
                <thead>
                <tr>
                  <th><span class="text"> Item Name:</span></th>
                  <th><span class="text"> Value:</span></th>
                  <th><span class="text"> Type:</span></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${asstByDate}">
                  <tr>
                    <td><a href="/assets/viewAsset?_id=${item._id}">${item.aname} </a></td>
                    <td>£${item.value}</td>
                    <td>${item.type}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
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
