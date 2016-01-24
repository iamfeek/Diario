<%--
  Created by IntelliJ IDEA.
  User: IamFeeK
  Date: 25/1/2016
  Time: 12:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diario</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/">Diario</a>
        </div>
        <ul class="nav navbar-nav navbar-right">
            <li class="active"><a href="/signin">Sign In</a></li>
            <li class=""><a href="/signup">Create New Account</a></li>
        </ul>
    </div>
</nav>
<div class="container" style="margin-top: 60px;">
    <div class="row">
        <div class="page-header">
            <h1>Just A Few More Steps<h1>
        </div>
        <form id="reset-password-now-form" role="form" data-toggle="validator" id="reset-form" method="post">
            <div id="email-sent" class="hidden alert alert-info" role="alert">
                <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <strong>Success!</strong> We sent you an email. Please check and follow the instructions there.
            </div>
            <div class="form-group has-feedback">
                <label for="username">Username</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                    <input type="username" class="form-control" id="username" placeholder="Username" value="${param.username}" readonly>
                </div>
            </div>
            <div class="form-group has-feedback">
                <label for="email">Email Address</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                    <input type="username" class="form-control" id="email" placeholder="Email" value="${param.email}" readonly>
                </div>
            </div>
            <div id="keyDiv" class="form-group has-feedback">
                <label for="key">Key</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-key"></i></div>
                    <input type="text" class="form-control" id="key" placeholder="Key" value="${param.key}" required>
                </div>
            </div>

            <div class="form-group has-feedback">
                <label for="password">Password</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-key"></i></div>
                    <input type="password" data-minlength="6" class="form-control" id="password" placeholder="Password" required>
                </div>
            </div>

            <div class="form-group has-feedback">
                <label for="cfmPassword">Confirm Password</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-key"></i></div>
                    <input type="password" class="form-control" id="cfmPassword" data-match="#password" data-match-error="Whoops! Passwords dont match" placeholder="Confirm Password" required>
                    <div class="help-block with-errors"></div>
                </div>
            </div>
            <button type="submit" class="btn btn-default" id="resetBtn">Reset My Password</button><br><br>
        </form>
    </div>
</div>

</body>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A==" crossorigin="anonymous"></script>
<script src="js/validator.js"></script>
<script src="js/rfc5054-safe-prime-config.js"></script>
<script src="js/thinbus-srpclient-sha256.js"></script>
<script src="js/reset-password-now.js"></script>
</html>

<%--Sign up Form--%>


