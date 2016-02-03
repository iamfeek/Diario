<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Diario</title>
    <script src="js/jquery.js"></script>
    <link rel="stylesheet" href="css/bootstrap.css">

    <link rel="stylesheet" href="css/bootstrap-theme.css">

    <script src="js/bootstrap.js"></script>
    <link rel="stylesheet" href="css/font-awesome.css">

</head>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/dashboard">Diario</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li class="${param.dashboard}"><a href="/dashboard">Dashboard</a></li>
            <li class="${param.profile}"><a href="/view?view=${sessionScope.userid}">Profile</a></li>
            <li class="${param.post}"><a href="/post.jsp">Post</a></li>

        </ul>
        <ul class="nav navbar-nav navbar-right" style="margin-right: 5px;">
            <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/signout';"
                   value="Sign Out"/>

            <p class="navbar-text">Signed in as <a href="/profile" class="navbar-link">${sessionScope.username}</a></p>
        </ul>
    </div>
</nav>
