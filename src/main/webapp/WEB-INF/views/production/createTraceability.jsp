<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      lang="es">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <title>Tpv Onuba</title>
</head>
<body>
<section> <div th:insert="~{menu}"></div></section>
<section> <div th:insert="~{production/menuProduction}"></div></section>

<div class="container px-5 my-5">
    <form method="POST" action="/traceabilities/createTraceability" th:object="${traceability}" class="form">
        <div class="form-floating mb-3">
            <select class="form-select" id="article" aria-label="Artículo" th:field="*{article}">
                <div th:each="article:${articles}">
                    <option th:value="${article.idArticle}" th:text="${article.nameSales}"></option>
                </div>
            </select>
            <label for="article">Artículo</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('article')}" th:errors="*{article}"></div>
        </div>
        <div class="form-floating mb-3">
            <input class="form-control" id="numberBatch" type="text" placeholder="Número de lote" readonly="readonly" th:field="*{numberBatch}" />
            <label for="numberBatch">Número de lote</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('numberBatch')}" th:errors="*{numberBatch}"></div>
        </div>

        <div class="form-floating mb-3">
            <input class="form-control" id="expirationDate" type="text"  placeholder="Fecha de caducidad" required autocomplete="off" readonly th:field="*{expirationDate}"/>
            <label for="expirationDate">Fecha de caducidad</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('expirationDate')}" th:errors="*{expirationDate}"></div>
        </div>

        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Siguiente</button>
        </div>
    </form>
</div>

<script>
    $(document).ready(function () {
        $('#expirationDate').datepicker({
            dateFormat: "yy-mm-dd",
            minDate: 0
        });
    });

</script>

</body>
</html>