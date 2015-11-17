<!DOCTYPE html>
<%@tag description="Main Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

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

    <nav class="main-nav">
        <ul>
            <li><a class="cd-signup" href="" id="profile_name">Profile</a></li>
        </ul>
    </nav>
    <script>
        var x = getCookie("User");
        var profile = document.getElementById("profile_name");
        profile.innerHTML = "Welcome, "+x+"!";
    </script>
</header>
<jsp:invoke fragment="body_area"/>


<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
<script src="../js/login.js"></script>
</body>
</html>