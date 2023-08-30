package com.example.evaluacion_cuatro_tres.models.user;

import com.example.evaluacion_cuatro_tres.utils.MYSQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Clase {

    private int id;
    private String nombre;
    private String descripcion;
    private int instructorId;

    public Clase() {
    }

    public Clase(int id, String nombre, String descripcion, int instructorId) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.instructorId = instructorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(int instructorId) {
        this.instructorId = instructorId;
    }

    @Override
    public String toString() {
        return "Clase{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", instructorId=" + instructorId +
                '}';
    }


        public static List<Instructor> getAllInstructors() {
            List<Instructor> instructores = new ArrayList<>();
            try (Connection connection = new MYSQLConnection().connect()) {
                String query = "SELECT * FROM Instructores";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query);
                     ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Instructor instructor = new Instructor();
                        instructor.setId(resultSet.getInt("id"));
                        instructor.setNombre(resultSet.getString("nombre"));
                        instructor.setApellidos(resultSet.getString("apellidos"));
                        instructor.setCurp(resultSet.getString("curp"));
                        instructor.setFechaNacimiento(resultSet.getString("fecha_nacimiento"));
                        instructores.add(instructor);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
                // Manejo de excepciones
            }
            return instructores;
        }
    }

