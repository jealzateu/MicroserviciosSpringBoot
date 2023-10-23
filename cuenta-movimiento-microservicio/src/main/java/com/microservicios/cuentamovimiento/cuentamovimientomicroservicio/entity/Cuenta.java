package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "cuentas")
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numero_cuenta", nullable = false)
    private String numeroCuenta;

    @Column(name = "tipo_cuenta", nullable = false)
    private String tipoCuenta;

    @Column(name = "saldo_inicial", nullable = false)
    private int saldoInicial;

    @Column(name = "estado", nullable = false)
    private boolean estado;
}

