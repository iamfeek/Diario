/**
 * Created by Jy on 16-Jan-16.
 */
function registerNewKeyPair(username, passphrase)   {
    var keyPair = generateRSAKey();
    var pubKey = keyPair.exportKey("pkcs8-public-pem");
    var prvKey = keyPair.exportKey("pkcs8-private");
    document.getElementById('pub_key').value = pubKey;
    //store the keys
    localStorage.setItem(username + "_pubKey", pubKey);
    localStorage.setItem(username + "_prvKey", encryptKey(prvKey, passphrase));
    sessionStorage.setItem("username", username);
}

//decrypts keys and store into sessionStorage
function retrieveRSAKeyFromStore(username, passphrase)  {
    sessionStorage.setItem("pubKey", localStorage.getItem(username + "_pubKey"));
    sessionStorage.setItem("prvKey", decryptKey(localStorage.getItem(username + "_prvKey"), passphrase));
}

function decryptString(encrypted)    {
    if (checkForExistingKey())  {
        var keyPair = retrieveRSAKeyFromSession();
        return keyPair.decrypt(encrypted, 'utf-8');
    }
    else    {
        alert('Error: Key not found!');
    }
}

function encryptString(plaintext)   {
    if (checkForExistingKey())  {
        var keyPair = retrieveRSAKeyFromSession();
        return keyPair.encrypt(plaintext, 'base64');
    }
    else    {
        alert('Error: Key not found!');
    }
}

function downloadKey()  {
    var textToWrite = localStorage.getItem(sessionStorage.getItem("username") + "_prvKey") + "          " + localStorage.getItem(sessionStorage.getItem("username") + "_pubKey");
    var textFileAsBlob = new Blob([textToWrite], {type: 'text/plain'});
    var fileNameToSaveAs = "key"

    var downloadLink = document.createElement("a");
    downloadLink.download = fileNameToSaveAs;
    downloadLink.innerHTML = "Download File";
    if (window.webkitURL != null) {
        // Chrome allows the link to be clicked
        // without actually adding it to the DOM.
        downloadLink.href = window.webkitURL.createObjectURL(textFileAsBlob);
    }
    else {
        // Firefox requires the link to be added to the DOM
        // before it can be clicked.
        downloadLink.href = window.URL.createObjectURL(textFileAsBlob);
        downloadLink.onclick = destroyClickedElement;
        downloadLink.style.display = "none";
        document.body.appendChild(downloadLink);
    }

    downloadLink.click();
}

function destroyClickedElement(event) {
    document.body.removeChild(event.target);
}