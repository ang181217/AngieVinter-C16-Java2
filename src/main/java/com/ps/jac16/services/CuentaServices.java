package com.ps.jac16.services;


import com.ps.jac16.model.Cuenta;
import com.ps.jac16.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.List;


@Service
public class CuentaServices {

    @Autowired
    CuentaRepository cuentaRepository;

    public Cuenta save(Cuenta cuenta) throws Exception {
        Cuenta cuentaExistente = CuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());

        if (cuentaExistente != null) {
            throw new Exception("Existe una cuenta asociada al numero: " + cuenta.getNumeroCuenta());
        }
        if (!StringUtils.isNumeric(cuenta.getNumeroCuenta())) {
            throw new Exception("La cuenta debe ser numerica.");
        }

        if (cuenta.getSaldo().compareTo(BigDecimal.ZERO) < 0) {
            throw new Exception("El saldo de la cuenta debe ser mayor a 0" + cuenta.getSaldo());
        }

        return cuentaRepository.save(cuenta);
    }

    public Cuenta get(Long idCuenta) throws Exception {

        return cuentaRepository.getReferenceById(idCuenta);
    }

    public Cuenta update(Cuenta cuenta) throws Exception {
        Cuenta cuentaExistente = CuentaRepository.findByNumeroCuenta(cuenta.getNumeroCuenta());
        if (cuentaExistente == null) {
            throw new Exception("No existe una cuenta asociada al numero: " + cuenta.getNumeroCuenta());
        }

        cuentaExistente.setSaldo(cuenta.getSaldo());

        return cuentaRepository.save(cuentaExistente);
    }

    public Object delete(String cuenta) {
        cuentaRepository.deleteByNumeroCuenta(cuenta);
        return null;
    }


    public Cuenta findByNumberCount(String numeroCuenta) {

        return CuentaRepository.findByNumeroCuenta(numeroCuenta);
    }

    public List<Cuenta> findByAll() {
        return cuentaRepository.findAll();
    }

    public Object get(String numeroCuenta) {
        return null;
    }
}