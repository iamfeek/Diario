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
            <li class=""><a href="/signin">Sign In</a></li>
            <li class="active"><a href="/signup">Create New Account</a></li>
        </ul>
    </div>
</nav>
<div class="container" style="margin-top: 60px;">
    <div class="row" id="cd-signup">
        <div class="page-header">
            <h1>Create A New Account</h1>
        </div>
        <form class="cd-form" id="register-form" method="post">
            <input id="password-salt" type="hidden" name="salt" />
            <input id="password-verifier" type="hidden" name="verifier" />
            <input type="hidden" id="pub_key" name="pubkey"/>

            <div class="form-group has-feedback">
                <label for="username">Username</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-user"></i></div>
                    <input type="username" class="form-control" id="username" placeholder="Username">
                </div>
            </div>
            <div class="form-group has-feedback">
                <label for="email">Email address</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-envelope-o"></i></div>
                    <input type="email" class="form-control" id="email" placeholder="Email">
                </div>
            </div>
            <div class="form-group has-feedback">
                <label for="password">Password</label>
                <div class="input-group">
                    <div class="input-group-addon"><i class="fa fa-key"></i></div>
                    <input type="password" class="form-control" id="password" placeholder="Password">
                </div>
            </div>
            <div class="checkbox">
                <label>
                    <input type="checkbox"> I agree with the Terms and Conditions
                </label>
            </div>
            <button type="submit" class="btn btn-default" id="registerBtn">Create My Account</button><a href="/signin"> I have an account!</a>
        </form>
    </div>
</div>
</body>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha256-KXn5puMvxCw+dAYznun+drMdG1IFl3agK0p/pqT9KAo= sha512-2e8qq0ETcfWRI4HJBzQiA3UoyFk6tbNyG+qSaIBZLyW9Xf3sWZHN/lxe9fTh1U45DpPf07yj94KsUHHWe4Yk1A==" crossorigin="anonymous"></script>
<script src="js/rfc5054-safe-prime-config.js"></script>
<script src="js/thinbus-srpclient-sha256.js"></script>
<script src="js/signup.js"></script>
<script type="text/javascript" src="js/security/key-handler.js"></script>
<script type="text/javascript" src="js/security/rsa-bundle.js"></script>
</html>

<%--Sign up Form--%>


