<!DOCTYPE html>
<%@tag description="Main Template" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="head_area" fragment="true" %>
<%@attribute name="body_area" fragment="true" %>

<html>
<head>
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
            window.location.href = '/home';
        }
    </script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link href='http://fonts.googleapis.com/css?family=PT+Sans:400,700' rel='stylesheet' type='text/css'>

    <link href="../css/reset.css" rel="stylesheet">
    <link href="../css/loginModal.css" rel="stylesheet">
    <script src="../js/modernizr.js"></script> <!-- Modernizr -->

    <title>${title}</title>

    <jsp:invoke fragment="head_area"/>
</head>
<body>
<header role="banner">
    <div id="cd-logo"><a href="#0"><img src="../img/cd-logo.svg" alt="Logo"></a></div>

    <nav class="main-nav">
        <ul>
            <li><a class="cd-signin" href="#0">Sign in</a></li>
            <li><a class="cd-signup" href="#0">Sign up</a></li>
        </ul>
    </nav>
</header>
<div class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
    <div class="cd-user-modal-container"> <!-- this is the container wrapper -->
        <ul class="cd-switcher">
            <li><a href="#0">Sign in</a></li>
            <li><a href="#0">New account</a></li>
        </ul>

        <div id="cd-login"> <!-- log in form -->
            <form class="cd-form" action="/auth" method="POST">

                <p class="fieldset">
                    <label class="image-replace cd-username" for="signin-email">Username</label>
                    <input class="full-width has-padding has-border" name="username" id="signin-email" type="text" placeholder="Username">
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-password" for="signin-password">Password</label>
                    <input class="full-width has-padding has-border" name="password" id="signin-password" type="text"  placeholder="Password">
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
        </div> <!-- cd-login -->

        <div id="cd-signup"> <!-- sign up form -->
            <form class="cd-form">
                <input name="flag" type="hidden" value="register">
                <p class="fieldset">
                    <label class="image-replace cd-username" for="signup-username">Username</label>
                    <input class="full-width has-padding has-border" id="signup-username" name="username" type="text" placeholder="Username">
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-email" for="signup-email">E-mail</label>
                    <input class="full-width has-padding has-border" id="signup-email" name="email" type="email" placeholder="E-mail">
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <label class="image-replace cd-password" for="signup-password">Password</label>
                    <input class="full-width has-padding has-border" id="signup-password" name="password" type="text"  placeholder="Password">
                    <a href="#0" class="hide-password">Hide</a>
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <input type="checkbox" id="accept-terms">
                    <label for="accept-terms">I agree to the <a href="#0">Terms</a></label>
                </p>

                <p class="fieldset">
                    <input class="full-width has-padding" type="submit" value="Create account">
                </p>
            </form>

            <!-- <a href="#0" class="cd-close-form">Close</a> -->
        </div> <!-- cd-signup -->

        <div id="cd-reset-password"> <!-- reset password form -->
            <p class="cd-form-message">Lost your password? Please enter your email address. You will receive a link to create a new password.</p>

            <form class="cd-form">
                <p class="fieldset">
                    <label class="image-replace cd-email" for="reset-email">E-mail</label>
                    <input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
                    <span class="cd-error-message">Error message here!</span>
                </p>

                <p class="fieldset">
                    <input class="full-width has-padding" type="submit" value="Reset password">
                </p>
            </form>

            <p class="cd-form-bottom-message"><a href="#0">Back to log-in</a></p>
        </div> <!-- cd-reset-password -->
        <a href="#0" class="cd-close-form">Close</a>
    </div> <!-- cd-user-modal-container -->
</div>

<jsp:invoke fragment="body_area"/>


<script src="https://code.jquery.com/jquery-2.1.4.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js" integrity="sha256-Sk3nkD6mLTMOF0EOpNtsIry+s1CsaqQC1rVLTAy+0yc= sha512-K1qjQ+NcF2TYO/eI3M6v8EiNYZfA95pQumfvcVrTHtwQVDG+aHRqLi/ETn2uB+1JqwYqVG3LIvdm9lj6imS/pQ==" crossorigin="anonymous"></script>
<script src="../js/login.js"></script>


</body>
</html>