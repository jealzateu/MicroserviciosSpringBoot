package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto;

import lombok.Data;

@Data
public class CuentaDTO {
    private Long id;
    private String numeroCuenta;
    private String tipoCuenta;
    private int saldoInicial;
    private boolean estado;
}

