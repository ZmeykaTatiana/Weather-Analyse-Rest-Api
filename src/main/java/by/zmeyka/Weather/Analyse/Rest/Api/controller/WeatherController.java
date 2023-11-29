package by.zmeyka.Weather.Analyse.Rest.Api.controller;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import by.zmeyka.Weather.Analyse.Rest.Api.service.Convert;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherApiService weatherApiService;

    @Autowired
    public WeatherDataRepository dataRepository;

    @GetMapping("/minsk")
    @Scheduled(fixedRate = 600000)
    public CurrentWeatherDTO getWeather(){
        CurrentWeatherDTO dto=weatherApiService.getWeatherByMinsk();
        WeatherData weatherData= Convert.convert(dto);
        dataRepository.save(weatherData);
        return dto;
    }



}
