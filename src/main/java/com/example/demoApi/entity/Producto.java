package com.example.demoApi.entity;

import com.example.demoApi.validation.CityFormatConstraint;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @CityFormatConstraint //validadores custom
    private String nombre;
    private BigDecimal precio;
    @CityFormatConstraint //validadores custom
    private String ciudad;

    public Producto() {
    }

    public Producto(String nombre, BigDecimal precio, String ciudad) {
        this.nombre= nombre;
        this.precio = precio;
        this.ciudad = ciudad;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public String getCiudad() {
        return ciudad;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", ciudad='" + ciudad + '\'' +
                '}';
    }
}
