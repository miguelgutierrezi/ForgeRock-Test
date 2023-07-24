package com.miguel.prueba.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.prueba.UtilsTest;
import com.miguel.prueba.models.JsonRequest;
import com.miguel.prueba.services.JSLTService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JSLTController.class)
public class JSLTControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private JSLTService jsltService;

    @Test
    public void shouldProcessJsltRequest() throws Exception {
        Mockito.when(jsltService.processRequest(any(JsonRequest.class)))
                .thenReturn(new HashMap<>());

        mockMvc.perform(post("/jslt/process")
                .content(objectMapper.writeValueAsString(UtilsTest.buildJsonRequest(true)))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
