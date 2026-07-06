package com.pe.idat.sistema.clinica.sitema_clinica.controller;

import com.pe.idat.sistema.clinica.sitema_clinica.entity.Cita;
import com.pe.idat.sistema.clinica.sitema_clinica.service.CitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cita")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitasService citasService;

    // LISTAR: Filtra las citas basándose en el parámetro '?personaId=X'
    @GetMapping
    public List<Cita> listarPorPersona(@RequestParam Long personaId) {
        return citasService.listaCitasPorPersona(personaId);
    }

    // REGISTRAR: Guarda la cita en la base de datos
    @PostMapping
    public List<Cita> registrar(@RequestBody Cita cita) {
        return citasService.registrarCita(cita);
    }

    // ELIMINAR: Cancela la cita usando el ID enviado en el cuerpo
    @PostMapping("/eliminar")
    public ResponseEntity<Boolean> eliminar(@RequestBody Cita cita) {
        boolean eliminado = citasService.deleteCita(cita);
        return ResponseEntity.ok(eliminado);
    }
}