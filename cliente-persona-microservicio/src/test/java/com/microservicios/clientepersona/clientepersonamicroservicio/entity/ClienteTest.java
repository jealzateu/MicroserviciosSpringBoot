package com.microservicios.clientepersona.clientepersonamicroservicio.entity;

import com.microservicios.clientepersona.clientepersonamicroservicio.dto.PersonaDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClienteTest {

    @Test
    public void whenCreatingCliente_thenReturnCorrectValues() {

        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setClienteId("cliente1");
        cliente.setContrasena("password");
        cliente.setEstado(true);

        Persona persona = new Persona();
        persona.setId(2L);
        persona.setNombre("Juan");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("123456789");
        persona.setDireccion("Quito");
        persona.setTelefono("0987654321");

        cliente.setPersona(persona);

        Long expectedId = 1L;
        String expectedClienteId = "cliente1";
        String expectedContrasena = "password";
        boolean expectedEstado = true;

        PersonaDTO expectedPersonaDTO = new PersonaDTO(2L, "Juan", "Masculino", 30, "123456789", "Quito", "0987654321");

        assertEquals(expectedId, cliente.getId());
        assertEquals(expectedClienteId, cliente.getClienteId());
        assertEquals(expectedContrasena, cliente.getContrasena());
        assertEquals(expectedEstado, cliente.isEstado());
        assertEquals(expectedPersonaDTO.getId(), cliente.getPersona().getId());
        assertEquals(expectedPersonaDTO.getNombre(), cliente.getPersona().getNombre());
        assertEquals(expectedPersonaDTO.getGenero(), cliente.getPersona().getGenero());
        assertEquals(expectedPersonaDTO.getEdad(), cliente.getPersona().getEdad());
        assertEquals(expectedPersonaDTO.getIdentificacion(), cliente.getPersona().getIdentificacion());
        assertEquals(expectedPersonaDTO.getDireccion(), cliente.getPersona().getDireccion());
        assertEquals(expectedPersonaDTO.getTelefono(), cliente.getPersona().getTelefono());
    }

}
