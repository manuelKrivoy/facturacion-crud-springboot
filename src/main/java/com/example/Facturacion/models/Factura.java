package com.example.Facturacion.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

//Defino la entidad Factura, con sus atributos
@Entity //configuramos para que sea una Entity
@Data //uso lombok para no tener que escribir los getters y setters
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDate createdAt; //utilizo LocalDate para que almacene la fecha en la que la factura es creada

    private double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}


