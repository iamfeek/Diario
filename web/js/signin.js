var loginBtn = $("#loginBtn");

var usernameElement = $("#username");
var passwordElement = $("#password");

$(document).ready(function() {
    disableSubmitBtn();
    loginBtn.on('click', $.proxy(function(e) {
        e.preventDefault();
        sendChallenge();
    }, this));
});

usernameElement.on('keyup', $.proxy(function(event) {
    // see recommendation in the thinbus docs
    random16byteHex.advance(Math.floor(event.keyCode / 4));
}, this));

passwordElement.on('keyup', $.proxy(function(event){
    $(event.currentTarget).val().length ? enableSubmitBtn() : disableSubmitBtn();

    random16byteHex.advance(Math.floor(event.keyCode / 4));
}, this))

var disableSubmitBtn = function () {
    loginBtn.attr("disabled", true);
}

var enableSubmitBtn = function () {
    loginBtn.removeAttr("disabled");
}

var sendChallenge = function(){
    var username = usernameElement.val();

    var postValues = {
        username: username
    }

    $.post("/challenge", postValues, function(response){
        challengeResponse(response);
    });
}

function challengeResponse(response){
    //var saltAndB = JSON.parse(response.saltAndB);
    //alert(response)
    var username = usernameElement.val();
    var password = passwordElement.val();
    var srpClient = new SRP6JavascriptClientSessionSHA256();

    try{
        srpClient.step1(username, password);
        console.log("Step 1: COMPLETED")
    } catch(e){
        console.log("Error: " + e);
        window.location = window.location;
    }

    var credentials = null;
    var saltAndB = JSON.parse(response);

    var salt = saltAndB.salt;
    var b = saltAndB.b;
    try{
        console.log("Step 2 Values")
        console.log("Salt: " + salt)
        console.log("B: " + b )
        credentials = srpClient.step2(salt, b);
        console.log("Step 2: COMPLETED")

    } catch(e){
        console.log("Step 2 " + e);
    }

    var values = {
        username: username,
        M1: credentials.M1,
        A: credentials.A
    };

    console.log("A: " + values.A)
    console.log("M1: " + values.M1)


    $.post("/authenticate", values, function(response){
        authenticateResponse(response, srpClient);
    });
}

function authenticateResponse(response, srpClient){
    console.log(response)
    if(response == "bad"){
        document.getElementById("bad-credentials").className = "alert alert-danger alert-dismissible";
        setTimeout(function(){document.getElementById("bad-credentials").className = "hidden alert alert-danger alert-dismissible";}, 5000)
    } else {
        srpClient.step3(response);
        alert("Step 3: " + response)
    }
}