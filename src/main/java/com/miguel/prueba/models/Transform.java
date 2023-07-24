package com.miguel.prueba.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transform {

    private String name;
    private boolean useInML;
    private boolean enabled;
    private String jsltExpression;

}
