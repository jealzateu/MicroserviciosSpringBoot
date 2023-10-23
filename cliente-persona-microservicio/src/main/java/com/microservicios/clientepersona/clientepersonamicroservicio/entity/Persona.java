package com.microservicios.clientepersona.clientepersonamicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "personas")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "genero", nullable = false)
    private String genero;

    @Column(name = "edad", nullable = false)
    private int edad;

    @Column(name = "identificacion", nullable = false, unique = true)
    private String identificacion;

    @Column(name = "direccion")
    private String direccion;

    @Column(name = "telefono")
    private String telefono;
}

