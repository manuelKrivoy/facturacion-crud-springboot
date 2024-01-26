package com.example.Facturacion.models;

import jakarta.persistence.*;
import lombok.Data;

//Defino la entidad Producto, con sus atributos
@Entity //configuramos para que sea una Entity
@Data //uso lombok para no tener que escribir los getters y setters
public class Producto {
    @Id //Marco que es el id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column() //cambio nombre columna
    private String name;

    @Column()
    private String description;

    @Column()
    private double price;

    @Column()
    private int stock;

}
