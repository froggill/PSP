<%--
  Created by IntelliJ IDEA.
  User: max20
  Date: 12.10.2021
  Time: 21:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdmMainMenuForm</title>
    <link href="util/styles.css" rel="stylesheet">
</head>
<body>
<% if(session.getAttribute("name")==null || session.getAttribute("password")==null){
    session.setAttribute("error","Log in or register!!!");
    response.sendRedirect("/jsp/loginMenu.jsp");}%>
<div class = "header">Меню администратора</div>
<div id = "loginForm">

    <div>
    <form class="form" action="/addMenuItem" method="post">
        <fieldset>
            <legend style="text-align: center">Добавить блюдо</legend>
            <div class="inputValue">
                <label for="nameMenuItem">Введите название: </label>
                <input type="text" id="nameMenuItem" name="nameMenuItemToAdd"  required="required">
            </div>
            <div class="inputValue">
                <label for="priceMenuItem">Введите цену: </label>
                <input type="number" id="priceMenuItem" name="priceMenuItemToAdd" step="1" maxlength="4" min="1" max="400" required="required">
            </div>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    <form class="form" action="/delMenuItem" method="post">
        <fieldset>
            <legend style="text-align: center">Удалить блюдо</legend>
            <div class="inputValue">
                <label for="nameMenuItemToDel">Введите название: </label>
                <input type="text" id="nameMenuItemToDel" name="nameMenuItemToDel"  required="required">
            </div>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    </div>

    <div>
    <form class="form" action="/regAdm" method="post">
        <fieldset>
            <legend style="text-align: center">Добавить администратора</legend>
            <label for="admName">Имя администратора: </label>
            <input type="text" id="admName" name="admName" required="required">
            <label for="admPassword">Пароль: </label>
            <input type="password" id="admPassword" name="admPassword" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>
    <form class="form" action="/delUsAdm" method="post">
        <fieldset>
            <legend style="text-align: center">Удалить пользователя/администратора</legend>
            <label for="usernameToDel">Введите имя: </label>
            <input type="text" id="usernameToDel" name="usernameToDel" required="required">
            <br/>
            <label for="admPassword">Введите пароль: </label>
            <input type="password" id="delPassword" name="delPassword" required="required">
            <br/>
            <button type="submit" class="addButton">Подтвердить</button>
            <button type="reset" class="addButton">Очистить</button>
        </fieldset>
    </form>

    </div>
</div>
<div class="buttons">
<form action="/showMenu" method="post">
    <button type="submit" class="addButton">Просмотреть всё меню</button>
</form>
<form action="/showUsers" method="post">
    <button type="submit" class="addButton" >Просмотреть всех пользователей</button>
</form>
<form action="/showOrdersForAdmin" method="post">
    <button type="submit" class="addButton" >Просмотреть все заказы</button>
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
