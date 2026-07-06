package com.pe.idat.sistema.clinica.sitema_clinica.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity // Convierte la clase en la tabla 'citas'
@Table(name = "citas")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Cita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID Autoincremental en MySQL
    private Long id;

    private String dia;
    private String hora;
    private String sede;
    private String doctor;

    // LA UNIÓN: Muchas citas pueden pertenecer a una sola persona
    @ManyToOne
    @JoinColumn(name = "persona_id", nullable = false) // Crea la columna 'persona_id' en la tabla citas
    private persona persona;
}
