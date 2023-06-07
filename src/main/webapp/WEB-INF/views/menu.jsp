<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous"/>
    <link href="../static/styles.css"/>
    <title>Tpv Onuba</title>
</head>

    <body>
        <div th:fragment="~{menu}">
        <nav class="navbar navbar-expand-lg bg-light">
            <div class="container-fluid">

                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo03" aria-controls="navbarTogglerDemo03" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <a class="navbar-brand" href="/index">TPV_ONUBA</a>

                <div class="collapse navbar-collapse" id="navbarTogglerDemo03">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">

                        <li class="nav-item">
                            <a class="nav-link active" href="/users/employees">Empleados</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/articles/articles">Artículos</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/traceabilities/traceabilities">Producciones</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/families/families">Familias</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/products/products">Productos</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/providers/providers">Proveedores</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/synchronize/tickets">Ventas</a>
                        </li>

                        <li class="nav-item">
                            <a class="nav-link active" href="/synchronize/terminals">Sincronizar</a>
                        </li>
                    </ul>

                    <div class="d-flex">
                        <button class="btn disabled" th:text="${session.user.userName}"></button>
                    </div>

                    <form class="d-flex" action="/users/logout">
                        <button class="btn" > Logout</button>
                    </form>
                </div>
            </div>
        </nav>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
        </div>
    </body>
</html>
