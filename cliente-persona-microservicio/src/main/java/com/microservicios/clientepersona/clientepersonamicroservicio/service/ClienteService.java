package com.microservicios.clientepersona.clientepersonamicroservicio.service;

import com.microservicios.clientepersona.clientepersonamicroservicio.dto.ClienteDTO;
import com.microservicios.clientepersona.clientepersonamicroservicio.dto.PersonaDTO;
import com.microservicios.clientepersona.clientepersonamicroservicio.entity.Cliente;
import com.microservicios.clientepersona.clientepersonamicroservicio.exceptions.ResourceNotFoundException;
import com.microservicios.clientepersona.clientepersonamicroservicio.repository.ClienteRepository;
import com.microservicios.clientepersona.clientepersonamicroservicio.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PersonaService personaService;

    @Autowired
    private PersonaRepository personaRepository;

    public ClienteDTO crearCliente(ClienteDTO clienteDTO) {
        PersonaDTO personaDTO = personaService.obtenerPersonaPorId(clienteDTO.getPersonaId());
        Cliente cliente = convertirAEntity(clienteDTO, personaDTO);
        clienteRepository.save(cliente);
        return convertirADto(cliente);
    }

    public ClienteDTO actualizarCliente(Long id, ClienteDTO clienteDTO) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        PersonaDTO personaDTO = personaService.obtenerPersonaPorId(clienteDTO.getPersonaId());
        actualizarClienteDesdeDto(cliente, clienteDTO, personaDTO);
        clienteRepository.save(cliente);
        return convertirADto(cliente);
    }

    public List<ClienteDTO> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        if (clientes.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron clientes registrados.");
        }
        return clientes.stream()
                .map(cliente -> convertirADto(cliente)).collect(Collectors.toList());
    }

    public ClienteDTO obtenerClientePorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
        return convertirADto(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cliente no encontrado con id: " + id);
        }
        clienteRepository.deleteById(id);
    }

    private ClienteDTO convertirADto(Cliente cliente) {
        return new ClienteDTO(cliente.getId(), cliente.getClienteId(), cliente.getContrasena(),
                cliente.isEstado(), cliente.getPersona().getId());
    }

    private Cliente convertirAEntity(ClienteDTO clienteDTO, PersonaDTO personaDTO) {
        Cliente cliente = new Cliente();
        cliente.setClienteId(clienteDTO.getClienteId());
        cliente.setContrasena(clienteDTO.getContrasena());
        cliente.setEstado(clienteDTO.isEstado());
        cliente.setPersona(personaService.convertirAEntity(personaDTO));
        return cliente;
    }

    private void actualizarClienteDesdeDto(Cliente cliente, ClienteDTO clienteDTO, PersonaDTO personaDTO) {
        cliente.setClienteId(clienteDTO.getClienteId());
        cliente.setContrasena(clienteDTO.getContrasena());
        cliente.setEstado(clienteDTO.isEstado());
        cliente.setPersona(personaService.convertirAEntity(personaDTO));
    }
}
