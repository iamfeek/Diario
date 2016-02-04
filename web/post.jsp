<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.loggedIn}">
    </c:when>
    <c:otherwise>
        <% response.sendRedirect("/signin"); %>
    </c:otherwise>
</c:choose>
<jsp:include page="header.jsp">
    <jsp:param name="post" value="active"></jsp:param>
    <jsp:param name="postNew" value="active"></jsp:param>
</jsp:include>

<script type="text/javascript" src="js/secu_slider.js"></script>
<script type="text/javascript" src="js/security/key-handler.js"></script>
<script type="text/javascript" src="js/security/rsa-bundle.js"></script>
<script type="text/javascript">
    function post() {
        var form = document.post_form;
        if (form.text_secu_level.value == 50) {
            form.text.value = encryptString(form.text.value);
        }
        form.submit();
    }

    function addFriendToList(name) {
        if (event.keyCode == 13) {
            var datalist = document.getElementById('friends').options;
            if (ifCollectionIncludes(name, datalist)) {
                if (document.getElementById('display_friends_names').innerText != "")
                    document.getElementById('display_friends_names').innerHTML += ",&nbsp";
                document.getElementById('display_friends_names').innerText += name;
                if (document.getElementById('selected_friends').value != "")
                    document.getElementById('selected_friends').value += ',';
                document.getElementById('selected_friends').value += name;
                document.getElementById('search_field').value = "";
            }
        }
    }

    function ifCollectionIncludes(item, collection) {
        for (var i = 0; i < collection.length; i++) {
            if (collection.item(i).value == item)
                return true;
        }
    }
</script>
<%--the content of the site goes here.--%>
<div style="padding: 30px; margin-left: 100px; margin-top: 50px">
    <h1 style="font-size: 30px;">New Post</h1>

    <form action="/post" method="post" name="post_form">
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
            <input type="hidden" name="selected_friends" id="selected_friends"/>
        </div>
    </form>
    <div style="text-align: center; width: 500px; padding-top: 20px; padding-bottom: 20px;" id="text_secu_text">
        Allow anyone to view this post (leave unencrypted)
    </div>
    <div style="display: none; width: 500px; padding-top: 20px; padding-bottom: 20px;"
         id="search_friends">
        Search for friend&nbsp
        <input type="text" id="search_field" list="friends" onkeydown="addFriendToList(this.value)"
               style="width: 300px"/><br/><br/>
        Selected Friends&nbsp
        <div style="display:inline" id="display_friends_names"></div>
    </div>

    <datalist id="friends">
        <jsp:include page="friends_list.jsp"></jsp:include>
    </datalist>
    <div style="text-align: center; width: 500px; padding-top: 20px">
        <button type="button" onclick="post()" style="width: 100px; height: 40px;">Post</button>
    </div>
</div>
</div>