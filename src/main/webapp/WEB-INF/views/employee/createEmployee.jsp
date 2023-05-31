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

           <form action="/users/addEmployee" th:object="${user}" method="post" class="form">
            <div class="form-floating mb-3">
                <input class="form-control" id="userName" type="text" placeholder="Nombre" th:field="*{userName}"/>
                <label for="userName">Nombre</label>
                <div class="alert alert-warning" th:if="${#fields.hasErrors('userName')}" th:errors="*{userName}"></div>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" id="password" type="text" placeholder="Contraseña" th:field="*{password} "/>
                <label for="password">Contraseña</label>
                <div class="alert alert-warning" th:if="${#fields.hasErrors('password')}" th:errors="*{password}"></div>
            </div>
            <div class="form-floating mb-3">
                <select class="form-select" name="typeUser" id="typeUser" aria-label="Tipo Empleado" th:field="*{typeUser}">
                    <div th:each="typeUser:${typesUser}">
                        <option th:value="${typeUser.id}" th:text="${typeUser.denomination}"></option>
                    </div>
                </select>
                <label for="typeUser">Tipo Empleado</label>
                <div class="alert alert-warning" th:if="${#fields.hasErrors('typeUser')}" th:errors="*{typeUser}"></div>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="address" id="direccion" type="text" placeholder="Dirección"/>
                <label for="direccion">Dirección</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="phone" id="telefono" type="text" placeholder="Telefono"/>
                <label for="telefono">Teléfono</label>
            </div>
            <div class="form-floating mb-3">
                <input class="form-control" name="passwordTPV" id="passwordTPV" type="text" placeholder="Contraseña TPV"  th:field="*{passwordTPV}"/>
                <label for="passwordTPV">Constraseña TPV</label>
                <div class="alert alert-warning" th:if="${#fields.hasErrors('passwordTPV')}" th:errors="*{passwordTPV}"></div>
            </div>
            <div class="d-grid">
                <button class="btn btn-primary btn-lg" id="submitButton" type="submit">Registrar</button>
            </div>
        </form>
    </div>
</body>
</html>