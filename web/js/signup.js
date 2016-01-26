var registerBtn = $("#registerBtn");

var signUpUsernameElement = $("#username");
var signUpEmailElement = $("#email");
var signUpPasswordElement = $("#password");
var signUpPasswordSaltElement = $("#password-salt");
var signUpPasswordVerifierElement = $("#password-verifier");
var pubkeyElement = $("#pub_key");

$(document).ready(function(e) {
    $("#register-form").validator();
    $("#username").focus();
    $('#register-form').on('submit', function(e){
        e.preventDefault();
        registerNewKeyPair();
        disableSubmitBtn();
        postSaltAndVerifier();
    });
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
    var verifier =  srpClient.generateVerifier(salt, username, password);

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
            document.getElementById("create-success").className = "alert alert-success";
            setTimeout(function(){
                window.location.replace("/checkInstagram")
            },3000)

        } else {
            document.getElementById("create-failed").className = "alert alert-danger";
            $("#usernameFormGroup").addClass("has-error");
            $("#emailFormGroup").addClass("has-error");
            $("#username").val("");
            $("#email").val("");
            $("#password").val("");
            $("#cfmPassword").val("");
            $("#username").focus();
            enableSubmitBtn();
        }
    })
}