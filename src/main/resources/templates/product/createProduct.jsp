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

<div class="container px-5 my-5">
    <form method="POST" action="/products/addProduct">
        <div class="form-floating mb-3">
            <input class="form-control" name="nameProduct" id="nombre" type="text" placeholder="Nombre" data-sb-validations="required" />
            <label for="nombre">Nombre</label>
            <div class="invalid-feedback" data-sb-feedback="nombre:required">Nombre is required.</div>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" name="idProvider" id="provider" aria-label="Proveedor">
                <div th:each="provider:${providers}">
                    <option th:value="${provider.idProvider}" th:text="${provider.nameProvider}"></option>
                </div>
            </select>
            <label for="provider">Proveedor</label>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="category" id="category" type="text" placeholder="Categoría" data-sb-validations="required" />
            <label for="category">Categoría</label>
            <div class="invalid-feedback" data-sb-feedback="category:required">Category is required.</div>

        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="price" id="price" type="text" placeholder="price" data-sb-validations="required" />
            <label for="price">Precio</label>
            <div class="invalid-feedback" data-sb-feedback="price:required">Price is required.</div>

        </div>
        <div class="d-none" id="submitErrorMessage">
            <div class="text-center text-danger mb-3">Error sending message!</div>
        </div>
        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>