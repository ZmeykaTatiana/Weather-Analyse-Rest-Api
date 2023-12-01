package by.zmeyka.Weather.Analyse.Rest.Api.controller;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.DTO.WeatherAvgDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.DTO.WeatherAvgResponse;
import by.zmeyka.Weather.Analyse.Rest.Api.aspect.LoggingAspect;
import by.zmeyka.Weather.Analyse.Rest.Api.exception_handling.IncorrectDataFormat;
import by.zmeyka.Weather.Analyse.Rest.Api.exception_handling.WeatherDataGetException;
import by.zmeyka.Weather.Analyse.Rest.Api.exception_handling.WeatherRequestProcessingException;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import by.zmeyka.Weather.Analyse.Rest.Api.util.Convert;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherApiService;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    public WeatherApiService weatherApiService;

    @Autowired
    public WeatherService weatherService;
    @Autowired
    public WeatherDataRepository dataRepository;

    public LoggingAspect loggingAspect;


    @GetMapping("/minsk")
    @Scheduled(fixedRate = 60000000)
    public CurrentWeatherDTO getWeather(){
        CurrentWeatherDTO dto=weatherApiService.getWeatherByMinsk();
        WeatherData weatherData= Convert.convert(dto);

        dataRepository.save(weatherData);
        return dto;
    }

    @GetMapping("/currentInfo")
    public String getCurrentWeather() {
        try {
            WeatherData weatherData = weatherService.getCurrentActualInfo();
            StringBuilder result = new StringBuilder();

            result.
                    append("1) Температура ").append(weatherData.getTemperature()).append("<br>")
                    .append("2) Скорость ветра в м/ч ").append(weatherData.getWind_speed()).append("<br>")
                    .append(" 3) Атмосферное давление в  миллибарах ").append(weatherData.getAtmospheric_pressure()).append("<br>")
                    .append(" 4) Влажность воздуха ").append(weatherData.getHumidity()).append("<br>")
                    .append("5) Погодные условия  ").append(weatherData.getWeatherCondition()).append("<br>")
                    .append("6) Локация ").append(weatherData.getLocation());

            return result.toString();

        } catch (Exception e){
            throw new WeatherDataGetException("can't get Weather data ");

        }

    }
    @PostMapping("/avg")
    public @ResponseBody WeatherAvgResponse getAvgTemperature(@RequestBody WeatherAvgDTO weatherAvgDTO){

      try{  int result= weatherService.calculateAvgTemperature(weatherAvgDTO.getFrom(),weatherAvgDTO.getTo());

        WeatherAvgResponse response=new WeatherAvgResponse();
        response.setAverage_temp(result);

        return response;
      }catch (Exception e){
          throw new WeatherRequestProcessingException("Error processing /weather/avg request");
    }


    }


    @ExceptionHandler
    public ResponseEntity<IncorrectDataFormat>handlerException(WeatherRequestProcessingException exception){
        IncorrectDataFormat incorrectDataFormat
                =new IncorrectDataFormat(exception.getMessage());
        return new ResponseEntity<>(incorrectDataFormat, HttpStatus.BAD_REQUEST);


    }





}
