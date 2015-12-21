var formModal = $('.cd-user-modal');
var formSignin = formModal.find('#cd-signin');
var loginBtn = formSignin.find('input[type="submit"]');

var usernameElement = $("#signin-username");
var passwordElement = $("#signin-password");

$(document).ready(function() {
    disableSubmitBtn();

    loginBtn.on('click', $.proxy(function(e) {
        console.log("CLIIIICKED")
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
        console.log(response);
    });
}