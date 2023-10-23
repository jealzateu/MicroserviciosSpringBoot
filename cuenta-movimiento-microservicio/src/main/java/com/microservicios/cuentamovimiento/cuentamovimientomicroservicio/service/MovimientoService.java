package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.service;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto.CuentaDTO;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto.MovimientoDTO;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Cuenta;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Movimiento;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Transaccion;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.exceptions.ResourceNotFoundException;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.exceptions.SaldoInsuficienteException;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository.CuentaRepository;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository.MovimientoRepository;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository.TransaccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private TransaccionRepository transaccionRepository;

    public MovimientoDTO crearMovimiento(MovimientoDTO movimientoDTO) {
        Cuenta cuenta = cuentaRepository.findById(movimientoDTO.getCuentaId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + movimientoDTO.getCuentaId()));
        if (movimientoDTO.getTipoMovimiento().equals("Retiro") && cuenta.getSaldoInicial() < movimientoDTO.getValor()) {
            throw new SaldoInsuficienteException("Saldo no disponible");
        }

        Movimiento movimiento = convertirAEntity(movimientoDTO);
        movimientoRepository.save(movimiento);

        actualizarSaldoDisponible(movimientoDTO);
        registrarTransaccion(movimientoDTO, cuenta);

        return convertirADto(movimiento);
    }

    private void registrarTransaccion(MovimientoDTO movimientoDTO, Cuenta cuenta) {
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(new Date());
        transaccion.setDescripcion("Se realizó un movimiento de tipo " + movimientoDTO.getTipoMovimiento() +
                " por un valor de " + movimientoDTO.getValor());
        transaccion.setCuenta(cuenta);

        transaccionRepository.save(transaccion);
    }

    private void actualizarSaldoDisponible(MovimientoDTO movimientoDTO) {
        CuentaDTO cuentaDTO = cuentaService.obtenerCuentaPorId(movimientoDTO.getCuentaId());

        if (movimientoDTO.getTipoMovimiento().equals("Deposito")) {
            cuentaDTO.setSaldoInicial(cuentaDTO.getSaldoInicial() + movimientoDTO.getValor());
        } else if (movimientoDTO.getTipoMovimiento().equals("Retiro")) {
            cuentaDTO.setSaldoInicial(cuentaDTO.getSaldoInicial() - movimientoDTO.getValor());
        }

        cuentaService.actualizarCuenta(cuentaDTO.getId(), cuentaDTO);
    }

    public MovimientoDTO actualizarMovimiento(Long id, MovimientoDTO movimientoDetails) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id: " + id));
        movimientoRepository.save(movimiento);
        return convertirADto(movimiento);
    }

    public List<MovimientoDTO> obtenerTodosLosMovimientos() {
        List<Movimiento> movimientos = movimientoRepository.findAll();
        if (movimientos.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron movimientos registrados.");
        }
        return movimientos.stream()
                .map(movimiento -> convertirADto(movimiento)).collect(Collectors.toList());
    }

    public MovimientoDTO obtenerMovimientoPorId(Long id) {
        Movimiento movimiento = movimientoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movimiento no encontrado con id: " + id));
        return convertirADto(movimiento);
    }

    public void eliminarMovimiento(Long id) {
        if (!movimientoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Movimiento no encontrado con id: " + id);
        }
        movimientoRepository.deleteById(id);
    }

    private MovimientoDTO convertirADto(Movimiento movimiento) {
        MovimientoDTO movimientoDTO = new MovimientoDTO();
        movimientoDTO.setId(movimiento.getId());
        movimientoDTO.setFecha(movimiento.getFecha());
        movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDTO.setValor(movimiento.getValor());
        movimientoDTO.setSaldo(movimiento.getSaldo());
        return movimientoDTO;
    }

    private Movimiento convertirAEntity(MovimientoDTO movimientoDTO) {
        Movimiento movimiento = new Movimiento();
        movimiento.setId(movimientoDTO.getId());
        movimiento.setFecha(movimientoDTO.getFecha());
        movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
        movimiento.setValor(movimientoDTO.getValor());
        movimiento.setSaldo(movimientoDTO.getSaldo());
        return movimiento;
    }
}
