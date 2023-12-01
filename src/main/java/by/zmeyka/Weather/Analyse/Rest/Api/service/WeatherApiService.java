package by.zmeyka.Weather.Analyse.Rest.Api.service;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.exception_handling.WeatherApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherApiService {

    private final String apiUrl = "https://weatherapi-com.p.rapidapi.com/current.json?q=Minsk";

    @Autowired
    private RestTemplate restTemplate;


    public CurrentWeatherDTO getWeatherByMinsk() {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);
            httpHeaders.add("X-RapidAPI-Key", "3c17e614d8msha8eb635f42a3510p13ddb1jsn189745864483");
            httpHeaders.add("X-RapidAPI-Host", "weatherapi-com.p.rapidapi.com");

            HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);


            ResponseEntity<CurrentWeatherDTO> responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    requestEntity,
                    CurrentWeatherDTO.class
            );
            return responseEntity.getBody();

        } catch (Exception e) {
            throw new WeatherApiException("Error fetching weather data from external API");
        }
    }
}
