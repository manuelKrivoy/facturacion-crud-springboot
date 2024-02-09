package com.example.Facturacion.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.web.client.RestTemplate;

import java.time.LocalTime;

@Entity
@Data
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private String createdAt;

    private double total = 0.0; // Valor por defecto para total

    private int cantidadProductos = 0; // Valor por defecto para cantidadProductos

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @PrePersist
    protected void onCreate() {
        RestTemplate restTemplate = new RestTemplate();
        final String apiUrl = "https://timeapi.io/api/Time/current/zone?timeZone=America/Argentina/Buenos_Aires";
        TimeApi tiempo = restTemplate.getForObject(apiUrl,TimeApi.class);

        if (tiempo != null && tiempo.getDateTime() != null) {
            createdAt = tiempo.getDateTime();
        } else {
            createdAt = LocalTime.now().toString();
        }
    }

}

