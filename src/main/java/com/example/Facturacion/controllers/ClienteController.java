package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Cliente;
import com.example.Facturacion.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ClienteController {

    @Autowired //Inyeccion de dependencias de forma automática
    private ClienteService clienteService;

    @GetMapping("clientes") //en este caso el path es localhost:8080/clientes
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @PostMapping("alta/cliente") //cargo un nuevo cliente
    public String post(@RequestBody Cliente cliente) {
       return clienteService.post(cliente);
    }

    @PutMapping("modificar/cliente/{id}") //Modifico cliente ya existente
    public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
        return clienteService.update(id,cliente);
    }


    @DeleteMapping("baja/cliente/{id}") //Elimino cliente ya existente
    public ResponseEntity<String> delete(@PathVariable Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
        return clienteService.delete(id);
    }

}