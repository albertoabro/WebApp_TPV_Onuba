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

<section> <div th:insert="~{production/menuProduction}"></div></section>

<span th:if="${error!=null}" th:text="${error}"></span>

<div class="container mt-4">
    <div class="row">
        <div class="col-sm-4" style="padding-bottom: 20px" th:each="traceability: ${traceabilities}">
            <div class="card">
                <div class="card-header text-center">
                    <label class="fw-bold mt-2 text-end">Número de lote:</label>
                    <br/>
                    <label class="fw-bold mt-2 text-end" th:text="${traceability.numberBatch}"></label>
                    <div class="card-body text-center">
                        <a class="noUnderlined" th:href="@{/traceabilities/traceability(id=${traceability.idTraceability})}">Datos</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>