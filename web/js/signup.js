var formModal = $('.cd-user-modal');
var formSignup = formModal.find('#cd-signup');
var registerBtn = formSignup.find('input[type="submit"]');

var signUpUsernameElement = $("#signup-username");
var signUpEmailElement = $("#signup-email");
var signUpPasswordElement = $("#signup-password");
var signUpPasswordSaltElement = $("#password-salt");
var signUpPasswordVerifierElement = $("#password-verifier");
var pubkeyElement = $("#pub_key");

$(document).ready(function() {
    disableSubmitBtn();
    registerBtn.prop("value", "Logging in...");
    registerBtn.on('click', $.proxy(function(e) {
        e.preventDefault();
        disableSubmitBtn();
        postSaltAndVerifier();
    }, this));

    signUpEmailElement.on('keyup', $.proxy(function(event) {
        // see recommendation in the thinbus docs
        random16byteHex.advance(Math.floor(event.keyCode / 4));
    }, this));

    signUpPasswordElement.on('keyup', $.proxy(function(event) {
            // only enable the button if the user has entered some password
            //ternary operator. test ? trueExpression : falseExpression
            $(event.currentTarget).val().length ? enableSubmitBtn() : disableSubmitBtn();
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
    var email = signUpEmailElement.val();
    var username = signUpUsernameElement.val();
    var password = signUpPasswordElement.val();
    var pubkey = pubkeyElement.val();

    console.log(email);
    console.log(username);
    console.log(password);

    var srpClient = new SRP6JavascriptClientSessionSHA256();
    var salt = srpClient.generateRandomSalt();
    var verifier =  srpClient.generateVerifier(salt, email, password);

    signUpPasswordSaltElement.attr('value', salt);
    signUpPasswordVerifierElement.attr('value', verifier);

    var postValues = {
        username: username,
        email: email,
        salt: salt,
        verifier: verifier,
        pubkey: pubkey
    }

    $.post("/register", postValues, function(response){
        console.log(response);
    })
}