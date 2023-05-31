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

<form action="/providers/delete" method="post">
    <input type="hidden" name="_method" value="delete" readonly>
    <div class="container mt-4">
        <div class="row">
            <div class="col-sm-8">
                <table class="table">
                    <thead>
                        <tr>
                            <th>NOMBRE</th>
                            <th>DIRECCIÓN</th>
                            <th>TELÉFONO</th>
                            <th>PRODUCTOS</th>
                            <th>ACCIONES</th>
                            <th> </th>
                        </tr>
                    </thead>
                    <tbody>

                    <tr th:each="provider:${provider}">
                        <td th:text="${provider.nameProvider}"></td>
                        <td th:text="${provider.address}"></td>
                        <td th:text="${provider.phone}"></td>
                        <td> <a class="btn btn-secondary mx-md-1" th:href="@{/products/productsByProvider(id=${provider.idProvider})}"> Productos</a></td>
                        <td><a class="btn btn-secondary mx-md-1" th:href="@{/providers/editProvider(id=${provider.idProvider})}"> Editar</a></td></td>
                        <td class="d-md-flex justify-content-md-end">
                            <button type="submit" class="btn btn-secondary" name="idProvider" th:value="${provider.idProvider}" onclick="return confirm('Borrar')">Eliminar</button>
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