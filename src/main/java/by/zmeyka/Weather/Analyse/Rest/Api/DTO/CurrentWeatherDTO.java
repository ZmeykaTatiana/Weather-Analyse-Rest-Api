package by.zmeyka.Weather.Analyse.Rest.Api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
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
        private int temperature;

        @JsonProperty("wind_mph")
        private int windMph;

        @JsonProperty("pressure_mb")
        private int pressureMb;

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
