package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

    Cuenta save(Cuenta cuenta);

    Optional<Cuenta> findById(Long id);

    List<Cuenta> findAll();

    void deleteById(Long id);

}

