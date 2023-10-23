package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "movimientos")
public class Movimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "tipo_movimiento", nullable = false)
    private String tipoMovimiento;

    @Column(name = "valor", nullable = false)
    private int valor;

    @Column(name = "saldo", nullable = false)
    private int saldo;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Cuenta cuenta;
}

