package com.example.Facturacion.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {
    @GetMapping //determino path de endpoint y es solititud get
    public  String index(){
        return "Conectado";
    }


}