package com.microservicios.clientepersona.clientepersonamicroservicio.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "clienteId", nullable = false)
    private String clienteId;

    @Column(name = "contraseña", nullable = false)
    private String contrasena;

    @Column(name = "estado", nullable = false)
    private boolean estado;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "id")
    private Persona persona;
}

