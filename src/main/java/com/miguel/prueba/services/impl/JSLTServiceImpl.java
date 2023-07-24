package com.miguel.prueba.services.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miguel.prueba.exceptions.BusinessException;
import com.miguel.prueba.models.JsonRequest;
import com.miguel.prueba.models.Transform;
import com.miguel.prueba.services.JSLTService;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class JSLTServiceImpl implements JSLTService {

    private final ObjectMapper objectMapper;

    @Override
    public Map<String, Object> processRequest(JsonRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            JsonNode jsonNode = objectMapper.valueToTree(request.getJson());

            processEventId(response, jsonNode);
            request.getFeatureConfiguration()
                    .getTransforms()
                    .forEach(transform -> processTransform(response, jsonNode, transform));

        } catch (Exception e) {
            log.error("An error occurred while making transforms with JSLT.", e);
            throw new BusinessException("An error occurred while making transforms with JSLT.");
        }
        return response;
    }

    private void processEventId(Map<String, Object> response, JsonNode jsonNode) {
        JsonNode eventNode = jsonNode.get("eventId");
        if (Objects.nonNull(eventNode))
            response.put("eventId", eventNode.asText());
    }

    private void processTransform(Map<String, Object> response, JsonNode jsonNode, Transform transform) {
        Expression jslt = Parser.compileString(transform.getJsltExpression());
        JsonNode res = jslt.apply(jsonNode);
        response.put(transform.getName(), res.asText());
    }

}
