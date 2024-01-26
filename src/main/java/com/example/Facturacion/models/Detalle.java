package com.example.Facturacion.models;

import jakarta.persistence.*;
import lombok.Data;

//Defino la entidad Detalle, con sus atributos
@Entity //configuramos para que sea una Entity
@Data //uso lombok para no tener que escribir los getters y setters
public class Detalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @ManyToOne
    @JoinColumn(name = "factura_id")
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

}
