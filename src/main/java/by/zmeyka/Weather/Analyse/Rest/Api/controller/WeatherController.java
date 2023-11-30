package by.zmeyka.Weather.Analyse.Rest.Api.controller;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import by.zmeyka.Weather.Analyse.Rest.Api.service.Convert;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherApiService;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    public WeatherApiService weatherApiService;

    @Autowired
    public WeatherService weatherService;
    @Autowired
    public WeatherDataRepository dataRepository;

    @GetMapping("/minsk")
    @Scheduled(fixedRate = 60000000)
    public CurrentWeatherDTO getWeather(){
        CurrentWeatherDTO dto=weatherApiService.getWeatherByMinsk();
        WeatherData weatherData= Convert.convert(dto);
        dataRepository.save(weatherData);
        return dto;
    }

    @GetMapping("/currentInfo")
    public String getCurrentWeather(){
         WeatherData weatherData=weatherService.getCurrentActualInfo();
        StringBuilder result = new StringBuilder();

            result.
                    append("1) Температура ").append(weatherData.getTemperature()).append("<br>")
                    .append("2) Скорость ветра в м/ч ").append(weatherData.getWind_speed()).append("<br>")
                    .append(" 3) Атмосферное давление в  миллибарах ").append(weatherData.getAtmospheric_pressure()).append("<br>")
                    .append(" 4) Влажность воздуха ").append(weatherData.getHumidity()).append("<br>")
                   .append("5) Погодные условия  ").append(weatherData.getWeatherCondition()).append("<br>")
                   .append("6) Локация ").append(weatherData.getLocation());

            return result.toString();


    }





}
