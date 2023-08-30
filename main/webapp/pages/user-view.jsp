<%--
  Created by IntelliJ IDEA.
  User: HP
  Date: 22/08/2023
  Time: 09:54 p. m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Bienvenido User ${user.correo}  </h1>
<form action="/api/auth/logout">
    <button >Salir</button>
</form>
</body>
</html>
