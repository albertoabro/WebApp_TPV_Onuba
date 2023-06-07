<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      lang="es">
<head>
    <meta charset="UTF-8">
    <title>Tpv Onuba</title>
</head>
<body>
<section> <div th:insert="~{menu}"></div></section>
<section> <div th:insert="~{employee/menuEmployee}"></div></section>

<form action="/users/editEmployee" th:object="${user}" method="post" class="form">
    <input type="hidden" name="_method" value="put" readonly>
    <input type="hidden" th:field="*{idUser}" readonly>
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
        <input class="form-control" id="address" type="text" placeholder="Dirección" th:field="*{address}"/>
        <label for="address">Dirección</label>
        <div class="alert alert-warning" th:if="${#fields.hasErrors('address')}" th:errors="*{address}"></div>
    </div>

    <div class="form-floating mb-3">
        <input class="form-control"id="phone" type="text" placeholder="Telefono" th:field="*{phone}"/>
        <label for="phone">Teléfono</label>
        <div class="alert alert-warning" th:if="${#fields.hasErrors('phone')}" th:errors="*{phone}"></div>
    </div>
    <div class="form-floating mb-3">
        <input class="form-control" id="passwordTPV" type="text" placeholder="Contraseña TPV"  th:field="*{passwordTPV}"/>
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