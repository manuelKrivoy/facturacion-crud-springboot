package com.example.Facturacion.service;

import com.example.Facturacion.models.Producto;
import com.example.Facturacion.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository repoProducto;

    public List<Producto> getProductos(){
        return repoProducto.findAll();
    }

    public String post( Producto producto) {
        repoProducto.save(producto);
        return "Producto guardado";
    }

    public String update(Long id, Producto producto) {
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

    public ResponseEntity<String> delete( Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
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
