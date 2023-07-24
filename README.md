# How to run the application

## Build

To build the application, use the following command in the root of the project:
```
./gradlew clean build
```

## Test

For executing the unit tests of the application, use the following command in the root of the project:
```
./gradlew test
```

## Execute the application

To execute the application, use the following command in the root of the project:
```
./gradlew bootRun
```

It will launch the application on port 8080. You can access the Swagger UI at the following URL:
http://localhost:8080/swagger-ui/index.html#/

## Documenting endpoints

There's just one endpoint under `/jslt/process`. And it should be used as follows:
```
POST /jslt/process
Example body
{
    "featureConfiguration": {
        "id": 1,
        "name": "DeviceFeatures",
        "transforms": [
            {
                "name": "device_os",
                "useInML": true,
                "enabled": true,
                "jsltExpression": ".device.osType"
            },
            {
                "name": "device_description",
                "useInML": true,
                "enabled": true,
                "jsltExpression": ".device.osType + \" \" + .device.model"
            }
        ]
    },
    "json": {
        "eventId": "878237843",
        "device": {
            "osType": "Linux",
            "model": "Laptop"
        },
        "ip": "10.45.2.30",
        "sessionId": "ads79uoijd098098"
    }
}
```

The featureConfiguration and json properties are fundamental for the application to work.