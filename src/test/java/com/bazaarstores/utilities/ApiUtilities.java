package com.bazaarstores.utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiUtilities {

    public static RequestSpecification spec() {
        return new RequestSpecBuilder()
                .setBaseUri(ConfigReader.getApiBaseUrl())
                .setContentType(ContentType.JSON)
                .addHeader("Accept", "application/json")
                .addHeader("Authorization", "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJodHRwczovL2JhemFhcnN0b3Jlcy5jb20vYXBpL2xvZ2luIiwiaWF0IjoxNzYyMzI4ODQ3LCJleHAiOjE3NjIzMzI0NDcsIm5iZiI6MTc2MjMyODg0NywianRpIjoiNmptRnFVYkVrbE1KZmoydCIsInN1YiI6IjM1NCIsInBydiI6IjIzYmQ1Yzg5NDlmNjAwYWRiMzllNzAxYzQwMDg3MmRiN2E1OTc2ZjcifQ.7dzHfBHoDkFfSzT2IlMotN9rcnnaX9ZaWj8AakBBq6o")
                .build();
    }


}
