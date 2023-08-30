create database extra;
use  extra;

-- Crear tabla de roles
CREATE TABLE Roles (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL
);

-- Insertar roles iniciales
INSERT INTO Roles (nombre) VALUES
    ('ADMIN_ROLE'),
    ('INSTRUCTOR_ROLE'),
    ('USER_ROLE');

-- Crear tabla de personas
CREATE TABLE Personas (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    curp VARCHAR(18) NOT NULL,
    fecha_nacimiento DATE NOT NULL
);

-- Crear tabla de usuarios
CREATE TABLE Usuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    persona_id INT NOT NULL,
    correo VARCHAR(100) NOT NULL,
    contraseña VARCHAR(100) NOT NULL,
    estado VARCHAR(20) NOT NULL,
    rol_id INT NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES Personas(id),
    FOREIGN KEY (rol_id) REFERENCES Roles(id)
);

-- Crear tabla de instructores
CREATE TABLE Instructores (
    id INT PRIMARY KEY AUTO_INCREMENT,
    persona_id INT NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES Personas(id)
);

-- Crear tabla de clases
CREATE TABLE Clases (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    instructor_id INT NOT NULL,
    FOREIGN KEY (instructor_id) REFERENCES Instructores(id)
);

-- Crear tabla de temarios
CREATE TABLE Temarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    contenido TEXT,
    clase_id INT NOT NULL,
    FOREIGN KEY (clase_id) REFERENCES Clases(id)
);

-- Crear tabla de ClaseUsuarios
CREATE TABLE ClaseUsuarios (
    id INT PRIMARY KEY AUTO_INCREMENT,
    clase_id INT NOT NULL,
    usuario_id INT NOT NULL,
    FOREIGN KEY (clase_id) REFERENCES Clases(id),
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id)
);

DESCRIBE Personas;


