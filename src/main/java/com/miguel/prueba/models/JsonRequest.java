package com.miguel.prueba.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JsonRequest {

    FeatureConfiguration featureConfiguration;
    Map<String, Object> json;

}
