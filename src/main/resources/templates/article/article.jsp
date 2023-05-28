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

<form action="/articles/delete" method="post">
    <input type="hidden" name="_method" value="delete" readonly>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-8">
                <table class="table">
                    <thead>
                        <tr>
                            <th>NOMBRE</th>
                            <th>PRECIO</th>
                            <th>FAMILIA</th>
                            <th>NÚMERO DE LOTE</th>
                            <th>STOCK</th>
                            <th>ACCIONES</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr th:each="article:${article}">
                            <td th:text="${article.nameSales}"></td>
                            <td th:text="${article.priceSales}"></td>
                            <td th:each="family:${families}" th:if="${family.idFamily == article.idFamily}" th:text="${family.nameFamily}"></td>
                            <td th:text="${article.numBatch}"></td>
                            <td th:text="${article.stock}"></td>
                            <td> <a class="btn btn-secondary mx-md-1" th:href="@{/articles/editArticle(id=${article.idArticle})}"> Editar</a></td>
                            <td class="d-md-flex justify-content-md-end">
                                <button type="submit" class="btn btn-secondary" name="idArticle" th:value="${article.idArticle}" onclick="return confirm('Borrar')">Eliminar</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</form>
</body>
</html>