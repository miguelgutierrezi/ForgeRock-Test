package com.miguel.prueba;

import com.miguel.prueba.models.FeatureConfiguration;
import com.miguel.prueba.models.JsonRequest;
import com.miguel.prueba.models.Transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UtilsTest {

    public static JsonRequest buildJsonRequest(boolean withEventId) {
        Transform transform1 = Transform.builder()
                .enabled(true)
                .jsltExpression(".device.osType")
                .name("device_os")
                .useInML(true)
                .build();

        Transform transform2 = Transform.builder()
                .enabled(true)
                .jsltExpression(".device.osType + \" \" + .device.model")
                .name("device_description")
                .useInML(true)
                .build();

        FeatureConfiguration featureConfiguration = FeatureConfiguration.builder()
                .id(1L)
                .name("DeviceFeatures")
                .transforms(List.of(transform1, transform2))
                .build();

        Map<String, String> device = new HashMap<>();
        device.put("osType", "Linux");
        device.put("model", "Laptop");

        Map<String, Object> json = new HashMap<>();
        json.put("ip", "10.45.2.30");
        json.put("sessionId", "ads79uoijd098098");
        json.put("device", device);

        if (withEventId) {
            json.put("eventId", "878237843");
        }

        return JsonRequest.builder()
                .featureConfiguration(featureConfiguration)
                .json(json)
                .build();
    }

}
