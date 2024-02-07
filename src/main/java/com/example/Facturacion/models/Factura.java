package com.example.Facturacion.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalTime;

//Defino la entidad Factura, con sus atributos
@Entity //configuramos para que sea una Entity
@Data //uso lombok para no tener que escribir los getters y setters
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private String createdAt; //utilizo LocalDate para que almacene la fecha en la que la factura es creada

    private double total;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @PrePersist
    protected void onCreate() {
        // Realizamos una solicitud GET a la API externa timeapi.io
        RestTemplate restTemplate = new RestTemplate();
        final String apiUrl = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires"; // Uso URL para la hora de Buenos Aires
        TimeApi tiempo = restTemplate.getForObject(apiUrl,TimeApi.class);

        // Almacenamos la hora obtenida en createdAt
        if (tiempo != null && tiempo.getDateTime() != null) {
            createdAt = tiempo.getDateTime();
        } else {
            createdAt = LocalTime.now().toString(); //Si no hay respuesta o es mala almacenamos la hora de otra forma. (LocalDate)
        }
    }
}


