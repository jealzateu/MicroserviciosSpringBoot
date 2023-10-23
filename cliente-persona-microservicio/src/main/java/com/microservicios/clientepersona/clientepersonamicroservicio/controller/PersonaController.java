package com.microservicios.clientepersona.clientepersonamicroservicio.controller;

import com.microservicios.clientepersona.clientepersonamicroservicio.dto.PersonaDTO;
import com.microservicios.clientepersona.clientepersonamicroservicio.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public ResponseEntity<List<PersonaDTO>> obtenerTodasLasPersonas() {
        List<PersonaDTO> personas = personaService.obtenerTodasLasPersonas();
        return new ResponseEntity<>(personas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDTO> obtenerPersonaPorId(@PathVariable Long id) {
        PersonaDTO persona = personaService.obtenerPersonaPorId(id);
        return new ResponseEntity<>(persona, HttpStatus.OK);
    }
}
