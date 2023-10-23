package com.microservicios.clientepersona.clientepersonamicroservicio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private Long id;
    private String clienteId;
    private String contrasena;
    private boolean estado;
    private Long personaId;
}

