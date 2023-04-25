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
<section> <div th:insert="~{product/menuProduct}"></div></section>
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-8">
            <table class="table">
                <thead>
                <tr>
                    <th>NOMBRE</th>
                    <th>PROVEEDOR</th>
                    <th>CATEGORÍA</th>
                    <th>PRECIO</th>
                    <th>ACCIÓN</th>
                </tr>
                </thead>
                <tbody>

                <tr th:each="product:${product}">
                    <td th:text="${product.nameProduct}"></td>
                    <td th:each="provider:${providers}" th:if="${provider.idProvider == product.idProvider}" th:text="${provider.nameProvider}"></td>
                    <td th:text="${product.price}"></td>
                    <td th:text="${product.category}"></td>
                    <td> <a th:href="@{/products/editProduct(id=${product.idProduct})}"> Editar</a></td>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>