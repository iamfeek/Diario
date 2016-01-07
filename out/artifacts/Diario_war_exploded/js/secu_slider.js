function onSecuSlider(value) {
    if (value == 100)   {
        document.getElementById('secu_text').innerHTML = 'A logo, your name, name of the viewer and heavy watermarking will be applied';
    }
    else if (value == 75)   {
        document.getElementById('secu_text').innerHTML = 'A small logo, your name, name of the viewer and light watermarking will be applied';
    }
    else if (value == 50)   {
        document.getElementById('secu_text').innerHTML = 'A small logo, your name and light watermarking will be applied';
    }
    else if (value == 25)   {
        document.getElementById('secu_text').innerHTML = 'A small logo and light watermarking will be applied';
    }
    else {
        document.getElementById('secu_text').innerHTML = 'Nothing will be applied';
    }
}