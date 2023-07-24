package com.miguel.prueba.services;

import com.miguel.prueba.models.JsonRequest;

import java.util.Map;

public interface JSLTService {

    Map<String, Object> processRequest(JsonRequest request);

}
