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
<section> <div th:insert="~{family/menuFamily}"></div></section>
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-8">
            <table class="table">
                <thead>
                <tr>
                    <th>NOMBRE</th>
                    <th>ACCIÓN</th>
                </tr>
                </thead>
                <tbody>

                <tr th:each="family:${family}">
                    <td th:text="${family.nameFamily}"></td>
                    <td> <a th:href="@{/families/editFamily(id=${family.idFamily})}"> Editar</a></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>