<%--
  Created by IntelliJ IDEA.
  User: James
  Date: 05/04/2016
  Time: 13:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

    <title>Edit User.</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.0/jquery.min.js"></script>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" >
    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" >
    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" ></script>
    <link rel="stylesheet" type="text/css" href="/resources/css/user.css">

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
            <li role="presentation"><a href="assets">Assets.</a></li>
            <li role="presentation" class="active"><a href="users">Users.</a></li>
        </ul>
    </div>
    <div id="content" class="container">
        <h1>Edit User.</h1>
        <div align="left" class="col-md-4">
            <h3><b>User: </b>${user.username}</h3>
            <h5><b>Date Created: </b>${user.dateCreated}</h5>
        </div>
        <div class="col-md-2">
            <h3>Basic Details: </h3>
            <h5><b>First Name: </b>${user.fName}</h5>
            <h5><b>Last Name: </b>${user.sName}</h5>
            <h5><b>Current Access: </b>${user.type}</h5>
        </div>
        <div class="col-md-4">
            <div align="center" class="col-md-12">
                <a href="#" class="btn btn-warning btn-circle" type="button" data-toggle="modal" data-target="#accessModal">Change Access.</a>

            </div>
            <div align="center" class="col-md-12">
                <a href="#" class="btn btn-info btn-circle" type="button" data-toggle="modal" data-target="#passModal">Change Password.</a>
            </div>
            <div align="center" class="col-md-12">
                <c:if test="${userLogged != user.username}">
                <a href="/delUser?_id=${user._id}" class="btn btn-danger btn-circle" type="submit">Remove User.</a>
                </c:if>
            </div>
        </div>
    </div>
    <div class="container">
        <!-- Modal -->
        <div class="modal fade" id="accessModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">${user.username}:
                    </div>
                    <div class="modal-body">
                        <h5>Please select the access level you want to set this user as.</h5>
                        <form method="post" action="" command="updateType">
                            <select multiple class="form-control" id="inputType" name="updateType" style="width: 300px" style="height: 200px">
                                <option>Admin</option>
                                <option>Developer</option>
                                <option>frUser</option>
                                <option>readonly</option>
                            </select>
                            <hr>
                            <button type="submit" class="btn btn-info">Update.</button>
                        </form>
                    </div>
               </div>
        </div>

    </div>

        <!-- Modal -->
        <div class="modal fade" id="passModal" role="dialog">
            <div class="modal-dialog">

                <!-- Modal content-->
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">${user.username}:
                    </div>
                    <div class="modal-body">
                        <h5>Enter a password between 8 - 16 characters. </h5>
                        <form method="post" action="" command="updatePass">
                            <div class="input-group">
                                <label>Please enter a new password: </label>
                                <input type="password" class="form-control" name="updatePass" placeholder="Enter new password..." aria-describedby="basic-addon1">
                            </div>
                            <hr>
                            <button type="submit" class="btn btn-info">Update.</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    <hr>
    <p>Use <a href="../sticky-footer-navbar">links as so</a> if needed.</p>
</div>
    </div>

<footer class="footer">
    <div class="container">
        <p class="text-muted">Footer Content here.</p>
    </div>
</footer>

</body>
</html>
