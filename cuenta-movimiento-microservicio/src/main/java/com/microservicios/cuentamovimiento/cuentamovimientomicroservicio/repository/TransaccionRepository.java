package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    Transaccion save(Transaccion transaccion);

}
