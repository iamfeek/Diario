/**
 * Created by Jy on 17-Jan-16.
 */
function decryptAES(encryptedText, base64Key) {
    var key = CryptoJS.enc.Base64.parse(base64Key);
    var decryptedData = CryptoJS.AES.decrypt( encryptedText, key, {
        mode: CryptoJS.mode.ECB,
        padding: CryptoJS.pad.Pkcs7
    });
    var decryptedText = decryptedData.toString( CryptoJS.enc.Utf8 );
    return decryptedText;
}