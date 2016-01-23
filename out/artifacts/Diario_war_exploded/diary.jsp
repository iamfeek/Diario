

<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 12/6/2015
  Time: 9:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main title="Diario">
<jsp:attribute name="head_area">

        <title>${title}</title>

            <link href="../css/pages.css" rel="stylesheet">

    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

    <jsp:attribute name="body_area">

<br>
<form action="/pageServlet" method="post">
    <div>
        <jsp:include page="scriptlet.jsp" flush="true" />
    <br>
    <p>
    <label class="andy">Enter Title:</label>
        <input name="title" id="diary-title" type="text" class="pg andy">
    </p>
    </div>
    <div class='pg outer'>
    <textarea class='inner andy' contenteditable='true' name="content" id="story" type="text"></textarea>
    </div>
    <p class="pg">
    <input class="andy" type="submit" id="submit" name="post" value="Continue">
    </p>
</form>
<br>
<br>
        <div style="display: block;width: 100%;
	 	background: #64e0ef;height: 55px;
	 	padding-left: 10px;color: #fff;
	 	font-size: 20px;line-height: 55px;
	 	text-shadow: 1px 1px 1px rgba(0,0,0,.3);
	 	box-sizing: border-box;">Diary Contents</div>

    <div style="display: table;width: 100%;
	 	background: #fff;margin: 0;
	 	box-sizing: border-box;">

        <div style="background: #2f889a;color: #fff; display: table-row;">
        <span style="display: table-cell;padding: 6px;
		border-bottom: 1px solid #e5e5e5;
		text-align: center;text-align: left;">Date</span>
        <span style="display: table-cell;padding: 6px;
		border-bottom: 1px solid #e5e5e5;
		text-align: center;">Title</span>
        <span style="display: table-cell;padding: 6px;
		border-bottom: 1px solid #e5e5e5;
		text-align: center;">Content</span>
        </div>

         <jsp:include page="retriever.jsp" flush="true" />

    </div>
        <br>
        <br>
</jsp:attribute>
</t:main>
