package com.microservicios.clientepersona.clientepersonamicroservicio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.microservicios.clientepersona.clientepersonamicroservicio.entity.Persona;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    List<Persona> findAll();

    Optional<Persona> findById(Long id);

}

