package com.example.demoApi.services;

import com.example.demoApi.connector.CatalogConnector;
import com.example.demoApi.connector.response.CityDTO;
import com.example.demoApi.enums.ApiError;
import com.example.demoApi.exception.ProductException;
import com.example.demoApi.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private CatalogConnector connector;

    public CityDTO getCity(String code){

        CityDTO city = connector.getCityDTO(code);

        if(city == null){
            throw  new ProductException(ApiError.VALIDATION_ERROR);
        }
        return city;

    }
}
