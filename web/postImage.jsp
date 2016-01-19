<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:layout title="Diario">

<jsp:attribute name="head_area">
    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

<jsp:attribute name="body_area">
    <script type="text/javascript" src="js/secu_slider.js"></script>
    <%--the content of the site goes here.--%>
    <form action="/postImage" method="post" enctype="multipart/form-data">
        <div style="padding: 30px; margin-left: 100px">
            <h1 style="font-size: 30px;">Upload Image</h1><br/>
            <input type="file" name="photo" size="50"/>
            <div style="padding-top: 20px">
                <h1 style="font-size:18px">Image Security</h1>

                <input name="img_secu_level" style="width: 400px; margin-left: 50px; margin-top: 30px" type="range"
                       value="25" min="0" max="100" step="25" list="img_secu_levels" oninput="onImgSlider(this.value)"
                       onchange="onImgSlider(this.value)">

                <datalist id="img_secu_levels">
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
                <div style="text-align: center; width: 500px; padding-top: 20px" id="img_secu_text">A small logo and light
                    watermarking will be applied
                </div>

                <div style="text-align: center; width: 500px; padding-top: 20px">
                    <input type="submit" style="width: 100px; height: 40px;" value="Post"/>
                </div>
            </div>
        </div>
    </form>
</jsp:attribute>
</t:layout>