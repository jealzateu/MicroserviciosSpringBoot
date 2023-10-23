package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.controller;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto.CuentaDTO;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<CuentaDTO> crearCuenta(@RequestBody CuentaDTO cuentaDTO) {
        CuentaDTO cuentaCreada = cuentaService.crearCuenta(cuentaDTO);
        return new ResponseEntity<>(cuentaCreada, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuentaDTO> actualizarCuenta(@PathVariable Long id, @RequestBody CuentaDTO cuentaDetails) {
        CuentaDTO cuentaActualizada = cuentaService.actualizarCuenta(id, cuentaDetails);
        return new ResponseEntity<>(cuentaActualizada, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CuentaDTO>> obtenerTodasLasCuentas() {
        List<CuentaDTO> cuentas = cuentaService.obtenerTodasLasCuentas();
        return new ResponseEntity<>(cuentas, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CuentaDTO> obtenerCuentaPorId(@PathVariable Long id) {
        CuentaDTO cuenta = cuentaService.obtenerCuentaPorId(id);
        return new ResponseEntity<>(cuenta, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarCuenta(@PathVariable Long id) {
        cuentaService.eliminarCuenta(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
