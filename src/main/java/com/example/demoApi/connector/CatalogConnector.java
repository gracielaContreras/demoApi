package com.example.demoApi.connector;

import com.example.demoApi.connector.configuration.EndpointConfiguration;
import com.example.demoApi.connector.configuration.HostConfiguration;
import com.example.demoApi.connector.configuration.HttpConnectorConfiguration;
import com.example.demoApi.connector.response.CityDTO;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import java.util.concurrent.TimeUnit;

/**
 *    Se encarga de la comunicación con la api
 */
@Component
@Log
public class CatalogConnector {
    @Value("${url.service.flights}")
    private String url;
    @Autowired
    private HttpConnectorConfiguration configuration;
    private final String HOST = "api-catalog";
    private final String ENDPOINT = "get-city";

    public CityDTO getCityDTO(String code) {
        log.info("iniciando llamado al servicio api catalog");

        //Obtenemos la información del Host
        HostConfiguration hostConfiguration = configuration.getHosts().get(HOST);
        EndpointConfiguration endpointConfiguration = hostConfiguration.getEndpoints().get(ENDPOINT);

        HttpClient httpClient = HttpClient.create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, Math.toIntExact(endpointConfiguration.getConnectionTimeout()))
                .doOnConnected(conn -> conn
                        .addHandler(new ReadTimeoutHandler(endpointConfiguration.getReadTimeout(), TimeUnit.MILLISECONDS))
                        .addHandler(new WriteTimeoutHandler(endpointConfiguration.getWriteTimeout(), TimeUnit.MILLISECONDS)));


        WebClient client = WebClient.builder()
                .baseUrl(hostConfiguration.getHost()+":"+hostConfiguration.getPort()+endpointConfiguration.getUrl())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .build();
        return client.get()
                .uri(uriBuilder -> uriBuilder.build(code))
                .retrieve() //obtenemos la información
                .bodyToMono(CityDTO.class)//devuelve un elemento
                .share()
                .block(); //comunicación síncrono
    }

}
