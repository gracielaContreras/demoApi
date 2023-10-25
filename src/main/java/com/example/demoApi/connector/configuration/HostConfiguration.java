package com.example.demoApi.connector.configuration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.HashMap;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HostConfiguration {
    private String host;
    private int port;
    private HashMap<String, EndpointConfiguration> endpoints;
}
