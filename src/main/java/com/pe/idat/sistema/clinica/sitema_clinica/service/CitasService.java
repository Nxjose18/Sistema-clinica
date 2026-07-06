package com.pe.idat.sistema.clinica.sitema_clinica.service;

import com.pe.idat.sistema.clinica.sitema_clinica.entity.Cita;
import com.pe.idat.sistema.clinica.sitema_clinica.repository.CitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CitasService {

    // Cambiamos LISTA_PERSISTENTE y el CONTADOR_ID por el repositorio
    @Autowired
    private CitaRepository citaRepository;

    // Trae todas las citas y automáticamente adjunta los datos de su respectiva persona
    public List<Cita> listaCita() {
        return citaRepository.findAll();
    }

    // Agrega esto dentro de tu clase CitasService
    public List<Cita> listaCitasPorPersona(Long personaId) {
        return citaRepository.findByPersonaId(personaId);
    }

    // Registra la cita vinculada a una persona en MySQL
    public List<Cita> registrarCita(Cita cita){
        citaRepository.save(cita); // MySQL asignará el ID de forma automática e incremental
        return citaRepository.findAll();
    }

    // Actualiza la cita en la base de datos
    public Cita updateCita(Cita cita){
        if (cita.getId() != null && citaRepository.existsById(cita.getId())) {
            return citaRepository.save(cita);
        }
        return null;
    }

    // Elimina la cita de MySQL
    public boolean deleteCita(Cita cita){
        if (cita == null || cita.getId() == null) {
            return false;
        }
        if (citaRepository.existsById(cita.getId())) {
            citaRepository.deleteById(cita.getId());
            return true;
        }
        return false;
    }
}