package com.example.demoApi.connector.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EndpointConfiguration {
    private String url;
    private int readTimeout;
    private int writeTimeout;
    private int connectionTimeout;
}
