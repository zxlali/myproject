<!DOCTYPE html>
<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title></title>
    <link rel="stylesheet" href="../css/plugins/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="../css/plugins/bootstrap/css/bootstrap-theme.css">
    <link rel="stylesheet" href="../css/plugins/select2/select2.css">
    <link rel="stylesheet" href="../css/plugins/select2/select2-bootstrap.css">
    <link rel="stylesheet" href="../css/plugins/iCheck/all.css">
    <link rel="stylesheet" href="../css/plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../css/plugins/bootstrap-datepicker/datepicker3.css">
    <link rel="stylesheet" href="../css/plugins/bootstrap-datetimepicker/bootstrap-datetimepicker.css">
    <link rel="stylesheet" href="../css/plugins/bootstrap-editable/css/bootstrap-editable.css">
    <link rel="stylesheet" href="../css/apsara-admin.css">
    <script data-main="../app/main.js" src="../js/require.js"></script>
</head>
<body>
<div id="wrapper">
    <!-- Sidebar -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <span class="navbar-title" >Admin</span>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav navbar-right navbar-user">
                <li class="dropdown user-dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown"><span
                            class="glyphicon glyphicon-user"></span> 您好, ${userName} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li><a href="../logout"><span class="glyphicon glyphicon-log-out"></span> Log Out</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav side-nav" ng-controller="sidebarCtrl">
                <%--<sec:authorize access="hasAuthority('ADMIN')">--%>
                    <li>
                        <a href ng-click="redirectTo('/flot')"><span class="glyphicon glyphicon-dashboard"></span>flot
                        </a>
                    </li>
                     <li>
                        <a href ng-click="redirectTo('/pie')"><span class="glyphicon glyphicon-dashboard"></span>pie
                        </a>
                    </li>
                    <li>
                        <a href ng-click="redirectTo('/user')"><span class="glyphicon glyphicon-dashboard"></span>user
                        </a>
                    </li>
                <%--</sec:authorize>--%>
                <%--<sec:authorize access="hasAuthority('USER')">--%>
                    <li>
                        <a href ng-click="redirectTo('/scroll')"><span class="glyphicon glyphicon-dashboard"></span>scroll
                        </a>
                    </li>
                 <%--</sec:authorize>--%>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </nav>
    <div id="page-wrapper" ng-view></div>
</div>
</body>
</html>
