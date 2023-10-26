package com.example.demoApi.controller;

import com.example.demoApi.connector.response.CityDTO;
import com.example.demoApi.controller.resource.ProductoResource;
import com.example.demoApi.entity.Producto;
import com.example.demoApi.services.ProductoService;
import com.example.demoApi.services.ReservationService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/")
@Validated
public class ProductoController implements ProductoResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
    @Autowired
    private ProductoService productoService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<List<Producto>> getAllProductos(){
        LOGGER.info("Obtaining all productos");
        return new ResponseEntity<>(productoService.getAllProducto(), HttpStatus.OK);
    }
    @GetMapping("{id}")
    public ResponseEntity<Producto> getProductosId(@PathVariable Integer id) {
        LOGGER.info("Obtain information from a product with id {}", id);
        return new ResponseEntity<>(productoService.getIdProducto(id), HttpStatus.OK);
    }

    @PostMapping("/crearProducto")
    public ResponseEntity<Producto> create(@RequestBody  @Valid Producto producto){
        return new ResponseEntity<>(productoService.create(producto), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Producto> update(@PathVariable Integer id, @RequestBody Producto producto){
        return new ResponseEntity<>(productoService.update(id, producto), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        LOGGER.info("Deleting product with id {}", id);
        productoService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("code/{code}")
    public ResponseEntity<CityDTO> getCiudad(@PathVariable String code) {
        return new ResponseEntity<>(reservationService.getCity(code), HttpStatus.OK);
    }

}
