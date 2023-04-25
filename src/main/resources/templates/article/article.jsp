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
<section> <div th:insert="~{article/menuArticle}"></div></section>
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-8">
            <table class="table">
                <thead>
                <tr>
                    <th>NOMBRE</th>
                    <th>PRECIO</th>
                    <th>FAMILIA</th>
                    <th>NÚMERO DE LOTE</th>
                    <th>ACCIÓN</th>
                </tr>
                </thead>
                <tbody>

                <tr th:each="article:${article}">
                    <td th:text="${article.nameSales}"></td>
                    <td th:text="${article.priceSales}"></td>
                    <td th:text="${article.address}"></td>
                    <td th:text="${article.numBatch}"></td>
                    <td th:each="family:${families}" th:if="${family.idFamily == article.idFamily}" th:text="${family.nameFamily}"></td>
                    <td> <a th:href="@{/articles/editArticle(id=${article.idArticle})}"> Editar</a></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>