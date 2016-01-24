var registerBtn = $("#registerBtn");

var signUpUsernameElement = $("#username");
var signUpEmailElement = $("#email");
var signUpPasswordElement = $("#password");
var signUpPasswordSaltElement = $("#password-salt");
var signUpPasswordVerifierElement = $("#password-verifier");
var pubkeyElement = $("#pub_key");

$(document).ready(function(e) {
    $('#register-form').on('submit', function(e){
        e.preventDefault();
        registerNewKeyPair();
        disableSubmitBtn();
        postSaltAndVerifier();
    });
//    disableSubmitBtn();
//    registerBtn.prop("value", "Logging in...");
//    registerBtn.on('click', $.proxy(function(e) {
//        e.preventDefault();
//        alert("CLICKED")
//        //disableSubmitBtn();
//        //postSaltAndVerifier();
//    }, this));
//
//    signUpEmailElement.on('keyup', $.proxy(function(event) {
//        // see recommendation in the thinbus docs
//        random16byteHex.advance(Math.floor(event.keyCode / 4));
//    }, this));
//
//    signUpPasswordElement.on('keyup', $.proxy(function(event) {
//            // only enable the button if the user has entered some password
//            //ternary operator. test ? trueExpression : falseExpression
//            $(event.currentTarget).val().length ? enableSubmitBtn() : disableSubmitBtn();
//            // see recommendation in the thinbus docs
//            random16byteHex.advance(Math.floor(event.keyCode / 4));
//        }, this));
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
        if(response === "done"){
            window.location.replace("/signin.do")
        }
    })
}