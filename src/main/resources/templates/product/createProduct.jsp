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

<div class="container px-5 my-5">
    <form method="POST" action="/products/addProduct" th:object="${product}" class="form">
        <div class="form-floating mb-3">
            <input class="form-control" id="nameProduct" type="text" placeholder="Nombre" th:field="*{nameProduct}"/>
            <label for="nameProduct">Nombre</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('nameProduct')}" th:errors="*{nameProduct}"></div>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" id="provider" aria-label="Proveedor" th:field="*{provider}">
                <div th:each="provider:${providers}">
                    <option th:value="${provider.idProvider}" th:text="${provider.nameProvider}"></option>
                </div>
            </select>
            <label for="provider">Proveedor</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('provider')}" th:errors="*{provider}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="category" type="text" placeholder="Categoría" th:field="*{category}" />
            <label for="category">Categoría</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('category')}" th:errors="*{category}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control"id="price" type="text" placeholder="Precio" th:field="*{price}" />
            <label for="price">Precio</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('price')}" th:errors="*{price}"></div>
        </div>

        <div class="form-floating mb-3">
            <input class="form-control" id="stock" type="text" placeholder="Stock" th:field="*{stock}" />
            <label for="price">Stock</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('stock')}" th:errors="*{stock}"></div>
        </div>

        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>