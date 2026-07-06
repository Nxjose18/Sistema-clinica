package com.pe.idat.sistema.clinica.sitema_clinica.controller;

import com.pe.idat.sistema.clinica.sitema_clinica.entity.persona;
import com.pe.idat.sistema.clinica.sitema_clinica.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/persona")
@CrossOrigin(origins = "*") // Evita problemas de CORS al conectar tus páginas HTML
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    // 1. ENDPOINT DE LOGIN: Valida las credenciales contra la BD
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
        String nombre = credenciales.get("usuario");
        String nDocumento = credenciales.get("documento");

        persona usuarioValido = personaService.validarLogin(nombre, nDocumento);

        if (usuarioValido != null) {
            return ResponseEntity.ok(usuarioValido); // 200 OK con los datos del usuario
        } else {
            return ResponseEntity.status(401).body("Usuario o documento incorrectos"); // 401 No Autorizado
        }
    }

    // 2. LISTAR: Trae todas las personas de MySQL
    @GetMapping
    public List<persona> listarTodas() {
        return personaService.listapersona();
    }

    // 3. REGISTRAR: Guarda una nueva persona y retorna la lista actualizada
    @PostMapping
    public List<persona> registrarNueva(@RequestBody persona nuevaPersona) {
        return personaService.registrarPersona(nuevaPersona);
    }

    // 4. ACTUALIZAR: Modifica los datos de una persona por su ID
    @PutMapping
    public ResponseEntity<persona> actualizar(@RequestBody persona personaAEditar) {
        persona actualizada = personaService.updatePersona(personaAEditar);
        if (actualizada != null) {
            return ResponseEntity.ok(actualizada);
        }
        return ResponseEntity.notFound().build();
    }

    // 5. ELIMINAR: Borra un registro de la base de datos
    @DeleteMapping
    public ResponseEntity<String> eliminar(@RequestBody persona personaAEliminar) {
        boolean eliminado = personaService.deletePersona(personaAEliminar);
        if (eliminado) {
            return ResponseEntity.ok("Persona eliminada con éxito");
        }
        return ResponseEntity.badRequest().body("No se pudo eliminar la persona (ID no encontrado o inválido)");
    }
}