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
<section> <div th:insert="~{production/menuProduction}"></div></section>
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-8">
            <table class="table">
                <thead>
                <tr>
                    <th>ARTICULO</th>
                    <th>NÚMERO DE LOTE</th>
                    <th>FECHA DE CADUCIDAD</th>
                    <th>PRODUCTOS UTILIZADOS</th>
                </tr>
                </thead>
                <tbody>

                <tr th:each="traceability:${traceability}">
                    <td th:each="article:${article}" th:text="${article.nameSales}"></td>
                    <td th:text="${traceability.numberBatch}"></td>
                    <td th:text="${traceability.expirationDate}"></td>
                    <td> <a th:href="@{/products/productsTraceability(idsProducts=${idsProducts})}">Productos</a></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>