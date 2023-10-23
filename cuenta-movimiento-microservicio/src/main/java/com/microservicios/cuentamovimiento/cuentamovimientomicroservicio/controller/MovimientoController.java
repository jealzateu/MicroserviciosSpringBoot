package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.controller;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto.MovimientoDTO;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<MovimientoDTO> crearMovimiento(@RequestBody MovimientoDTO movimientoDTO) {
        MovimientoDTO movimientoCreado = movimientoService.crearMovimiento(movimientoDTO);
        return new ResponseEntity<>(movimientoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovimientoDTO> actualizarMovimiento(@PathVariable Long id, @RequestBody MovimientoDTO movimientoDetails) {
        MovimientoDTO movimientoActualizado = movimientoService.actualizarMovimiento(id, movimientoDetails);
        return new ResponseEntity<>(movimientoActualizado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MovimientoDTO>> obtenerTodosLosMovimientos() {
        List<MovimientoDTO> movimientos = movimientoService.obtenerTodosLosMovimientos();
        return new ResponseEntity<>(movimientos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDTO> obtenerMovimientoPorId(@PathVariable Long id) {
        MovimientoDTO movimiento = movimientoService.obtenerMovimientoPorId(id);
        return new ResponseEntity<>(movimiento, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminarMovimiento(@PathVariable Long id) {
        movimientoService.eliminarMovimiento(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
