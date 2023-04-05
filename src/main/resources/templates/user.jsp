
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
  <meta charset="UTF-8">
  <title>User Manager</title>
</head>
<body>
<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Name</th>
  </tr>
  </thead>
  <tbody>
    <tr th:each="user: ${users}">
      <td th:text="${user.id}"></td>
      <td th:text="${user.userName}"></td>
    </tr>

  </tbody>
</table>
</body>
</html>