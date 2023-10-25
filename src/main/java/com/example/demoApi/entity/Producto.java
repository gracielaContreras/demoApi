package com.example.demoApi.entity;

import com.example.demoApi.validation.CityFormatConstraint;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    public Producto(String nombre, BigDecimal precio, String ciudad) {
        this.nombre= nombre;
        this.precio = precio;
        this.ciudad = ciudad;
    }

}
