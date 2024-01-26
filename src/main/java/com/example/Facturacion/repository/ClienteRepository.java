package com.example.Facturacion.repository;

import com.example.Facturacion.models.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// los repositorios y JpaRepository son componentes
// que facilitan el acceso y la manipulación de datos en una base de datos
public interface ClienteRepository extends JpaRepository<Cliente,Long> {

}
