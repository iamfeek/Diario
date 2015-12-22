var formModal = $('.cd-user-modal');
var formSignin = formModal.find('#cd-signin');
var loginBtn = formSignin.find('input[type="submit"]');

var usernameElement = $("#signin-username");
var passwordElement = $("#signin-password");

$(document).ready(function() {
    disableSubmitBtn();

    loginBtn.on('click', $.proxy(function(e) {
        e.preventDefault();
        sendChallenge();
    }, this));
});

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
    alert(response)
    var username = usernameElement.val();
    var password = passwordElement.val();
    var srpClient = new SRP6JavascriptClientSessionSHA256();

    var start = +(new Date());

    try{
        srpClient.step1(username, password);
    } catch(e){
        console.log("Error: " + e);
        window.location = window.location;
    }

    //var credentials = srpClient.step2(response.salt, response.b);
    alert(response.salt + " " + response.b);
}