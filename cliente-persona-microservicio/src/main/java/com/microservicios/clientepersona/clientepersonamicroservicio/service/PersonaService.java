package com.microservicios.clientepersona.clientepersonamicroservicio.service;

import com.microservicios.clientepersona.clientepersonamicroservicio.dto.PersonaDTO;
import com.microservicios.clientepersona.clientepersonamicroservicio.entity.Persona;
import com.microservicios.clientepersona.clientepersonamicroservicio.exceptions.ResourceNotFoundException;
import com.microservicios.clientepersona.clientepersonamicroservicio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<PersonaDTO>obtenerTodasLasPersonas() {
        List<Persona> personas = personaRepository.findAll();
        if (personas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron personas registradas.");
        }
        return personas.stream().map(this::convertirADto).collect(Collectors.toList());
    }

    public PersonaDTO obtenerPersonaPorId(Long id) {
        Persona persona = personaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Persona no encontrada con id: " + id));
        return convertirADto(persona);
    }

    protected PersonaDTO convertirADto(Persona persona) {
        return new PersonaDTO(persona.getId(), persona.getNombre(), persona.getGenero(), persona.getEdad(),
                persona.getIdentificacion(), persona.getDireccion(), persona.getTelefono());
    }

    protected Persona convertirAEntity (PersonaDTO personaDTO) {
        Persona persona = new Persona();
        persona.setId(personaDTO.getId());
        persona.setNombre(personaDTO.getNombre());
        persona.setGenero(personaDTO.getGenero());
        persona.setEdad(personaDTO.getEdad());
        persona.setIdentificacion(personaDTO.getIdentificacion());
        persona.setDireccion(personaDTO.getDireccion());
        persona.setTelefono(personaDTO.getTelefono());

        return persona;
    }
}
