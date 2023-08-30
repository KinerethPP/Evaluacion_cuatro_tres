package com.example.evaluacion_cuatro_tres.controllers.users;

import com.example.evaluacion_cuatro_tres.models.user.Instructor;
import com.example.evaluacion_cuatro_tres.models.user.InstructorDAO;
import com.example.evaluacion_cuatro_tres.utils.MYSQLConnection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "registrar-instructors", urlPatterns = {
        "/api/save",
        "/instructores/view"
})
public class RegistroInstructorServlet extends HttpServlet {
String redirect,action;
    private static final String REDIRECT_SUCCESS = "/api/user/admin?result=true";
    private static final String REDIRECT_ERROR = "/api/user/admin?result=false";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        action = req.getServletPath();
        switch (action) {
            case "/instructores/view":
        List<Instructor> instructores = getAllInstructors();

                req.setAttribute("instructores", instructores);
                System.out.println("Ayuda me quiero morri "+instructores);
             redirect="/pages/admin-view.jsp";
                break;


            default:
                System.out.println(action);
        }
        req.getRequestDispatcher(redirect).forward(req, resp);
    }



    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");

        // Obtener los datos del formulario
        String nombre = request.getParameter("nombre");
        String apellidos = request.getParameter("apellidos");
        String curp = request.getParameter("curp");
        String fechaNacimiento = request.getParameter("fecha_nacimiento");

        // Crear una instancia de Instructor y establecer sus propiedades
        Instructor instructor = new Instructor();
        instructor.setNombre(nombre);
        instructor.setApellidos(apellidos);
        instructor.setCurp(curp);
        instructor.setFechaNacimiento(fechaNacimiento);

    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> instructores = new ArrayList<>();
        try {
            Connection conn = new MYSQLConnection().connect();
            String query = "SELECT p.id, p.nombre, p.apellidos, p.curp, p.fecha_nacimiento FROM instructores i " +
                    "JOIN personas p ON i.persona_id = p.id";
            PreparedStatement pstm = conn.prepareStatement(query);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(rs.getInt("id"));
                instructor.setNombre(rs.getString("nombre"));
                instructor.setApellidos(rs.getString("apellidos"));
                instructor.setCurp(rs.getString("curp"));
                instructor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toString());
                instructores.add(instructor);
            }
            rs.close();
            pstm.close();
            conn.close();
        } catch (SQLException e) {
            Logger.getLogger(InstructorDAO.class.getName()).log(Level.SEVERE, "Error in getAllInstructors: " + e.getMessage());
        }
        return instructores;
    }
}
