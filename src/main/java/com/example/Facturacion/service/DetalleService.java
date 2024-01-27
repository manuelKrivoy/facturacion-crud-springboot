package com.example.Facturacion.service;


import com.example.Facturacion.models.Detalle;
import com.example.Facturacion.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleService {
    @Autowired
    private DetalleRepository repoDetalle;

    public List<Detalle> getDetalles(){
        return repoDetalle.findAll();
    }

    public String post( Detalle detalle) {
        repoDetalle.save(detalle);
        return "Detalle guardado";
    }

    @PutMapping("modificar/detalle/{id}")
    public String update(Long id,  Detalle detalle) {
        Optional<Detalle> optionalDetalle = repoDetalle.findById(id);
        if (optionalDetalle.isPresent()) {
            Detalle updateDetalle = optionalDetalle.get();
            updateDetalle.setAmount(detalle.getAmount());
            repoDetalle.save(updateDetalle);
            return "Detalle modificado";
        } else {
            return "Detalle no encontrado";
        }
    }

    public ResponseEntity<String> delete( Long id) {
        Optional<Detalle> optionalDetalle = repoDetalle.findById(id);

        if (optionalDetalle.isPresent()) {
            Detalle deleteDetalle = optionalDetalle.get();
            repoDetalle.delete(deleteDetalle);
            return ResponseEntity.ok("Detalle eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Detalle no encontrado"); //Tambien tira el status not found
        }
    }
}
