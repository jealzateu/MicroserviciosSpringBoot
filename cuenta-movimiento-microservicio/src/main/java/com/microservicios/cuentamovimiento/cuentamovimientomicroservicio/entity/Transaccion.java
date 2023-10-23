package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "transacciones")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "descripcion")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "cuenta_id", referencedColumnName = "id")
    private Cuenta cuenta;
}