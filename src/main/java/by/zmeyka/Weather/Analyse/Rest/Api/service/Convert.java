package by.zmeyka.Weather.Analyse.Rest.Api.service;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;

import java.util.Date;

public class Convert {

    public CurrentWeatherDTO weatherDTO;


    public static WeatherData convert(CurrentWeatherDTO currentWeatherDTO){
        WeatherData weatherData=new WeatherData();
        weatherData.setTemperature(currentWeatherDTO.getCurrent().getTemperature());
        weatherData.setWind_speed(currentWeatherDTO.getCurrent().getWindMph());
        weatherData.setAtmospheric_pressure(currentWeatherDTO.getCurrent().getPressureMb());
        weatherData.setHumidity(currentWeatherDTO.getCurrent().getHumidity());
        weatherData.setWeatherCondition(null);
        weatherData.setLocation(currentWeatherDTO.getLocation().getName());
        weatherData.setCreated_at(currentWeatherDTO.getLocation().getLocaltime());
        return weatherData;
    }
}
