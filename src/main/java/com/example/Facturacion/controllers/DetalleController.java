package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Detalle;
import com.example.Facturacion.repository.DetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DetalleController {

    @Autowired //Inyeccion de dependencias de forma automática
    private DetalleRepository repoDetalle; //Defino el repositorio de mis detalles para utilizarlo


    @GetMapping("detalles") //traigo detalles
    public List<Detalle> getDetalles(){
        return repoDetalle.findAll();
    }

    @PostMapping("alta/detalle") //cargo un detalle
    public String post(@RequestBody Detalle detalle) {
        repoDetalle.save(detalle);
        return "Detalle guardado";
    }

    @PutMapping("modificar/detalle/{id}")
    public String update(@PathVariable Long id, @RequestBody Detalle detalle) {
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


    @DeleteMapping("baja/detalle/{id}") //borro un detalle
    //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
    public ResponseEntity<String> delete(@PathVariable Long id) {
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
