package dev.rohitrai.Finance.service;

import dev.rohitrai.Finance.model.PingOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    public ResponseEntity<PingOutput> ping() {

        return ResponseEntity.ok(PingOutput.builder()
                .status("success")
                .build());
    }
}
