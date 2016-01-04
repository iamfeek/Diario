<%--Sign up Form--%>
<div id="cd-signup"> <!-- sign up form -->
    <form class="cd-form" id="register-form" method="post">
        <input id="password-salt" type="hidden" name="salt" />
        <input id="password-verifier" type="hidden" name="verifier" />

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
            <input class="full-width has-padding" type="submit" id="registerBtn" value="Create account">
        </p>
    </form>

    <!-- <a href="#0" class="cd-close-form">Close</a> -->
</div>

