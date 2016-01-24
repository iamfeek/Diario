var keyInput = $("#key");
var resetBtn = $("#resetBtn");
$( document ).ready(function() {
    keyInput.val() != "" ? $("#password").focus() : keyInput.focus();

    $("#reset-password-now-form").validator();

    resetBtn.on('click', $.proxy(function(e) {
        e.preventDefault();

        doReset();
    }, this));
});

function doReset(){
    alert("Do Reset")
    var srpClient = new SRP6JavascriptClientSessionSHA256();
    var password = $("#password").val();
    var username = $("#username").val();
    var email = $("#email").val();
    var salt = srpClient.generateRandomSalt();
    var verifier =  srpClient.generateVerifier(salt, username, password);
    var key = keyInput.val();


    var postValues = {
        stage: 2,
        username: username,
        email: email,
        salt: salt,
        verifier: verifier,
        key: key
    }

    console.log(postValues)

    $.post("/reset-now", postValues, function(response){
        if(response === "true"){
            document.getElementById("email-sent").className = "alert alert-info";
            //document.getElementById("keyDiv").className = "form-group";
        }
    });


}
