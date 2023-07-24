package com.miguel.prueba.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FeatureConfiguration {

    private Long id;
    private String name;
    private List<Transform> transforms;

}
