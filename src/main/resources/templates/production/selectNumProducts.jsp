<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="//code.jquery.com/jquery-1.6.2.js"></script>
    <title>Tpv Onuba</title>
</head>
<body>
<section> <div th:insert="~{menu}"></div></section>
<section> <div th:insert="~{production/menuProduction}"></div></section>
<div class="container mt-4">
    <div class="col-sm-8">
        <form action="/traceabilities/addTraceability" method="post" th:object="${traceability}">
            <input type="hidden" readonly="readonly" th:field="*{idTraceability}">
            <input type="hidden" readonly="readonly" th:field="*{article}">
            <input type="hidden" readonly="readonly" th:field="*{numberBatch}">
            <input type="hidden" readonly="readonly" th:field="*{expirationDate}">
            <table>
                <tbody>

                <tr th:each="product:${products}">
                    <td th:text="${product.nameProduct}"></td>
                    <input type="hidden" th:value="${product.idProduct}" readonly="readonly" name="idsProducts">
                    <td class="form-check">
                        <input type="number" min="1" max="${product.stock}" required name="stocks">
                    </td>
                </tr>

                </tbody>
            </table>
            <div class="d-grid">
                <button class="btn btn-primary btn-lg submit" id="submitButton" type="submit">Registrar</button>
            </div>
            <br/>
        </form>
    </div>
</div>
</body>
</html>