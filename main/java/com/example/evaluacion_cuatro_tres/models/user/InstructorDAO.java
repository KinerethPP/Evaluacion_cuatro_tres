package com.example.evaluacion_cuatro_tres.models.user;

import com.example.evaluacion_cuatro_tres.utils.MYSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InstructorDAO {

    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cs;
    private ResultSet rs;

    public boolean save(Instructor instructor) {
        try {
            conn = new MYSQLConnection().connect();
            conn.setAutoCommit(false); // Deshabilitar la confirmación automática para manejar las transacciones

            // Insertar en la tabla Personas
            String insertPersonQuery = "INSERT INTO personas (nombre, apellidos, curp, fecha_nacimiento) VALUES (?, ?, ?, ?)";
            pstm = conn.prepareStatement(insertPersonQuery, Statement.RETURN_GENERATED_KEYS);
            pstm.setString(1, instructor.getNombre());
            pstm.setString(2, instructor.getApellidos());
            pstm.setString(3, instructor.getCurp());
            pstm.setString(4, instructor.getFechaNacimiento());
            pstm.executeUpdate();

            // Obtener el ID generado para la persona
            int generatedId;
            try (ResultSet generatedKeys = pstm.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se pudo obtener el ID generado.");
                }
            }

            // Insertar en la tabla Instructores
            String insertInstructorQuery = "INSERT INTO instructores (persona_id) VALUES (?)";
            pstm = conn.prepareStatement(insertInstructorQuery);
            pstm.setInt(1, generatedId);
            pstm.executeUpdate();

            conn.commit(); // Confirmar la transacción
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback(); // Deshacer la transacción en caso de error
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            Logger.getLogger(InstructorDAO.class.getName()).log(Level.SEVERE, "Error in save: " + e.getMessage());
        } finally {
            close();
        }
        return false;
    }

    public List<Instructor> getAllInstructors() {
        List<Instructor> instructores = new ArrayList<>();
        System.out.println("Que pedoooooooooooooooo");
        try {
            conn = new MYSQLConnection().connect();
            String query = "SELECT p.id, p.nombre, p.apellidos, p.curp, p.fecha_nacimiento FROM instructores i " +
                    "JOIN personas p ON i.persona_id = p.id";
            pstm = conn.prepareStatement(query);
            rs = pstm.executeQuery();
            while (rs.next()) {
                Instructor instructor = new Instructor();
                instructor.setId(rs.getInt("id"));
                instructor.setNombre(rs.getString("nombre"));
                instructor.setApellidos(rs.getString("apellidos"));
                instructor.setCurp(rs.getString("curp"));
                instructor.setFechaNacimiento(rs.getDate("fecha_nacimiento").toString());
                instructores.add(instructor);
            }
        } catch (SQLException e) {
            Logger.getLogger(InstructorDAO.class.getName()).log(Level.SEVERE, "Error in getAllInstructors: " + e.getMessage());
        } finally {
            close();
        }
        return instructores;
    }

    public static boolean registerClassTemario(int classId, String temario) {
        String sql = "UPDATE clases SET temario = ? WHERE id = ?";
        try (Connection conn = new MYSQLConnection().connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, temario);
            stmt.setInt(2, classId);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            Logger.getLogger(InstructorDAO.class.getName()).log(Level.SEVERE, "Error in registerClassTemario: " + e.getMessage());
        }
        return false;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (cs != null) cs.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
