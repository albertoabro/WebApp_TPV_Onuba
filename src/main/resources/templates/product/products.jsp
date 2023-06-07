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

<span th:if="${error!=null}" th:text="${error}"></span>

<form action="/products/delete" method="post">
    <input type="hidden" name="_method" value="delete" readonly>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-4" style="padding-bottom: 20px" th:each="product: ${products}">
                <div class="card">
                    <div class="card-header text-center">
                        <label class="fw-bold mt-2 text-end" th:text="${product.nameProduct}"></label>
                        <div class="card-body text-center">
                            <a class="noUnderlined" th:href="@{/products/product(id=${product.idProduct})}">Datos</a>
                        </div>
                    </div>
                    <div class="card-footer">
                        <table>
                            <td>
                                <a class="btn btn-secondary mx-md-1" th:href="@{/products/editProduct(id=${product.idProduct})}">Editar</a>
                            </td>
                            <td class="d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-secondary" name="idProduct" th:value="${product.idProduct}" onclick="return confirm('Borrar')">Eliminar</button>
                            </td>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>