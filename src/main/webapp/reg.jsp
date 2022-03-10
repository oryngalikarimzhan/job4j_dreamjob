<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.model.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
                crossorigin="anonymous"></script>
        <script>
            function validate() {
                document
                    .querySelector('#form-user-initial-data')
                    .onsubmit = function (evt) {
                    if ($('#user-name').val() === ''
                        || $('#user-email').val() === ''
                        || $('#user-pwd').val() === ''
                        || $('#user-pwd-control').val() === '') {
                        alert($('#user-name').attr('title'));
                        evt.preventDefault();
                    }
                    if ($('#user-pwd').val() != $('#user-pwd-control').val()) {
                        alert($('#user-pwd-control').attr('title'));
                        evt.preventDefault();
                    }
                }
            }
        </script>
        <title>Работа мечты</title>
    </head>
    <body>
        <%
            User user = new User(0, "", "", "");
        %>
        <div class="container pt-3">
            <div class="row">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/index.jsp">Главная страница</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/posts.do">Вакансии</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/candidates.do">Кандидаты</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/post/edit.jsp">Добавить вакансию</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="<%=request.getContextPath()%>/candidate/edit.jsp">Добавить кандидата</a>
                    </li>
                    <c:if test="${user == null}">
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/login.jsp">Вoйти</a>
                        </li>
                    </c:if>
                </ul>
            </div>
            <div class="row">
                <div class="card" style="width: 100%">
                    <div class="card-header">
                        Регистрация нового пользователя.
                    </div>
                    <div class="card-body">
                        <form action="<%=request.getContextPath()%>/reg.do?id=<%=user.getId()%>"
                              id="form-user-initial-data"
                              method="post">
                            <div class="form-group">
                                <label>Имя</label>
                                <input type="text"
                                       class="form-control"
                                       name="name"
                                       id="user-name"
                                       title="Заполните все пустые поля и повторите попытку">
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="text"
                                       class="form-control"
                                       name="email"
                                       id="user-email">
                            </div>
                            <div class="form-group">
                                <label>Пароль</label>
                                <input type="password"
                                       class="form-control"
                                       name="password"
                                       id="user-pwd">
                            </div>
                            <div class="form-group">
                                <label>Пароль</label>
                                <input type="password"
                                       class="form-control"
                                       name="password"
                                       id="user-pwd-control"
                                       title="Введенные пароли отличаются. Повторите попытку">
                            </div>
                            <button type="submit" class="btn btn-primary" onclick="validate()">Сохранить</button>
                            <c:if test="${not empty error}">
                                <div style="color:red; font-weight: bold; margin: 30px 0;">
                                    <c:out value="${error}"/>
                                </div>
                            </c:if>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>