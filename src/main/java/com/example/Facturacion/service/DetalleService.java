package com.example.Facturacion.service;


import com.example.Facturacion.models.Detalle;
import com.example.Facturacion.models.Factura;
import com.example.Facturacion.models.Producto;
import com.example.Facturacion.repository.DetalleRepository;
import com.example.Facturacion.repository.FacturaRepository;
import com.example.Facturacion.repository.ProductoRepository;
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
    @Autowired
    private FacturaService facturaService;
    @Autowired
    private FacturaRepository repoFactura;
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ProductoRepository repoProducto;

    public List<Detalle> getDetalles(){
        return repoDetalle.findAll();
    }

    public String post(Detalle detalle) {
        Optional<Factura> optionalActualizarFactura = repoFactura.findById(detalle.getFactura().getId());
        Optional<Producto> optionalActualizarStockProducto = repoProducto.findById(detalle.getProducto().getId());
        if (optionalActualizarFactura.isPresent() && optionalActualizarStockProducto.isPresent()) {
            Producto productoElegido = optionalActualizarStockProducto.get();
            if(productoElegido.getStock() > detalle.getAmount()){
                Factura actualizarFactura = optionalActualizarFactura.get();
                // Actualizo valor factura y sumo la cantidad de productos agregados
                actualizarFactura.setTotal(actualizarFactura.getTotal() + detalle.getAmount() * productoElegido.getPrice());
                actualizarFactura.setCantidadProductos(actualizarFactura.getCantidadProductos() + detalle.getAmount());
                repoDetalle.save(detalle);
                //Actualizo stock producto
                productoElegido.setStock(productoElegido.getStock()- detalle.getAmount());
                // Guardar los cambios en las entidades actualizadas
                repoFactura.save(actualizarFactura);
                repoProducto.save(productoElegido);
                return "Detalle guardado";
            }
            else{
                return "No hay suficiente stock disponible";
            }}
            else {
             return "Factura o Producto no encontrados";
        }}
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
