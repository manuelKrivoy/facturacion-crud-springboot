package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Detalle;
import com.example.Facturacion.repository.DetalleRepository;
import com.example.Facturacion.service.DetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DetalleController {

    @Autowired //Inyeccion de dependencias de forma automática
    private DetalleService detalleService;


    @GetMapping("detalles") //traigo detalles
    public List<Detalle> getDetalles(){
        return detalleService.getDetalles();
    }

    @PostMapping("alta/detalle") //cargo un detalle
    public String post(@RequestBody Detalle detalle) {
        return detalleService.post(detalle);
    }

    @PutMapping("modificar/detalle/{id}")
    public String update(@PathVariable Long id, @RequestBody Detalle detalle) {
      return detalleService.update(id,detalle);
    }


    @DeleteMapping("baja/detalle/{id}") //borro un detalle
    //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
    public ResponseEntity<String> delete(@PathVariable Long id) {
    return detalleService.delete(id);
    }



}
