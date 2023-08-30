package com.example.evaluacion_cuatro_tres.controllers.users;

import com.example.evaluacion_cuatro_tres.models.user.Clase;
import com.example.evaluacion_cuatro_tres.models.user.ClaseDAO;
import com.example.evaluacion_cuatro_tres.models.user.Instructor;
import com.example.evaluacion_cuatro_tres.models.user.InstructorDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "registrar-clases", urlPatterns = {
        "/clase/save",
        "/clases/view"
})

public class RegistroClaseServlet extends HttpServlet {
    private String action;
    private String id;
    private String redirect;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getServletPath();

        switch (action) {
            case "/clases/view":
                List<Clase> clases = new ClaseDAO().getAllClasses();
                req.setAttribute("clases", clases);
                req.getRequestDispatcher("/pages/admin-view.jsp").forward(req, resp);
                break;
            default:
                System.out.println(action);
        }
    }



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        action = request.getServletPath();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        switch (action) {
            case "/clases/save":
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        int instructorId = Integer.parseInt(request.getParameter("instructor_id"));

        Clase clase = new Clase();
        clase.setNombre(nombre);
        clase.setDescripcion(descripcion);
        clase.setInstructorId(instructorId);

                try {
                    if (new ClaseDAO().save(clase)) {
                        redirect = "/api/user/admin?result=true&message=" + URLEncoder.encode("Instructor guardado correctamente", StandardCharsets.UTF_8);
                    } else {
                        throw new Exception("Error");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    redirect = "/api/user/admin?result=false&message=" + URLEncoder.encode("No se pudo guardar el instructor", StandardCharsets.UTF_8);
                }
                break;
        }
        response.sendRedirect(request.getContextPath() + redirect);
    }
}
