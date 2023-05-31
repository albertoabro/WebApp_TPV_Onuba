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
            <form action="/traceabilities/selectNumProducts" method="post" th:object="${traceability}" class="form">
                <input type="hidden" readonly="readonly" th:field="*{idTraceability}">
                <input type="hidden" readonly="readonly" th:field="*{article}">
                <input type="hidden" readonly="readonly" th:field="*{numberBatch}">
                <input type="hidden" readonly="readonly" th:field="*{expirationDate}">
                <table>
                    <tbody>

                    <tr th:each="product:${products}">
                        <td class="form-check">
                            <input class="form-check-input checkbox" type="checkbox" th:value="${product.idProduct}" name="idsProducts">
                        </td>
                        <td th:text="${product.nameProduct}"></td>
                    </tr>

                    </tbody>
                </table>
                <div class="d-grid">
                    <button class="btn btn-primary btn-lg submit" id="submitButton" type="submit" disabled="disabled">Siguiente</button>
                </div>
            </form>
        </div>
</div>
<script type="text/javascript">
    $(function()
    {
        $(".checkbox").click(function (){
            $('.submit').prop('disabled', $('input.checkbox:checked').length === 0);
        });
    });

</script>
</body>
</html>