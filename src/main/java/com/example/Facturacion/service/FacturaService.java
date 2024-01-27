package com.example.Facturacion.service;


import com.example.Facturacion.models.Factura;
import com.example.Facturacion.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repoFactura;

    public List<Factura> getFacturas(){
        return repoFactura.findAll();
    }

    public String post( Factura factura) {
        repoFactura.save(factura);
        return "Factura guardada";
    }

    public String update( Long id, Factura factura) {
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

    public ResponseEntity<String> delete( Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
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
