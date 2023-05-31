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
<section> <div th:insert="~{family/menuFamily}"></div></section>

<span th:if="${error!=null}" th:text="${error}"></span>

<form action="/families/delete" method="post">
    <input type="hidden" name="_method" value="delete" readonly>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-4" style="padding-bottom: 20px" th:each="family: ${families}">
                <div class="card">
                    <div class="card-header text-center">
                        <label class="fw-bold mt-2 text-end" th:text="${family.nameFamily}"></label>
                        <div class="card-body text-center">
                            <a class="noUnderlined" th:href="@{/families/family(id=${family.idFamily})}">Datos</a>
                        </div>
                    </div>
                    <div class="card-footer">
                        <table>
                            <td>
                                <a class="btn btn-secondary mx-md-1" th:href="@{/families/editFamily(id=${family.idFamily})}">Editar</a>
                            </td>
                            <td class="d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-secondary" name="idFamily" th:value="${family.idFamily}" onclick="return confirm('Borrar')">Eliminar</button>
                            </td>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>