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
<section> <div th:insert="~{provider/menuProvider}"></div></section>

<div class="container px-5 my-5">
    <form method="POST" action="/providers/editProvider" th:object="${provider}" class="form">
        <input type="hidden" name="_method" value="put" readonly>
        <input type="hidden" th:field="*{idProvider}" readonly>
        <div class="form-floating mb-3">
            <input class="form-control" id="nameProvider" type="text" placeholder="Nombre" th:field="*{nameProvider}"/>
            <label for="nameProvider">Nombre</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('nameProvider')}" th:errors="*{nameProvider}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="address" type="text" placeholder="Dirección" th:field="*{address}" />
            <label for="address">Dirección</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('address')}" th:errors="*{address}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="phone" type="text" placeholder="Telefono" th:field="*{phone}"/>
            <label for="phone">Telefono</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('phone')}" th:errors="*{phone}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="productDescription" type="text" placeholder="Descripción de Productos"  th:field="*{productDescription}"/>
            <label for="productDescription">Descripción de productos</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('productDescription')}" th:errors="*{productDescription}"></div>
        </div>
        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>