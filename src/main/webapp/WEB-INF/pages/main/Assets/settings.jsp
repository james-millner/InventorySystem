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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <link rel="stylesheet" href="/resources/css/tabs.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
            <li class="active"><span class="glyphicon glyphicon-th-list"></span>&nbsp;Asset Settings</li>
            <li><a href="/assets/viewAll">View Assets</a></li>
          </ol>
        </div>
      </div>
      <c:if test="${bool == 'true'}">
        <div class="alert alert-danger alert-dismissible" role="alert" align="center">
          <p><strong>Warning!</strong> You cannot delete this type as it contains Asset Items. <strong>In order to delete this type</strong> you must first
            move any entries you have stored.</p>
        </div>
      </c:if>

      <div class="container-fluid">
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
            <li class="tab-link current" data-tab="tab-1">Add Asset Type!</li>
            <li class="tab-link" data-tab="tab-2">Other Settings.</li>
          </ul>

          <div id="tab-1" class="tab-content current col-md-12">
            <div style="border-right: groove 1px; height: 150px" class="col-md-3" align="right">
             <form method="post" action="" command="type">
               <div class="form-group">
                 <div class="input-group">
                   <h3>New Asset Type: </h3>
                 </div>
                 <div class="input-group">
                   <label>Enter new type:</label>
                 </div>
                 <div class="input-group">
                   <input type="text" name="type">
                   <button type="submit"><span class="glyphicon glyphicon-plus"></span></button>
                 </div>
               </div>
             </form>
            </div>
            <div class="col-md-9">
              <table id="types" class="table display">
                <script>
                  $(document).ready( function () {
                    $('#types').DataTable();
                  } );
                </script>
                <thead>
                  <tr>
                    <th><span class="text">Number</span></th>
                    <th><span class="text">Type</span></th>
                    <th><span class="text">Remove</span></th>
                  </tr>
                </thead>
                <tbody>
                  <c:set var="count" value="0" scope="page"/>
                  <c:forEach var="type" items="${types}">
                    <c:url value="/assets/deleteType?type=${type.type}" var="delURL"></c:url>
                    <tr>
                      <c:set var="count" value="${count + 1}" scope="page"></c:set>
                      <td>${count}</td>
                      <td>${type.type}</td>
                      <td><a href='<c:out value="${delURL}" escapeXml="true"></c:out>'><span class="glyphicon glyphicon-trash"></span></a></td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>

          </div>
          <div id="tab-2" class="tab-content">
            Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
          </div>

        </div><!-- container -->
      </div>
    </div>


  </body>
</html>
