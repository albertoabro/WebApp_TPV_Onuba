<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      lang="es">
<head>
    <meta charset="UTF-8">
    <script type="text/javascript" src="//code.jquery.com/jquery-1.6.2.js"></script>
    <title>Tpv Onuba</title>
</head>
<body>
<section> <div th:insert="~{menu}"></div></section>
<section> <div th:insert="~{synchronize/menuTicket}"></div></section>
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-8">
            <form action="/synchronize/download" method="post" >
               <input type="submit" class="submit" value="Obtener Ventas">
            </form>
        </div>
    </div>
</div>

</body>
</html>