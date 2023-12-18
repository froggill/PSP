<%@ page import="entity.Order" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: лиза
  Date: 05.12.2023
  Time: 13:20
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
<div class = "header">Данные о всех заказах</div>
<form class="form" action="/jsp/clientMainMenuForm.jsp" style="margin: 100px">
    <%
        List<Order> list = (List<Order>) session.getAttribute("ResultList");
        out.print("<table>");
        out.print("<tr><th>Id</th><th>Название</th><th>Количество</th></tr>");

        for (Order item : list) {
            out.print("<tr>");
            out.print("<td>" + item.getId() + "</td>");
            out.print("<td>" + item.getName() + "</td>");
            out.print("<td>" + item.getQuantity() + "</td>");
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
