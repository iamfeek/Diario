<%@ page import="diary.pageController" %>
<%@ page import="DAO.pages" %>
<%@ page import="java.util.List" %>

<%
    pageController pc = new pageController();
    List<pages> list = pc.getPages();
    for (pages pg : list) {
%>
<div style="display: table-row;">
            <span style="display: table-cell;padding: 6px;
		border-bottom: 1px solid #e5e5e5;
		text-align: center; text-align: left;">
        <%= pg.getDate()%>
                </span>

            <span style="display: table-cell;padding: 6px;
		border-bottom: 1px solid #e5e5e5;
		text-align: center;">
        <%= pg.getTitle()%>
                </span>

            <span style="display: table-cell;padding: 6px;
		border-bottom: 1px solid #e5e5e5;
		text-align: center;">
        <%= pg.getContent()%>
                </span>
</div>
    <%
        }
    %>





