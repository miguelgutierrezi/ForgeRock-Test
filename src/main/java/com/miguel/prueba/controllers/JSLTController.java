package com.miguel.prueba.controllers;

import com.miguel.prueba.models.JsonRequest;
import com.miguel.prueba.services.JSLTService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/process")
    @Operation(
            summary = "Processing transforms",
            description = "Endpoint for making processing of configurations with jslt",
            tags = {"Processing transforms"}
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successful result in a Map"),
            @ApiResponse(responseCode = "400", description = "An error occurred")
    })
    public Map<String, Object> processJson(@RequestBody JsonRequest request) {
        return jsltService.processRequest(request);
    }

}
