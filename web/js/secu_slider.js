function onImgSlider(value) {
    if (value == 100)   {
        document.getElementById('img_secu_text').innerHTML = 'A logo, your name, name of the viewer and heavy watermarking will be applied';
    }
    else if (value == 75)   {
        document.getElementById('img_secu_text').innerHTML = 'A small logo, your name, name of the viewer and light watermarking will be applied';
    }
    else if (value == 50)   {
        document.getElementById('img_secu_text').innerHTML = 'A small logo, your name and light watermarking will be applied';
    }
    else if (value == 25)   {
        document.getElementById('img_secu_text').innerHTML = 'A small logo and light watermarking will be applied';
    }
    else {
        document.getElementById('img_secu_text').innerHTML = 'Nothing will be applied';
    }
}

function onTextSlider(value)    {
    if (value == 100)   {
        document.getElementById('text_secu_text').innerHTML = "Share with selected friends (Add them later)";
    }
    else if (value == 50)  {
        document.getElementById('text_secu_text').innerHTML = "Only allow yourself to view this post";
    }
    else    {
        document.getElementById('text_secu_text').innerHTML = "Allow anyone to view this post (leave unencrypted)";
    }
}