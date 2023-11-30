package by.zmeyka.Weather.Analyse.Rest.Api.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class WeatherAvgDTO {
    @JsonProperty("from")
    private String from;
    @JsonProperty("to")
    private String to;

}