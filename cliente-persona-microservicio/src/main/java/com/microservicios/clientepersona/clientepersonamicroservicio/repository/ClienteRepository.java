package com.microservicios.clientepersona.clientepersonamicroservicio.repository;

import com.microservicios.clientepersona.clientepersonamicroservicio.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Cliente save(Cliente cliente);

    Optional<Cliente> findById(Long id);

    List<Cliente> findAll();

    void deleteById(Long id);

}
