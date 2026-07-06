package com.pe.idat.sistema.clinica.sitema_clinica.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "personas") // Define el nombre de la tabla
@Data
@NoArgsConstructor
@AllArgsConstructor

public class persona {
    @Id // Define la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Hace que el ID sea autoincremental (1, 2, 3...)
    private Long id;

    private String ndocumento;
    private String apellido;
    private String nombre;
    private String direccion;

}
