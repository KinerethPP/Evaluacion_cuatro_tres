package com.example.evaluacion_cuatro_tres.controllers.users;

import com.example.evaluacion_cuatro_tres.models.user.Clase;
import com.example.evaluacion_cuatro_tres.models.user.ClaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class ConsultarTemarioServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int estudianteId = 0 /* Obtener el ID del estudiante desde la sesión o parámetro */;
        int claseId = Integer.parseInt(request.getParameter("clase_id"));

        // Obtener las clases inscritas por el estudiante
        List<Clase> clasesInscritas = ClaseDAO.getClasesInscritas(estudianteId);

        // Verificar si la clase seleccionada está en las clases inscritas por el estudiante
        boolean estaInscrito = clasesInscritas.stream().anyMatch(clase -> clase.getId() == claseId);

        if (estaInscrito) {
            // Redireccionar a una página para mostrar el temario de la clase
            response.sendRedirect("/temario.jsp?clase_id=" + claseId);
        } else {
            // Redireccionar a una página de error o a otra sección del sitio
            response.sendRedirect("/error-temario.jsp");
        }
    }
}
