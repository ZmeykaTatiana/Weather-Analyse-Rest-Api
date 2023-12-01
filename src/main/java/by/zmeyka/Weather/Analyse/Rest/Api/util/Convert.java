package by.zmeyka.Weather.Analyse.Rest.Api.util;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {

    public CurrentWeatherDTO weatherDTO;


    public static WeatherData convert(CurrentWeatherDTO currentWeatherDTO){
        WeatherData weatherData=new WeatherData();
        weatherData.setTemperature(currentWeatherDTO.getCurrent().getTemperature());
        weatherData.setWind_speed(currentWeatherDTO.getCurrent().getWindMph());
        weatherData.setAtmospheric_pressure(currentWeatherDTO.getCurrent().getPressureMb());
        weatherData.setHumidity(currentWeatherDTO.getCurrent().getHumidity());
        weatherData.setWeatherCondition(currentWeatherDTO.getCurrent().getWeatherCondition().getText());
        weatherData.setLocation(currentWeatherDTO.getLocation().getName());
        String date=currentWeatherDTO.getLocation().getLocaltime();
        Date currentDate=convertToDate(date);
        weatherData.setCreatedAt(currentDate);
        return weatherData;
    }

    public static Date convertToDate(String dateString) {
        String pattern = "yyyy-MM-dd";
        Date date=null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
             date = dateFormat.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date;

    }
    public static Date convertToAnotherFormat(String dateString){
        String pattern = "dd-MM-yyyy";
        Date date=null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            date = dateFormat.parse(dateString);

        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date;

    }


}
