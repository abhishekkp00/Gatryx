package com.abhishek.gatryx.analytics.controller;

import com.abhishek.gatryx.analytics.dto.responsedto.HealthResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    private String serviceName;
    private String serviceDescription;


    public HealthController(@Value("${info.app.name}") String serviceName,
                            @Value("${info.app.description}") String getServiceDescription) {
        this.serviceName = serviceName;
        this.serviceDescription = getServiceDescription;
    }

    @GetMapping
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(
                new HealthResponse(serviceName, serviceDescription, "UP")
        );
    }
}
