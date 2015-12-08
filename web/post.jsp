<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

<jsp:attribute name="body_area">
    <script type="text/javascript" src="js/secu_slider.js"></script>
    <%--the content of the site goes here.--%>
    <div style="padding: 30px; margin-left: 100px">
        <h1 style="font-size: 30px;">New Post</h1>

        <div style="padding-top: 10px">Post a new post to your Diary!</div>
        <div style="padding-top: 30px">
            <textarea style="width: 500px; height: 300px"></textarea>
        </div>
        <div style="padding-top: 50px"><h1 style="font-size: 20px;">Security Level</h1>
            <input style="width: 400px; margin-left: 50px; margin-top: 30px" type="range" value="25" min="0" max="100" step="25" list="secu_levels" oninput="onSecuSlider(this.value)" onchange="onSecuSlider(this.value)">

            <datalist id="secu_levels">
                <option value="0">No Security</option>
                <option value="25">Minimal Security</option>
                <option value="50">Basic Security</option>
                <option value="75">Moderate Security</option>
                <option value="100">Strong Security</option>
            </datalist>
            <br/>
            <table style="width: 500px;">
                <tr>
                    <td align="center" style="width: 100px">None</td>
                    <td align="center" style="width: 100px">Minimal</td>
                    <td align="center" style="width: 100px">Basic</td>
                    <td align="center" style="width: 100px">Moderate</td>
                    <td align="center" style="width: 100px">Strong</td>
                </tr>
            </table>
            <div style="text-align: center; width: 500px; padding-top: 20px" id="secu_text">A small logo and light watermarking will be applied</div>

            <div style="text-align: center; width: 500px; padding-top: 20px"><button style="width: 100px; height: 40px;">Post</button></div>
        </div>
    </div>
</jsp:attribute>
</t:layout>