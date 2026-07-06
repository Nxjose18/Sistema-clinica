package com.pe.idat.sistema.clinica.sitema_clinica.repository;
import com.pe.idat.sistema.clinica.sitema_clinica.entity.persona;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<persona, Long> {
    Optional<persona> findByNombreAndNdocumento(String nombre, String ndocumento);
}
