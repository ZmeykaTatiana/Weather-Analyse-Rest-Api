package by.zmeyka.Weather.Analyse.Rest.Api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentWeatherDTO {
    @JsonProperty("location")
    private Location location;
    @JsonProperty("current")
    private Current current;

    @Data
    public static class Location {
        @JsonProperty("name")
        private String name;
        @JsonProperty("localtime")
        private String localtime;


        }


    @Data
    public static class Current {
        @JsonProperty("temp_c")
        private double temperature;

        @JsonProperty("wind_mph")
        private double windMph;

        @JsonProperty("pressure_mb")
        private double pressureMb;

        @JsonProperty("humidity")
        private int humidity;
        @JsonProperty("condition")
        private Condition weatherCondition;
        @Data
        public static class Condition{
            @JsonProperty("text")
            private String text;
        }

    }


}
