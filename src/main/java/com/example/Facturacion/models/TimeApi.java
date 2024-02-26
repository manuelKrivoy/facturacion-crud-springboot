package com.example.Facturacion.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TimeApi { //Creo clase TimeApi para almacenar la información de la api y poder manejarla de manera eficiente.
    @JsonProperty("dateTime")
    private String dateTime;

    @JsonProperty("timezone")
    private String timezone;

    @JsonProperty("unixTime")
    private long unixTime;
}

