<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario | Login">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
    <link href="css/login.css" rel="stylesheet">
</jsp:attribute>

<jsp:attribute name="body_area">
    <%--the content of the site goes here.--%>
    <div class="container">
        <h2 class="form-signin-heading">Login</h2>
        <form class="form-signin">
            <label for="inputEmail" class="sr-only">Email address</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
            <label for="inputPassword" class="sr-only">Password</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Password" required>
            <div class="checkbox">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        </form>
    </div>

    <%--script to set navbar element to active.--%>
    <script>
        var loginNav = document.getElementById('loginNav');
        loginNav.className('active');
    </script>
</jsp:attribute>

</t:layout>