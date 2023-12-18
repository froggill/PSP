<%@ page import="java.util.List" %>
<%@ page import="entity.MenuItem" %><%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 13.10.2021
  Time: 15:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data</title>
    <link href="util/styles.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("name")==null || session.getAttribute("password")==null){
    session.setAttribute("error","Log in or register!!!");
    response.sendRedirect("/jsp/loginMenu.jsp");}%>
<div class = "header">Меню</div>
<form class="form" action="/jsp/admMainMenuForm.jsp" style="margin: 100px;">
    <%
        List<MenuItem> list = (List<MenuItem>) session.getAttribute("ResultList");
        out.print("<table>");
        out.print("<tr><th>Id</th><th>Название</th><th>Цена</th></tr>");

        for (MenuItem item : list) {
            out.print("<tr>");
            out.print("<td>" + item.getId() + "</td>");
            out.print("<td>" + item.getName() + "</td>");
            out.print("<td>" + item.getPrice() + "</td>");
            out.print("</tr>");
        }

        out.print("</table>");
        session.setAttribute("ResultSet", null);
    %>
    <br/>
    <button type="submit" class="addButton">Подтвердить</button>
</form>
</body>
</html>
