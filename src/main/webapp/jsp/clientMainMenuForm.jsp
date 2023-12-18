<%@ page import="entity.MenuItem" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 13.10.2021
  Time: 16:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ClientMainMenuForm</title>
    <link href="util/styles.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("name")==null || session.getAttribute("password")==null){
    session.setAttribute("error","Log in or register!!!");
    response.sendRedirect("/jsp/loginMenu.jsp");}%>
<div class = "header">Меню клиента</div>
<div id = "loginForm">

    <form class="form" action="/addOrder" method="post">
        <fieldset>
            <legend style="text-align: center">Сделать заказ</legend>
            <div class="inputValue">
                <label for="nameMenuItem">Введите название: </label>
                <input type="text" id="nameMenuItem" name="nameMenuItemToOrder"  required="required">
            </div>
            <div class="inputValue">
                <label for="quantityMenuItem">Введите количество: </label>
                <input type="number" id="quantityMenuItem" name="quantityMenuItemToOrder" step="1" maxlength="4" min="1" max="400" required="required">
            </div>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>

    <form class="form" action="/delOrder" method="post">
        <fieldset>
            <legend style="text-align: center">Отменить заказ</legend>
            <div class="inputValue">
                <label for="idOrderToDel">Номер заказа: </label>
                <input type="number" id="idOrderToDel" name="idOrderToDel"  required="required">
            </div>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>

</div>

<div class="buttons">
    <form action="/showOrdersForUser" method="post">
        <button type="submit" class="addButton"  >Просмотреть свои заказы</button>
    </form>

    <form action="loginMenu.jsp" method="post">
        <button type="submit" class="addButton" >Выйти</button>
    </form>
</div>

<div class="errArea">
    <p><%if(session.getAttribute("error")!=null){
        out.println(session.getAttribute("error"));
    }session.setAttribute("error",null);%></p>
</div>
</body>
</html>
