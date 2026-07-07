package ar.edu.utn.ba.ddsi.climalert.dtos.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherApiCurrentResponse(
        @JsonProperty("location")
        Location location,

        @JsonProperty("current")
        Current current
) {

    public record Location(
            @JsonProperty("name")
            String name
    ) {
    }

    public record Current(
            @JsonProperty("temp_c")
            double tempC,

            @JsonProperty("humidity")
            int humidity,

            @JsonProperty("condition")
            Condition condition
    ) {
    }

    public record Condition(
            @JsonProperty("text")
            String text
    ) {
    }
}