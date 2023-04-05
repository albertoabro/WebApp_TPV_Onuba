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
        <form method="POST" action="/users/addEmployee">
            <div class="form-floating mb-3">
                <input class="form-control" id="nombre" type="text" placeholder="Nombre" data-sb-validations="required" />
                <label for="nombre">Nombre</label>
                <div class="invalid-feedback" data-sb-feedback="nombre:required">Nombre is required.</div>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" id="contrasena" type="text" placeholder="Contraseña" data-sb-validations="required" />
                <label for="contrasena">Contraseña</label>a-sb-feedback="contr
                <div class="invalid-feedback datasena:required">Contraseña is required.</div>
            </div>
            <div class="form-floating mb-3">
                <select class="form-select" id="tipoEmpleado" aria-label="Tipo Empleado">
                    <div th:each="typeUser:${typesUser}">
                        <option th:value="${typeUser.id}" th:text="${typeUser.denomination}"></option>
                    </div>
                </select>
                <label for="tipoEmpleado">Tipo Empleado</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" id="direccion" type="text" placeholder="Dirección" data-sb-validations="" />
                <label for="direccion">Dirección</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" id="telefono" type="text" placeholder="Telefono" data-sb-validations="" />
                <label for="telefono">Telefono</label>
            </div>
            <div class="d-none" id="submitErrorMessage">
                <div class="text-center text-danger mb-3">Error sending message!</div>
            </div>
            <div class="d-grid">
                <button class="btn btn-primary btn-lg disabled" id="submitButton" type="submit">Submit</button>
            </div>
        </form>
    </div>
</body>
</html>