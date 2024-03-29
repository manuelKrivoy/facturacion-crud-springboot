package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Factura;
import com.example.Facturacion.service.FacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FacturaController {

    @Autowired //Inyeccion de dependencias de forma automática
    private FacturaService facturaService;


    @GetMapping("facturas") // devuelvo las factuars existentes
    public List<Factura> getFacturas(){
        return facturaService.getFacturas();
    }

    @PostMapping("alta/factura") //cargo una nueva factura
    public String post(@RequestBody Factura factura) {
        return facturaService.post(factura);
    }

    @PutMapping("modificar/factura/{id}") //Modifico factura existente
    public String update(@PathVariable Long id, @RequestBody Factura factura) {
       return facturaService.update(id,factura);
    }


    @DeleteMapping("baja/factura/{id}") //Borro factura existente
    public ResponseEntity<String> delete(@PathVariable Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
       return facturaService.delete(id);
    }

    @GetMapping("comprobante/{id}") // Genero comprobante de la factura.
    public ResponseEntity<?> getComprobante(@PathVariable Long id){ //es de tipo ? porque puede devolver un objeto (caso exitoso) o un string (caso no exitoso)
        return facturaService.getComprobante(id);
    }


}
