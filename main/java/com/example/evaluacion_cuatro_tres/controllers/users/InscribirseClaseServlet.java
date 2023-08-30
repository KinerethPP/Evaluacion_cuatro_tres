package com.example.evaluacion_cuatro_tres.controllers.users;

import com.example.evaluacion_cuatro_tres.models.user.ClaseDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class InscribirseClaseServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int estudianteId = 0 /* Obtener el ID del estudiante desde la sesión o parámetro */;
        int claseId = Integer.parseInt(request.getParameter("clase_id"));

        ClaseDAO.inscribirseAClase(estudianteId, claseId);

        // Redireccionar a una página de éxito o a otra sección del sitio
        response.sendRedirect("/exito-inscripcion.jsp");
    }
}

