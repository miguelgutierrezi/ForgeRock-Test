package com.miguel.prueba.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.prueba.models.JsonRequest;
import com.miguel.prueba.services.JSLTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/jslt")
@RequiredArgsConstructor
public class JSLTController {

    private final JSLTService jsltService;
    private final ObjectMapper objectMapper;

    @GetMapping("/status")
    @Operation(
            summary = "Simple endpoint",
            description = "Simple endpoint description",
            tags = {"test"}
    )
    @ApiResponses(
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema())})
    )
    public String getStatus() {
        return "Working...";
    }

    @PostMapping("/process")
    public Map<String, Object> processJson(@RequestBody JsonRequest request) {
        return jsltService.processRequest(request);
    }

}
