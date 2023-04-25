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
    <form method="POST" action="/articles/editArticle">
        <input type="hidden" name="_method" value="put">
        <input type="hidden" name="id" th:value="${article.idArticle}">
        <div class="form-floating mb-3">
            <input class="form-control" name="nameSales" id="nombre" type="text" placeholder="Nombre" data-sb-validations="required" th:value="${article.nameSales}"/>
            <label for="nombre" >Nombre</label>
            <div class="invalid-feedback" data-sb-feedback="nombre:required">Nombre is required.</div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="price" id="price" type="text" placeholder="Precio" data-sb-validations="required" />
            <label for="price">Precio</label>
            <div class="invalid-feedback"  data-sb-feedback="price:required">Price is required.</div>
        </div>
        <div class="form-floating mb-3">
            <select class="form-select" name="typeUser" id="family" aria-label="Familia">

                <div th:each="family:${families}">
                    <option th:selected="${article.idFamily==family.idFamily}" th:value="${family.idFamily}" th:text="${family.nameFamily}"></option>
                </div>
            </select>
            <label for="family">Familia</label>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" name="numBatch" id="numBatch" type="text" placeholder="Dirección" data-sb-validations="required" />
            <label for="numBatch">Número de Lote</label>
            <div class="invalid-feedback"  data-sb-feedback="numBatch:required">NumBatch is required.</div>
        </div>
        <div class="d-none" id="submitErrorMessage">
            <div class="text-center text-danger mb-3">Error sending message!</div>
        </div>
        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Actualizar</button>
        </div>
    </form>
</div>
</body>
</html>