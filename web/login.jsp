<%--
  Created by IntelliJ IDEA.
  User: IamFeeK
  Date: 7/12/15
  Time: 16:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Diario | Login</title>
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

        var user = getCookie("User");
        if(user != ""){
            //window.location.href = '/dashboard';
        }
    </script>
</head>
<body>
<div class="cd-user-modal is-visible">
    <div class="cd-user-modal-container">
        <div id="cd-login">

            <form class="cd-form" action="/auth" method="POST">
                <h1 style="text-align: center; font-size: 32px;">Welcome to Diario!</h1>
                <input name="flag" type="hidden" value="login">

                <p class="fieldset">
                    <label class="image-replace cd-username" for="signin-email">Username</label>
                    <input class="full-width has-padding has-border" name="username" id="signin-email" type="text"
                           placeholder="Username">
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-password" for="signin-password">Password</label>
                    <input class="full-width has-padding has-border" name="password" id="signin-password" type="text"
                           placeholder="Password">
                    <a href="#0" class="hide-password">Hide</a>
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <input type="checkbox" id="remember-me" checked>
                    <label for="remember-me">Remember me</label>
                </p>
                <input name="flag" type="hidden" value="login">

                <p class="fieldset">
                    <input class="full-width" type="submit" id="btnLogin" value="Login">
                </p>
            </form>

            <p class="cd-form-bottom-message"><a href="#0">Forgot your password?</a></p>
            <%--<a href="#0" class="cd-close-form">Close</a>--%>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"
        integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ=="
        crossorigin="anonymous"></script>
<script src="../js/login.js"></script>
<script>
    var formModal = $('.cd-user-modal'),
            formLogin = formModal.find('#cd-login'),
            formSignup = formModal.find('#cd-signup'),
            formForgotPassword = formModal.find('#cd-reset-password'),
            formModalTab = $('.cd-switcher'),
            tabLogin = formModalTab.children('li').eq(0).children('a'),
            tabSignup = formModalTab.children('li').eq(1).children('a'),
            forgotPasswordLink = formLogin.find('.cd-form-bottom-message a'),
            backToLoginLink = formForgotPassword.find('.cd-form-bottom-message a'),
            mainNav = $('.main-nav');

    mainNav.children('ul').removeClass('is-visible');
    formModal.addClass('is-visible');
    formLogin.addClass('is-selected');
    formSignup.removeClass('is-selected');
    formForgotPassword.removeClass('is-selected');
    tabLogin.addClass('selected');
    tabSignup.removeClass('selected');
</script>
</body>
</html>
