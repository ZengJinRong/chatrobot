<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Chat</title>
</head>

<style>

</style>

<body>

<h1>Chat</h1>

<div>


    <%
        String messageRcv = "";%>
    <% if (request.getAttribute("messageRcv") != null) {
        messageRcv = messageRcv + request.getAttribute("messageRcv");
    %>

    <p id="messages"><%=messageRcv%>
    </p>
    <%
            request.setAttribute("messageRcv", null);
        }
    %>

    <form action="chat" method="post">
        <input type="text"  name="messageToSend">
        <input type="submit" id="sendButton" value="发送">
    </form>

</div>

</body>
</html>
