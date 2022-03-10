<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="ru.job4j.dream.store.DbStore" %>
<%@ page import="ru.job4j.dream.model.Candidate" %>
<%@ page import="ru.job4j.dream.model.City" %>
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
        <link rel="stylesheet"
              href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" ></script>
        <script>
            function validate() {
                document
                    .querySelector('#form-candidate-name')
                    .onsubmit = function (evt) {
                    if ($('#candidate-name').val() === '') {
                        alert($('#candidate-name').attr('title'));
                        evt.preventDefault();
                    }
                    if ($('select option:selected').val() === '---Не выбран---') {
                        alert($('#city-selector').attr('title'));
                        evt.preventDefault();
                    }
                }
            }
        </script>
        <script>
            $(document).ready(function () {
                $.ajax({
                    type: 'GET',
                    url: 'http://localhost:8080/dreamjob/city.do',
                    dataType: 'json'
                }).done(function (data) {
                    for (let i = 0; i <= data.length - 1; i++) {
                        let option = document.createElement('option');
                        option.value = data[i].id;
                        option.text = data[i].name;
                        document.querySelector('#city-selector').append(option);
                    }
                }).fail(function (err) {
                    console.log(err);
                });
            });
        </script>
        <title>Работа мечты</title>
    </head>
    <body>
        <%
            String id = request.getParameter("id");
            Candidate can = new Candidate(0, "", 1);
            if (id != null) {
                can = DbStore.instOf().findCandidateById(Integer.valueOf(id));
            }
        %>
        <div class="container pt-3">
            <div class="row">
                <ul class="nav">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/index.do">Главная страница</a>
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
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/user/edit.jsp">Регистрация</a>
                        </li>
                    </c:if>
                    <c:if test="${user != null}">
                        <li class="nav-item">
                            <a class="nav-link"
                               href="<%=request.getContextPath()%>/userProfile.do"><c:out value="${user.name}"/></a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="<%=request.getContextPath()%>/logout.do">Выйти</a>
                        </li>
                    </c:if>
                </ul>
            </div>
            <div class="row">
                <div class="card" style="width: 100%">
                    <div class="card-header">
                        <% if (id == null) { %>
                        Новый кандидат.
                        <% } else { %>
                        Редактирование данных кандидата.
                        <% } %>
                    </div>
                    <div class="card-body">
                        <form action="<%=request.getContextPath()%>/candidates.do?id=<%=can.getId()%>"
                              id="form-candidate-name"
                              method="post">
                            <div class="form-group">
                                <label>Должность</label>
                                <input type="text"
                                       class="form-control"
                                       name="name"
                                       id="candidate-name"
                                       title="Введите должность кандидата">
                            </div>
                            <div class="form-group">
                                <label for="city-selector">Город</label>
                                <select class="form-control" id="city-selector" name="city" title="Выберите город">
                                    <option>---Не выбран---</option>
                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary" onclick="validate()">Сохранить</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>