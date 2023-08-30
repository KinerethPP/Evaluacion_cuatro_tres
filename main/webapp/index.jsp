<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Iniciar Sesión</title>
    <jsp:include page="./layouts/head.jsp"/>
</head>
<body class="bg-light">

<div class="container d-flex vh-100">
    <div class="row align-self-center w-100">
        <div class="col-md-8 mx-auto">
            <div class="card shadow">
                <div class="card-body">
                    <div class="text-center mb-5">
                        <h3>Bienvenido</h3>
                        <p>Por favor, inicia sesión para continuar</p>
                    </div>
                    <form id="loginForm" action="/api/auth" class="needs-validation" novalidate method="post">
                        <div class="form-floating mb-3">
                            <input type="text" class="form-control" name="correo" id="correo" placeholder="name@example.com" required>
                            <label for="correo">Correo electrónico</label>
                            <div class="invalid-feedback text-start">
                                Campo obligatorio
                            </div>
                        </div>
                        <div class="form-floating mb-4">
                            <input type="contraseña" name="contraseña" class="form-control" id="contraseña" placeholder="Contraseña" required>
                            <label for="contraseña">Contraseña</label>
                            <div class="invalid-feedback text-start">
                                Campo obligatorio
                            </div>
                        </div>
                        <button id="login" class="btn btn-primary w-100 mb-3" type="submit">
                            <i data-feather="log-in"></i> INICIAR SESIÓN
                        </button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="./layouts/footer.jsp"/>
<!-- -->
<script>
    // Tu código JS sigue aquí...
</script>
</body>
</html>


<jsp:include page="./layouts/footer.jsp"/>
<!-- -->
<script>
    window.addEventListener("DOMContentLoaded", () => {
        feather.replace();
        const form = document.getElementById("loginForm");
        const btn = document.getElementById("login");
        form.addEventListener('submit', event => {
            btn.innerHTML = `<div class="d-flex justify-content-center">
                                <div class="spinner-border" role="status">
                                    <span class="visually-hidden">Loading...</span>
                                </div>
                            </div>`;
            btn.classList.add("disabled");
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
                btn.classList.remove("disabled");
                btn.innerHTML = `<i data-feather="log-in"></i> INICIAR SESIÓN`;
            }
            form.classList.add('was-validated');
        }, false);
        if (!${param['result']}) {
            Swal.fire({
                title: 'Acceso denegado',
                text: '${param['message']}',
                icon: 'error',
                confirmButtonText: 'Aceptar'
            });
        }
    }, false);
</script>
</body>
</html>