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
<section> <div th:insert="~{synchronize/menuTerminal}"></div></section>
<div class="container mt-4">
    <div class="row">
        <div class="col-sm-8">
            <form action="/synchronize/synchronize" method="post" >
                <table class="table">
                    <caption>TERMINALES</caption>
                    <thead>
                    <tr>
                        <th>NOMBRE</th>
                        <th>Ipv4</th>
                        <th>Ipv6</th>
                        <th>ACCIÓN</th>
                    </tr>
                    </thead>
                    <tbody>

                    <tr th:each="terminal:${terminals}">
                        <td th:text="${terminal.name}"></td>
                        <td th:text="${terminal.ipv4}"></td>
                        <td th:text="${terminal.ipv6}"></td>
                        <td> <input type="checkbox" class="checkbox" th:value="${terminal.idterminal}" name="terminalSelected" onchange=""/></td>
                    </tr>

                    </tbody>
                </table>

                <input type="submit" class="submit" value="Sincronizar" disabled="disabled">
            </form>
        </div>
    </div>
</div>

<script type="text/javascript">
 $(function()
 {
    $(".checkbox").click(function (){
       $('.submit').prop('disabled', $('input.checkbox:checked').length === 0);
    });
 });

</script>
</body>
</html>