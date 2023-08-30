package com.example.evaluacion_cuatro_tres.models.user;

import com.example.evaluacion_cuatro_tres.models.user.Clase;
import com.example.evaluacion_cuatro_tres.utils.MYSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClaseDAO {
    private Connection conn;
    private PreparedStatement pstm;
    private CallableStatement cs;
    private ResultSet rs;


    public boolean save (Clase clase) {
        try  {
            conn = new MYSQLConnection().connect();
            String query = "INSERT INTO Clases (nombre, descripcion, instructor_id) VALUES (?, ?, ?)";
            pstm = conn.prepareStatement(query);
                pstm.setString(1, clase.getNombre());
                pstm.setString(2, clase.getDescripcion());
                pstm.setInt(3, clase.getInstructorId());
                pstm.execute();
            return  true;
        } catch (SQLException e) {
            Logger.getLogger(ClaseDAO.class.getName()).log(Level.SEVERE,"Error findAll" + e.getMessage());
        }finally {
            close();
        } return false;
    }

    public static List<Clase> getAllClasses() {
        List<Clase> clases = new ArrayList<>();
        try (Connection conn = new MYSQLConnection().connect()) {
            String query = "SELECT * FROM Clases";
            try (PreparedStatement pstm = conn.prepareStatement(query);
                 ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    Clase claseObj = new Clase();
                    claseObj.setId(rs.getInt("id"));
                    claseObj.setNombre(rs.getString("nombre"));
                    claseObj.setDescripcion(rs.getString("descripcion"));
                    claseObj.setInstructorId(rs.getInt("instructor_id"));
                    clases.add(claseObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return clases;
    }

    public static void inscribirseAClase(int estudianteId, int claseId) {
        try (Connection conn = new MYSQLConnection().connect()) {
            String query = "INSERT INTO Inscripciones (estudiante_id, clase_id) VALUES (?, ?)";
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, estudianteId);
                pstm.setInt(2, claseId);
                pstm.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
    }

    public static List<Clase> getClasesInscritas(int estudianteId) {
        List<Clase> clases = new ArrayList<>();
        try (Connection conn = new MYSQLConnection().connect()) {
            String query = "SELECT c.* FROM Clases c INNER JOIN Inscripciones i ON c.id = i.clase_id WHERE i.estudiante_id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(query)) {
                pstm.setInt(1, estudianteId);
                try (ResultSet rs = pstm.executeQuery()) {
                    while (rs.next()) {
                        Clase claseObj = new Clase();
                        claseObj.setId(rs.getInt("id"));
                        claseObj.setNombre(rs.getString("nombre"));
                        claseObj.setDescripcion(rs.getString("descripcion"));
                        claseObj.setInstructorId(rs.getInt("instructor_id"));
                        clases.add(claseObj);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Manejo de excepciones
        }
        return clases;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
