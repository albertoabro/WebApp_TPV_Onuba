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

    <div>
       <table>
           <tbody>

           </tbody>
       </table>
    </div>
</body>
</html>