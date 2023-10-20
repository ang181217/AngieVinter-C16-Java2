package com.ps.jac16.repository;

import com.ps.jac16.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta,Long> {
    static Cuenta findByNumeroCuenta(String numeroCuenta) {
        return null;
    }

    void deleteByNumeroCuenta(String numeroCuenta);
}