

<%--
  Created by NH
--%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<t:main title="Diario">
<jsp:attribute name="head_area">

        <title>${title}</title>

            <link href="../css/pages.css" rel="stylesheet">
    <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

    <%--all custom js/css for this page belongs here.--%>
</jsp:attribute>

    <jsp:attribute name="body_area">

<br>
<form action="/pageServlet" method="post">
    <div>
        <jsp:include page="date.jsp" flush="true" />
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
        <div class="container"><span class="h3 tab" >**************************************************Diary Contents********************************************</span></div>
<br>
    <div style="display: table;width: 100%;
	 	background: #fff;margin: 0;
	 	box-sizing: border-box;" class="container">

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
