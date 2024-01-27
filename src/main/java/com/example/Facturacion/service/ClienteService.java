package com.example.Facturacion.service;

import com.example.Facturacion.models.Cliente;
import com.example.Facturacion.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repoCliente;

    public List<Cliente> getClientes(){
        return repoCliente.findAll();
    }

    public String post( Cliente cliente) {
        repoCliente.save(cliente);
        return "Cliente guardado";
    }

    public String update(Long id, Cliente cliente) {
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

    public ResponseEntity<String> delete( Long id) { //Hice Response Entity para que si no encuentra el id devuelva un 404 con un mensaje personalizado
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
