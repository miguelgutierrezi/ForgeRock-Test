package com.miguel.prueba.services.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.miguel.prueba.UtilsTest;
import com.miguel.prueba.exceptions.BusinessException;
import com.miguel.prueba.models.JsonRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class JSLTServiceImplTest {

    @InjectMocks
    private JSLTServiceImpl jsltService;

    @Mock
    private ObjectMapper objectMapper;

    @Test
    public void shouldRunSuccessfullyWholeProcessWithEventId() {
        JsonRequest jsonRequest = UtilsTest.buildJsonRequest(true);
        JsonNodeFactory factory = JsonNodeFactory.instance;

        Map<String, Object> expected = new HashMap<>();
        expected.put("eventId", "878237843");
        expected.put("device_os", "Linux");
        expected.put("device_description", "Linux Laptop");

        ObjectNode device = factory.objectNode();
        device.put("osType", "Linux");
        device.put("model", "Laptop");

        ObjectNode json = factory.objectNode();
        json.put("ip", "10.45.2.30");
        json.put("sessionId", "ads79uoijd098098");
        json.put("device", device);
        json.put("eventId", "878237843");

        Mockito.when(objectMapper.valueToTree(any())).thenReturn(json);

        Map<String, Object> response = jsltService.processRequest(jsonRequest);

        Assertions.assertThat(response).isEqualTo(expected);
    }

    @Test
    public void shouldRunSuccessfullyWholeProcessWithoutEventId() {
        JsonRequest jsonRequest = UtilsTest.buildJsonRequest(false);
        JsonNodeFactory factory = JsonNodeFactory.instance;

        Map<String, Object> expected = new HashMap<>();
        expected.put("device_os", "Linux");
        expected.put("device_description", "Linux Laptop");

        ObjectNode device = factory.objectNode();
        device.put("osType", "Linux");
        device.put("model", "Laptop");

        ObjectNode json = factory.objectNode();
        json.put("ip", "10.45.2.30");
        json.put("sessionId", "ads79uoijd098098");
        json.put("device", device);

        Mockito.when(objectMapper.valueToTree(any())).thenReturn(json);

        Map<String, Object> response = jsltService.processRequest(jsonRequest);

        Assertions.assertThat(response).isEqualTo(expected);
    }

    @Test
    public void shouldThrowExceptionDueToError() {
        JsonRequest jsonRequest = UtilsTest.buildJsonRequest(false);
        Mockito.when(objectMapper.valueToTree(any())).thenThrow(new BusinessException("An error ocurred"));

        Assertions.assertThatThrownBy(() -> jsltService.processRequest(jsonRequest))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("An error occurred while making transforms with JSLT");

    }

}
