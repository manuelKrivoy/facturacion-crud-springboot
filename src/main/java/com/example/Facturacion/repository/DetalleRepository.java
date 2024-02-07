package com.example.Facturacion.repository;

import com.example.Facturacion.models.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// los repositorios y JpaRepository son componentes
// que facilitan el acceso y la manipulación de datos en una base de datos
public interface DetalleRepository extends JpaRepository<Detalle,Long> {
    List<Detalle> findByFacturaId(Long facturaId);
}
