package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Producto;
import com.example.Facturacion.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductoController {

    @Autowired //Inyeccion de dependencias de forma automática
    private ProductoService productoService;


    @GetMapping("productos") //en este caso el path es localhost:8080/Productos
    public List<Producto> getProductos(){
        return productoService.getProductos();
    }

    @PostMapping("alta/producto") // doy de alta un nuevo producto
    public String post(@RequestBody Producto producto) {
        return productoService.post(producto);
    }

    @PutMapping("modificar/producto/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto) {
        return productoService.update(id,producto);
    }


    @DeleteMapping("baja/producto/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
        return  productoService.delete(id);
    }




}