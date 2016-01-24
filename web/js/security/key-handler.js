/**
 * Created by Jy on 16-Jan-16.
 */
function registerNewKeyPair()   {
    var keyPair = generateRSAKeyAndStore();
    document.getElementById('pub_key').value = keyPair.exportKey("pkcs8-public-pem");
}

function decryptString(encrypted)    {
    if (checkForExistingKey())  {
        var keyPair = retrieveRSAKeyFromStore();
        return keyPair.decrypt(encrypted, 'utf-8');
    }
    else    {
        alert('Error: Key not found!');
    }
}

function encryptString(plaintext)   {
    if (checkForExistingKey())  {
        var keyPair = retrieveRSAKeyFromStore();
        return keyPair.encrypt(plaintext, 'base64');
    }
    else    {
        alert('Error: Key not found!');
    }
}