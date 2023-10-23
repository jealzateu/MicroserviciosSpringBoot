package com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.service;

import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.dto.CuentaDTO;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.entity.Cuenta;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.exceptions.ResourceNotFoundException;
import com.microservicios.cuentamovimiento.cuentamovimientomicroservicio.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    public CuentaDTO crearCuenta(CuentaDTO cuentaDTO) {
        Cuenta cuenta = convertirAEntity(cuentaDTO);
        cuentaRepository.save(cuenta);
        return convertirADto(cuenta);
    }

    public CuentaDTO actualizarCuenta(Long id, CuentaDTO cuentaDetails) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
        cuentaRepository.save(cuenta);
        return convertirADto(cuenta);
    }

    public List<CuentaDTO> obtenerTodasLasCuentas() {
        List<Cuenta> cuentas = cuentaRepository.findAll();
        if (cuentas.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron cuentas registradas.");
        }
        return cuentas.stream()
                .map(cuenta -> convertirADto(cuenta)).collect(Collectors.toList());
    }

    public CuentaDTO obtenerCuentaPorId(Long id) {
        Cuenta cuenta = cuentaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada con id: " + id));
        return convertirADto(cuenta);
    }

    public void eliminarCuenta(Long id) {
        if (!cuentaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cuenta no encontrada con id: " + id);
        }
        cuentaRepository.deleteById(id);
    }

    private CuentaDTO convertirADto(Cuenta cuenta) {
        CuentaDTO cuentaDTO = new CuentaDTO();
        cuentaDTO.setId(cuenta.getId());
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.isEstado());
        return cuentaDTO;
    }

    private Cuenta convertirAEntity(CuentaDTO cuentaDTO) {
        Cuenta cuenta = new Cuenta();
        cuenta.setId(cuentaDTO.getId());
        cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
        cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
        cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
        cuenta.setEstado(cuentaDTO.isEstado());
        return cuenta;
    }
}
