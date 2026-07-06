package com.pe.idat.sistema.clinica.sitema_clinica.service;

import com.pe.idat.sistema.clinica.sitema_clinica.entity.persona;
import com.pe.idat.sistema.clinica.sitema_clinica.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonaService {

    // Agrega esto dentro de tu clase PersonaService
    public persona validarLogin(String nombre, String ndocumento) {
        return personaRepository.findByNombreAndNdocumento(nombre, ndocumento)
                .orElse(null); // Devuelve la persona si existe, o null si las credenciales están mal
    }

    // Cambiamos LISTA_PERSISTENTE por la inyección de nuestro repositorio MySQL
    @Autowired
    private PersonaRepository personaRepository;

    // Trae todas las personas registradas en la tabla de MySQL
    public List<persona> listapersona() {
        return personaRepository.findAll();
    }

    // Guarda el registro directo en la BD y te devuelve la lista actualizada
    public List<persona> registrarPersona(persona persona){
        personaRepository.save(persona);
        return personaRepository.findAll();
    }

    // Actualiza los campos en la fila correspondiente buscando por ID
    public persona updatePersona(persona persona){
        if (persona.getId() != null && personaRepository.existsById(persona.getId())) {
            return personaRepository.save(persona); // .save() actualiza si el ID ya existe en MySQL
        }
        return null;
    }

    // Elimina el registro de la BD usando el ID
    public boolean deletePersona(persona persona){
        if (persona == null || persona.getId() == null) {
            return false;
        }
        if (personaRepository.existsById(persona.getId())) {
            personaRepository.deleteById(persona.getId());
            return true;
        }
        return false;
    }
}