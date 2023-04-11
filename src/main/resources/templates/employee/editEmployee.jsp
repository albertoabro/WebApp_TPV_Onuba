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
    <section> <div th:insert="~{employee/menuEmployee}"></div></section>

    <div class="container px-5 my-5">
        <form method="POST" action="/users/editEmployee">
            <input type="hidden" name="_method" value="put">
            <input type="hidden" name="id" th:value="${user.idUser}">
            <div class="form-floating mb-3">
                <input class="form-control" name="userName" id="nombre" type="text" placeholder="Nombre" data-sb-validations="required" th:value="${user.userName}"/>
                <label for="nombre" >Nombre</label>
                <div class="invalid-feedback" data-sb-feedback="nombre:required">Nombre is required.</div>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="password" id="contrasena" type="text" placeholder="Contraseña" data-sb-validations="required" th:value="${user.password}"/>
                <label for="contrasena">Contraseña</label>
                <div class="invalid-feedback" datasena:required>Contraseña is required.</div>
            </div>
            <div class="form-floating mb-3">
                <select class="form-select" name="typeUser" id="tipoEmpleado" aria-label="Tipo Empleado">

                    <div th:each="typeUser:${typesUser}">
                        <option th:selected="${user.typeUser==typeUser.id}" th:value="${typeUser.id}" th:text="${typeUser.denomination}"></option>
                    </div>
                </select>
                <label for="tipoEmpleado">Tipo Empleado</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="address" id="direccion" type="text" placeholder="Dirección" data-sb-validations="" th:value="${user.address}"/>
                <label for="direccion">Dirección</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="phone" id="telefono" type="text" placeholder="Telefono" data-sb-validations="" th:value="${user.phone}"/>
                <label for="telefono">Telefono</label>
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