package by.zmeyka.Weather.Analyse.Rest.Api.exception_handling;

public class WeatherApiException extends  RuntimeException{
    public WeatherApiException(String message) {
        super(message);
    }
}
