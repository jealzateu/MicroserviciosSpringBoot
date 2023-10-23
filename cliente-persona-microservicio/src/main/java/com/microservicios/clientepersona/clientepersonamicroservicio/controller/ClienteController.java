package com.microservicios.clientepersona.clientepersonamicroservicio.controller;

import com.microservicios.clientepersona.clientepersonamicroservicio.dto.ClienteDTO;
import com.microservicios.clientepersona.clientepersonamicroservicio.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDTO> crearCliente(@RequestBody ClienteDTO clienteDTO) {
        ClienteDTO clienteCreado = clienteService.crearCliente(clienteDTO);
        return new ResponseEntity<>(clienteCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> actualizarCliente(@PathVariable Long id, @RequestBody ClienteDTO clienteDTO) {
            ClienteDTO clienteActualizado = clienteService.actualizarCliente(id, clienteDTO);
            return new ResponseEntity<>(clienteActualizado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> obtenerTodosLosClientes() {
        List<ClienteDTO> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obtenerClientePorId(id);
        return new ResponseEntity<>(cliente, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
