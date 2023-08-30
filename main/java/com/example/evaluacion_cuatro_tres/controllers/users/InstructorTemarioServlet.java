package com.example.evaluacion_cuatro_tres.controllers.users;

import com.example.evaluacion_cuatro_tres.models.user.Clase;
import com.example.evaluacion_cuatro_tres.models.user.InstructorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/gestion-temario")
public class InstructorTemarioServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obtener los datos del formulario.
        int classId = Integer.parseInt(req.getParameter("clase_id"));
        String temario = req.getParameter("temario");

        // Registrar el temario en la base de datos.
        boolean isRegistered = InstructorDAO.registerClassTemario(classId, temario);

        // Redirigir de nuevo al formulario con un mensaje de éxito o error.
        if (isRegistered) {
            req.setAttribute("message", "Temario registrado con éxito");
        } else {
            req.setAttribute("error", "Hubo un problema al registrar el temario");
        }
        doGet(req, resp);
    }
}
