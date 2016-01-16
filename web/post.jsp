<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

<jsp:attribute name="body_area">
    <script type="text/javascript" src="js/secu_slider.js"></script>
    <%--the content of the site goes here.--%>
    <form action="/post" method="post">
        <div style="padding: 30px; margin-left: 100px">
            <h1 style="font-size: 30px;">New Post</h1>

            <div style="padding-top: 10px">Post a new post to your Diary!</div>
            <div style="padding-top: 20px">
                <textarea name="text" style="width: 500px; height: 300px"></textarea>
            </div>
            <div style="padding-top: 30px">
                <h1 style="font-size: 20px;">Sharing Options</h1><br/>
                <input name="text_secu_level" style="width: 400px; margin-left: 50px; margin-top: 20px" type="range"
                       value="0" min="0" max="100" step="50" list="text_secu_levels" oninput="onTextSlider(this.value)"
                       onchange="onTextSlider(this.value)">

                <datalist id="text_secu_levels">
                    <option value="0">Everyone</option>
                    <option value="50">Just Me</option>
                    <option value="100">Share with Selected Friends</option>
                </datalist>
                <br/>
                <table style="width: 500px;">
                    <tr>
                        <td align="center" style="width: 166px">Everyone</td>
                        <td align="center" style="width: 166px">Just Me</td>
                        <td align="center" style="width: 166px">Selected Friends</td>
                    </tr>
                </table>
                <div style="text-align: center; width: 500px; padding-top: 20px; padding-bottom: 20px;" id="text_secu_text">Allow anyone to view this post (leave unencrypted)</div>
                <div style="text-align: center; width: 500px; padding-top: 20px">
                    <button style="width: 100px; height: 40px;">Post</button>
                </div>
            </div>
        </div>
    </form>
</jsp:attribute>
</t:layout>