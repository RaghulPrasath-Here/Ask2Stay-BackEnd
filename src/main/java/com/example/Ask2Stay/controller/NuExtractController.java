package com.example.Ask2Stay.controller;
import com.example.Ask2Stay.payload.ExtractionRequest;
import com.example.Ask2Stay.payload.NuExtractResponse;
import com.example.Ask2Stay.service.NuExtractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/extract")
public class NuExtractController {
    @Autowired
    private NuExtractService nuExtractService;

    @PostMapping("/fields")
    public Mono<ResponseEntity<NuExtractResponse>> extractFields(
            @RequestBody ExtractionRequest request) {

        return nuExtractService.extractFields(request.getText(), request.getTemplate())
                .map(response -> {
                    if ("error".equals(response.getStatus())) {
                        return ResponseEntity.badRequest().body(response);
                    }
                    return ResponseEntity.ok(response);
                });
    }

}
