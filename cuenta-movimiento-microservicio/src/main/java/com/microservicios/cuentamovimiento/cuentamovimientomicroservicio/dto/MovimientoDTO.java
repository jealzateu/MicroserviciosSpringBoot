package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto;

import lombok.Data;

import java.util.Date;

@Data
public class MovimientoDTO {
    private Long id;
    private Date fecha;
    private String tipoMovimiento;
    private int valor;
    private int saldo;
    private Long cuentaId;
}

