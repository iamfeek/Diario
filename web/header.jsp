<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<head>
    <title>Diario</title>
    <script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
          integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
          integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
            integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

</head>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/dashboard">Diario</a>
        </div>
        <ul class="nav navbar-nav navbar-left">
            <li class="${param.dashboard}"><a href="/dashboard">Dashboard</a></li>
            <li class="${param.profile}"><a href="/profile">Profile</a></li>
            <li class="${param.post}"><a href="/post.jsp">Post</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <input type="button" class="btn btn-default navbar-btn" onclick="location.href='/signout';"
                   value="Sign Out"/>

            <p class="navbar-text">Signed in as <a href="/profile" class="navbar-link">${sessionScope.username}</a></p>
        </ul>
    </div>
</nav>
