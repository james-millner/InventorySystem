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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <link rel="stylesheet" type="text/css" href="//cdn.datatables.net/1.10.11/css/jquery.dataTables.css">
    <script type="text/javascript" charset="utf8" src="//cdn.datatables.net/1.10.11/js/jquery.dataTables.js"></script>
    <script type="text/javascript">
      google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);

      function drawChart() {

        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Cuisine');
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
        chart.draw(data, options);;
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
          <li role="presentation"class="active"><a href="/inventory">Inventory.</a></li>
          <li role="presentation"><a href="/assets">Assets.</a></li>
          <li role="presentation"><a href="/users">Users.</a></li>
        </ul>
      </div>
      <div id="title">
                <h2>Inventory Home</h2>
                <div class="row">
                  <ol class="breadcrumb">
                    <li class="active">Home</li>
                    <li><a href="/inventory/addInventory"><span class="glyphicon glyphicon-plus"></span>&nbsp;Add Entry</a></li>
                    <li><a href="/inventory/settings"><span class="glyphicon glyphicon-th-list"></span>&nbsp;Inventory Settings</a></li>
                    <li><a href="/inventory/viewAll">View Inventory</a></li>
                  </ol>
                </div>
      </div>
      <div class="container">
        <div class="col-md-6" id="chart" style=" height: 400px"></div>
        <div class="col-md-6">
          <h2>Sticky Inventory: </h2>
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
                <c:forEach var="item" items="${invByDate}">
                  <tr>
                    <td><a href="/inventory/viewInventory?_id=${item._id}"> ${item.iname}</a></td>
                    <td>${item.dateCreated}</td>
                  </tr>
                </c:forEach>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <hr>
        <div class="col-md-12" style="margin-top: 15px; border-top: groove 4px">
          <h1> Crate Information: </h1>
          <div class="container-fluid">
            <div id="tableCrate">
              <div id="tableCrate-scroll">
                <table id="tableCrates" class="table table-condensed table-striped display">
                  <script>
                    $(document).ready( function () {
                      $('#tableCrates').DataTable( {

                      } );
                    } );
                  </script>
                  <thead>
                  <tr>
                    <th><span class="text"> Crate Name:</span></th>
                    <th><span class="text"> Item Name:</span></th>
                    <th><span class="text"> Quantity:</span></th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="item" items="${invWithCrate}">
                    <tr>
                      <td>
                        <c:if test="${item.cid.equals('')}">
                          <p>Loose Standing</p>
                        </c:if>
                        <c:if test="${not item.cid.equals('')}" >
                          <a href="/crates/viewCrate?_id=${item.extraInfo}"> ${item.cid}</a>
                        </c:if>
                      </td>
                      <td><a href="/inventory/viewInventory?_id=${item._id}"> ${item.iname}</a></td>
                      <td>${item.qty}</td>
                    </tr>
                  </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>

  </body>
</html>
