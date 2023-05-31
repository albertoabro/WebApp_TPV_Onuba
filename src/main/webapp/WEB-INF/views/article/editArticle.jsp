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

<div class="container px-5 my-5">
    <form method="POST" action="/articles/editArticle" th:object="${article}" class="form">
        <input type="hidden" name="_method" value="put" readonly>
        <input type="hidden" th:field="*{idArticle}" readonly>
        <div class="form-floating mb-3">
            <input class="form-control" id="nameSales" type="text" placeholder="Nombre" th:field="*{nameSales}"/>
            <label for="nameSales">Nombre</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('nameSales')}" th:errors="*{nameSales}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="priceSales" type="text" placeholder="Precio"th:field="*{priceSales}"/>
            <label for="priceSales">Precio</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('priceSales')}" th:errors="*{priceSales}"></div>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" id="family" aria-label="Familia" th:field="*{family}">
                <div th:each="family:${families}">
                    <option th:value="${family.idFamily}" th:text="${family.nameFamily}"></option>
                </div>
            </select>
            <label for="family">Familia</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('family')}" th:errors="*{family}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="numBatch" type="text" placeholder="Número de Lote" th:field="*{numBatch}"/>
            <label for="numBatch">Número de Lote</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('numBatch')}" th:errors="*{numBatch}"></div>
        </div>

        <div class="form-floating mb-3">
            <input class="form-control" id="stock" type="text" placeholder="Stock" th:field="*{stock}" />
            <label for="stock">Stock</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('stock')}" th:errors="*{stock}"></div>
        </div>

        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>