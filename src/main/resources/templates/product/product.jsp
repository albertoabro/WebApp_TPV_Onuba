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
<section> <div th:insert="~{product/menuProduct}"></div></section>

<form action="/products/delete" method="post">
    <input type="hidden" name="_method" value="delete" readonly>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-8">
                <table class="table">
                    <thead>
                        <tr>
                            <th>NOMBRE</th>
                            <th>PROVEEDOR</th>
                            <th>PRECIO</th>
                            <th>CATEGORÍA</th>
                            <th>STOCK</th>
                            <th>ACCIONES</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                    <tr th:each="product:${products}">
                        <td th:text="${product.nameProduct}"></td>
                        <td th:each="provider:${providers}" th:if="${provider.idProvider == product.provider}" th:text="${provider.nameProvider}"></td>
                        <td th:text="${product.price}"></td>
                        <td th:text="${product.category}"></td>
                        <td th:text="${product.stock}"></td>
                        <td><a class="btn btn-secondary mx-md-1" th:href="@{/products/editProduct(id=${product.idProduct})}"> Editar</a></td>
                        <td class="d-md-flex justify-content-md-end">
                            <button type="submit" class="btn btn-secondary" name="idProduct" th:value="${product.idProduct}" onclick="return confirm('Borrar')">Eliminar</button>
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