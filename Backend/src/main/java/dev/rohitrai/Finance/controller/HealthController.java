package dev.rohitrai.Finance.controller;

import dev.rohitrai.Finance.model.PingOutput;
import dev.rohitrai.Finance.service.HealthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/health")
@RequiredArgsConstructor
@RestController
public class HealthController {

    @NonNull
    private HealthService healthService;

    @GetMapping("/ping")
    public ResponseEntity<PingOutput> ping() {

        return healthService.ping();
    }

}
