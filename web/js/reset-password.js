/**
 * Created by IamFeeK on 25/1/2016.
 */
var loginBtn = $("#resetBtn");

var usernameElement = $("#username");
var emailElement = $("#email");

$(document).ready(function() {
    loginBtn.on('click', $.proxy(function(e) {
        e.preventDefault();

        sendResetRequest();
    }, this));
});

function sendResetRequest(){
    var username = usernameElement.val();
    var email = emailElement.val();

    var postValues = {
        stage: 1,
        username: username,
        email: email
    };

    var posting = $.post("/reset", postValues);
    posting.done(function(data){
        document.getElementById("email-sent").className = "alert alert-info";
        setTimeout(function(){
            window.location.replace("/signin")
        }, 5500)
    });
}