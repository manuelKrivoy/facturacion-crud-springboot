package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Factura;
import com.example.Facturacion.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FacturaController {

    @Autowired //Inyeccion de dependencias de forma automática
    private FacturaRepository repoFactura; //me traigo el repositorio de mis facturas


    @GetMapping("facturas") // devuelvo las factuars existentes
    public List<Factura> getFacturas(){
        return repoFactura.findAll();
    }

    @PostMapping("alta/factura") //cargo una nueva factura
    public String post(@RequestBody Factura factura) {
        repoFactura.save(factura);
        return "Factura guardada";
    }

    @PutMapping("modificar/factura/{id}") //Modifico factura existente
    public String update(@PathVariable Long id, @RequestBody Factura factura) {
        Optional<Factura> optionalFactura = repoFactura.findById(id);

        if (optionalFactura.isPresent()) {
            Factura updateFactura = optionalFactura.get();
            updateFactura.setTotal(factura.getTotal());
            updateFactura.setCreatedAt(factura.getCreatedAt());
            repoFactura.save(updateFactura);
            return "Factura modificada";
        } else {
            return "Factura no encontrada";
        }
    }


    @DeleteMapping("baja/factura/{id}") //Borro factura existente
    public ResponseEntity<String> delete(@PathVariable Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
        Optional<Factura> optionalFactura = repoFactura.findById(id);

        if (optionalFactura.isPresent()) {
            Factura deleteFactura = optionalFactura.get();
            repoFactura.delete(deleteFactura);
            return ResponseEntity.ok("Factura eliminada");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Factura no encontrada");
        }
    }



}
