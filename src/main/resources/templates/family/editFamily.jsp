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
<section> <div th:insert="~{family/menuFamily}"></div></section>

<div class="container px-5 my-5">
    <form method="POST" action="/families/editFamily" th:object="${family}" class="form">
        <input type="hidden" name="_method" value="put" readonly>
        <input type="hidden" th:field="*{idFamily}" readonly>
        <div class="form-floating mb-3">
            <input class="form-control" id="nameFamily" type="text" placeholder="Nombre" th:field="*{nameFamily}"/>
            <label for="nameFamily">Nombre</label>
            <div class="alert alert-warning" th:if="${#fields.hasErrors('nameFamily')}" th:errors="*{nameFamily}"></div>
        </div>

        <div class="d-grid">
            <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button>
        </div>
    </form>
</div>
</body>
</html>