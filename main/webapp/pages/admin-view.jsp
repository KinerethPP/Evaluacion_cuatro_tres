<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Panel de Administrador</title>
</head>
<body>
<h1>Bienvenido administrador ${user.correo}</h1>

<h2>Registro de Instructores</h2>
<form action="/tu-servlet-de-registro-de-instructores" method="post">
    <!-- Campos para el registro de instructores -->
    <input type="text" name="nombre" placeholder="Nombre" required>
    <input type="text" name="apellidos" placeholder="Apellidos" required>
    <input type="text" name="curp" placeholder="CURP" required>
    <input type="date" name="fecha_nacimiento" required>
    <button type="submit">Registrar Instructor</button>
</form>

<h2>Registro de Clases</h2>
<form action="/tu-servlet-de-registro-de-clases" method="post">
    <!-- Campos para el registro de clases -->
    <input type="text" name="nombre_clase" placeholder="Nombre de Clase" required>
    <textarea name="descripcion" placeholder="Descripción de Clase" required></textarea>
    <select name="instructor_id" required>
        <option value="">Selecciona un Instructor</option>
        <!-- Aquí deberías obtener y mostrar la lista de instructores -->
    </select>
    <button type="submit">Registrar Clase</button>
</form>

<h2>Consulta de Instructores</h2>
<!-- Aquí deberías mostrar la lista de instructores -->

<h2>Consulta de Clases</h2>
<!-- Aquí deberías mostrar la lista de clases -->

<form action="/api/auth/logout">
    <button>Salir</button>
</form>
</body>
</html>
