<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tpv Onuba</title>
</head>
<body>
    <section> <div th:insert="~{menu}"></div></section>
    <section> <div th:insert="~{employee/menuEmployee}"></div></section>
    <form action="/users/delete" method="post">
        <input type="hidden" name="_method" value="delete" readonly>
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
                        <th>CONTRASEÑA TPV</th>
                        <th>ACCIONES</th>
                        <th> </th>
                    </tr>
                    </thead>
                    <tbody>
                        <tr th:each="user:${user}">
                            <td th:text="${user.userName}"></td>
                            <td th:text="${user.password}"></td>
                            <td th:text="${user.address}"></td>
                            <td th:text="${user.phone}"></td>
                            <td th:each="type:${typeUser}" th:if="${type.id == user.typeUser}" th:text="${type.denomination}"></td>
                            <td th:text="${user.passwordTPV}"></td>
                            <td> <a class="btn btn-secondary mx-md-1" th:href="@{/users/editEmployee(id=${user.idUser})}"> Editar</a></td>
                            <td class="d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-secondary" name="idUser" th:value="${user.idUser}" onclick="return confirm('Borrar')">Eliminar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    </form>
</body>
</html>