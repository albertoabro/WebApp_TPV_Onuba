<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8">
    <title>Tpv Onuba</title>
</head>
<body>
    <section> <div th:insert="~{menu}"></div></section>
    <section> <div th:insert="~{employee/menuEmployee}"></div></section>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-8">
                <table class="table">
                    <thead>
                    <tr>
                        <th>USUARIO</th>
                        <th>CONTRASEÑA</th>
                        <th>DIRECCIÓN</th>
                        <th>TELÉFONO</th>
                        <th>TIPO DE USUARIO</th>
                        <th>ACCION</th>
                    </tr>
                    </thead>
                    <tbody>

                        <tr th:each="user:${user}">
                            <td th:text="${user.userName}"></td>
                            <td th:text="${user.password}"></td>
                            <td th:text="${user.address}"></td>
                            <td th:text="${user.phone}"></td>
                            <td th:each="type:${typeUser}" th:if="${type.id == user.typeUser}" th:text="${type.denomination}"></td>
                            <td> <a th:href="@{/users/editEmployee(id=${user.idUser})}"> Editar</a></td>
                        </tr>

                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>