<!DOCTYPE html>
<%@tag description="Main Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>


    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/loginModal.css" rel="stylesheet">
    <script src="../js/modernizr.js"></script> <!-- Modernizr -->
    <script>
        function getCookie(cname) {
            var name = cname + "=";
            var ca = document.cookie.split(';');
            for(var i=0; i<ca.length; i++) {
                var c = ca[i];
                while (c.charAt(0)==' ') c = c.substring(1);
                if (c.indexOf(name) == 0) return c.substring(name.length, c.length);
            }
            return "";
        }
    </script>

    <title>${title}</title>

    <jsp:invoke fragment="head_area"/>
</head>
<body>
<header role="banner">
    <div id="cd-logo"><a href="/home"><img src="../img/cd-logo.svg" alt="Logo"></a></div>

    <div style=" height: 100%; border: 1px solid;width: 500px; text-align: right; float: right;">
        <a style="color: #fff; font-weight: 100; font-size: 165%; margin-top: 25px;" id="profile_name"></a>
        <a style="color: #fff;" href="" style="background-color: transparent;">
            <i class="fa fa-sign-out fa-2x"></i>
        </a>
    </div>
    <script>
        var x = getCookie("User");
        var profile = document.getElementById("profile_name");
        profile.innerHTML += "Hey, <u>"+x+"</u>!";
    </script>
</header>
<jsp:invoke fragment="body_area"/>


<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="../js/login.js"></script>
</body>
</html>