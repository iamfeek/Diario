var formModal = $('.cd-user-modal');
var formSignup = formModal.find('#cd-signup');
var registerBtn = formSignup.find('input[type="submit"]');

var usernameElement = $("#signup-username");
var emailElement = $("#signup-email");
var passwordElement = $("#signup-password");
var passwordSaltElement = $("#password-salt");
var passwordVerifierElement = $("#password-verifier");

$(document).ready(function() {
    disableSubmitBtn();

    registerBtn.on('click', $.proxy(function(e) {
        e.preventDefault();
        postSaltAndVerifier();
    }, this));

    emailElement.on('keyup', $.proxy(function(event) {
        // see recommendation in the thinbus docs
        random16byteHex.advance(Math.floor(event.keyCode / 4));
    }, this));

    passwordElement.on('keyup', $.proxy(function(event) {
            // only enable the button if the user has entered some password
            //ternary operator. test ? trueExpression : falseExpression
            $(event.currentTarget).val().length ? enableSubmitBtn()
                : disableSubmitBtn();
            // see recommendation in the thinbus docs
            random16byteHex.advance(Math.floor(event.keyCode / 4));
        }, this));
});

var disableSubmitBtn = function () {
    registerBtn.attr("disabled", true);
}

var enableSubmitBtn = function () {
    registerBtn.removeAttr("disabled");
}

var postSaltAndVerifier = function(){
    var email = emailElement.val();
    var username = usernameElement.val();
    var password = passwordElement.val();

    var srpClient = new SRP6JavascriptClientSessionSHA256();
    var salt = srpClient.generateRandomSalt();
    var verifier =  srpClient.generateVerifier(salt, email, password);

    passwordSaltElement.attr('value', salt);
    passwordVerifierElement.attr('value', verifier);

    var postValues = {
        username: username,
        email: email,
        salt: salt,
        verifier: verifier
    }

    $.post("/register", postValues, function(response){
        console.log(response);
    })
}