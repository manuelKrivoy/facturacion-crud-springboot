package com.example.Facturacion.controllers;

import com.example.Facturacion.models.Cliente;
import com.example.Facturacion.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ClienteController {

    @Autowired //Inyeccion de dependencias de forma automática
    private ClienteRepository repoCliente;

    @GetMapping("clientes") //en este caso el path es localhost:8080/clientes
    public List<Cliente> getClientes(){
        return repoCliente.findAll();
    }

    @PostMapping("alta/cliente") //cargo un nuevo cliente
    public String post(@RequestBody Cliente cliente) {
        repoCliente.save(cliente);
        return "Cliente guardado";
    }

    @PutMapping("modificar/cliente/{id}") //Modifico cliente ya existente
    public String update(@PathVariable Long id, @RequestBody Cliente cliente) {
        Optional<Cliente> optionalCliente = repoCliente.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente updateCliente = optionalCliente.get();
            updateCliente.setName(cliente.getName());
            updateCliente.setEmail(cliente.getEmail());
            repoCliente.save(updateCliente);
            return "Cliente modificado";
        } else {
            return "El id no existe";
        }
    }


    @DeleteMapping("baja/cliente/{id}") //Elimino cliente ya existente
    public ResponseEntity<String> delete(@PathVariable Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
        Optional<Cliente> optionalCliente = repoCliente.findById(id);

        if (optionalCliente.isPresent()) {
            Cliente deleteCliente = optionalCliente.get();
            repoCliente.delete(deleteCliente);
            return ResponseEntity.ok("Cliente eliminado");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente con el ID " + id + " no encontrado");
        }
    }





}