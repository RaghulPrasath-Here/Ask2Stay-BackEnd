package com.example.Ask2Stay.service;
import com.example.Ask2Stay.config.NuExtractConfig;
import com.example.Ask2Stay.payload.NuExtractRequest;
import com.example.Ask2Stay.payload.NuExtractResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;

public class NuExtractService {
    private final WebClient webClient;
    private final NuExtractConfig config;

    @Autowired
    public NuExtractService(NuExtractConfig config) {
        this.config = config;
        this.webClient = WebClient.builder()
                .baseUrl(config.getBaseUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + config.getApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }
    public Mono<NuExtractResponse> extractFields(String text, Map<String, Object> template) {
        NuExtractRequest request = new NuExtractRequest(text, template);

        return webClient.post()
                .uri("/extract")
                .bodyValue(request)
                .retrieve()
                .bodyToMono(NuExtractResponse.class)
                .retryWhen(Retry.backoff(config.getMaxRetries(), Duration.ofSeconds(1))
                        .filter(throwable -> !(throwable instanceof WebClientResponseException.BadRequest)))
                .timeout(Duration.ofMillis(config.getTimeout()));
    }
    public NuExtractResponse extractFieldsSync(String text, Map<String, Object> template) {
        return extractFields(text, template).block();
    }
}
