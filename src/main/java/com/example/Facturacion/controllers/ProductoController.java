package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Producto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Facturacion.repository.ProductoRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductoController {

    @Autowired //Inyeccion de dependencias de forma automática
    private ProductoRepository repoProducto; //traigo el repositorio de mis productos creados


    @GetMapping("productos") //en este caso el path es localhost:8080/Productos
    public List<Producto> getProductos(){
        return repoProducto.findAll();
    }

    @PostMapping("alta/producto") // doy de alta un nuevo producto
    public String post(@RequestBody Producto producto) {
        repoProducto.save(producto);
        return "Producto guardado";
    }

    @PutMapping("modificar/producto/{id}")
    public String update(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> optionalProducto = repoProducto.findById(id);

        if (optionalProducto.isPresent()) {
            Producto updateProducto = optionalProducto.get();
            updateProducto.setName(producto.getName());
            updateProducto.setDescription(producto.getDescription());
            updateProducto.setStock(producto.getStock());
            updateProducto.setPrice(producto.getPrice());
            repoProducto.save(updateProducto);
            return "Producto modificado";
        } else {
            return "Producto no encontrado";
        }
    }


    @DeleteMapping("baja/producto/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
        Optional<Producto> optionalProducto = repoProducto.findById(id);

        if (optionalProducto.isPresent()) {
            Producto deleteProducto = optionalProducto.get();
            repoProducto.delete(deleteProducto);
            return ResponseEntity.ok("Producto eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Producto no encontrado");
        }
    }




}