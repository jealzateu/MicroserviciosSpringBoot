package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    Movimiento save(Movimiento movimiento);

    Optional<Movimiento> findById(Long id);

    List<Movimiento> findAll();

    void deleteById(Long id);

}
