package by.zmeyka.Weather.Analyse.Rest.Api.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class WeatherAvgResponse {
    @JsonProperty("average_temp")
    private int average_temp;
}
