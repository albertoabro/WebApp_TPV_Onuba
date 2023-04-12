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

    <div class="container px-5 my-5">
        <form method="POST" action="/providers/editProvider">
            <input type="hidden" name="_method" value="put">
            <input type="hidden" name="idProvider" th:value="${provider.idProvider}">
            <div class="form-floating mb-3">
                <input class="form-control" name="nameProvider" id="nombre" type="text" placeholder="Nombre" data-sb-validations="required" th:value="${provider.nameProvider}"/>
                <label for="nombre" >Nombre</label>
                <div class="invalid-feedback" data-sb-feedback="nombre:required">Nombre is required.</div>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="address" id="direccion" type="text" placeholder="Dirección" data-sb-validations="" th:value="${provider.address}"/>
                <label for="direccion">Dirección</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="phone" id="telefono" type="text" placeholder="Telefono" data-sb-validations="" th:value="${provider.phone}"/>
                <label for="telefono">Telefono</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="products" id="product" type="text" placeholder="Producto" data-sb-validations="" th:value="${provider.products}"/>
                <label for="product">Producto</label>
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