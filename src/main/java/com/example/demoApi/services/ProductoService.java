package com.example.demoApi.services;

import com.example.demoApi.entity.Producto;
import com.example.demoApi.enums.ApiError;
import com.example.demoApi.exception.ProductException;
import com.example.demoApi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> getAllProducto() {
       return productoRepository.findAll();
    }

    public Producto create(Producto producto) {
        if(Objects.nonNull(producto.getId())){
           throw new ProductException(ApiError.PRODUCT_WITH_SAME_ID);
        }
        Producto nuevo = productoRepository.save(producto);
        return nuevo;
    }

    public Producto update(Integer id, Producto producto) {
        Optional<Producto> result = productoRepository.findById(id);

        if(result.isPresent()){
            Producto productoActualizado = productoRepository.save(producto);
            return productoActualizado;
        }else {
            throw new ProductException(ApiError.PRODUCT_NOT_FOUND);
        }
    }

    public void delete(Integer id) {
        Optional<Producto> result = productoRepository.findById(id);

        if (result.isPresent()){
            productoRepository.delete(result.get());
        } else {
            throw new ProductException(ApiError.PRODUCT_NOT_FOUND);
        }
    }

    public Producto getIdProducto(Integer id) {
      return productoRepository.findById(id)
               .orElseThrow(() -> new ProductException(ApiError.PRODUCT_WITH_SAME_ID));
    }
}
