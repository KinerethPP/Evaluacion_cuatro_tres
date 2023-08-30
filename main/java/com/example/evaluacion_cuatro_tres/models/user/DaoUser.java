package com.example.evaluacion_cuatro_tres.models.user;

import com.example.evaluacion_cuatro_tres.utils.MYSQLConnection;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DaoUser {

    private Connection conn;
    private PreparedStatement pstm;
    private ResultSet rs;
    private CallableStatement cs;

    public User loadUserByUsernameAndPassword(String correo,
                                              String contraseña) {
        System.out.println(correo + contraseña);
        try {
            conn = new MYSQLConnection().connect();
            String query = "SELECT u.id, u.correo, r.nombre FROM Usuarios u JOIN Roles r ON u.rol_id = r.id WHERE u.correo = ? AND u.contraseña = ?";
            pstm = conn.prepareStatement(query);
            pstm.setString(1, correo);
            pstm.setString(2, contraseña);
            rs = pstm.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId_user(rs.getLong("id"));
                user.setUsername(rs.getString("correo"));

                Role role = new Role();
                role.setRole(rs.getString("nombre"));
                user.setRole(role);
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(DaoUser.class.getName())
                    .log(Level.SEVERE,
                            "Credentials mismatch: " + e.getMessage());
        } finally {
            close();
        }
        return null;
    }

    public void close() {
        try {
            if (conn != null) conn.close();
            if (pstm != null) pstm.close();
            if (rs != null) rs.close();
        } catch (SQLException e) {

        }

    }



}

