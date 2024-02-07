package com.example.Facturacion.service;


import com.example.Facturacion.models.Cliente;
import com.example.Facturacion.models.Detalle;
import com.example.Facturacion.models.Factura;
import com.example.Facturacion.models.Producto;
import com.example.Facturacion.repository.ClienteRepository;
import com.example.Facturacion.repository.DetalleRepository;
import com.example.Facturacion.repository.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository repoFactura;
    @Autowired
    private ClienteRepository repoCliente;
    @Autowired
    private DetalleRepository repoDetalle;

    @Autowired
    private DetalleRepository repoProducto;



    public List<Factura> getFacturas(){
        return repoFactura.findAll();
    }

    public String post( Factura factura) {
        factura.setTotal(0); //siempre factura se crea con un valor total = 0
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



    //Metodo para generar comprobante. Quizás el más engorroso de leer pero lo hice así para que saliera bien la información.
    public ResponseEntity<?> getComprobante(Long id) {
        Optional<Factura> optionalFactura = repoFactura.findById(id); // Hago optional para ver si existe la factura con el ID solicitado
        if (optionalFactura.isPresent()) {  //Chequeo la existencia de la factura
            Factura factura = optionalFactura.get(); //al verificar que isPresent es True , genero un objeto de tipo Factura

            Optional<Cliente> optionalCliente = repoCliente.findById(factura.getCliente().getId()); //Mismo procedimiento de Factura pero con Cliente
            //Esto basicamente se hace para poder utilizar el nombre del cliente y no su ID
            if (optionalCliente.isPresent()) {
                Cliente cliente = optionalCliente.get();
                // Traemos todos los detalles correspondientes a la factura buscada
                List<Detalle> detalles = repoDetalle.findByFacturaId(id);
                // Creamos una lista de mapas para almacenar los detalles individualmente
                List<Map<String, Object>> productosComprobante = new ArrayList<>();
                for (Detalle detalle : detalles) {
                    Producto producto = detalle.getProducto();
                    Map<String, Object> detalleMap = new LinkedHashMap<>();
                    detalleMap.put("ID detalle: ", detalle.getId());
                    detalleMap.put("Producto: ", producto.getName());
                    detalleMap.put("Precio Unitario: ", producto.getPrice());
                    detalleMap.put("Cantidad: ", detalle.getAmount());
                    detalleMap.put("Total de este producto: ", producto.getPrice() * detalle.getAmount());
                    // Agregamos el detalle actual al listado de detalles
                    productosComprobante.add(detalleMap);
                }
                //Genero Map del comprobante con los datos ya bien bonitos.
                Map<String, Object> comprobante = new LinkedHashMap<>();
                comprobante.put("ID factura: ", factura.getId());
                comprobante.put("Nombre cliente: ", cliente.getName()); // Agregamos el nombre del cliente
                comprobante.put("Productos: ", productosComprobante);
                comprobante.put("Total:",factura.getTotal());
                //Retorno el comprobante.
                return ResponseEntity.ok(comprobante);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró el cliente asociado a la factura.");
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No existe factura con ID = " + id);
        }
    }


}
