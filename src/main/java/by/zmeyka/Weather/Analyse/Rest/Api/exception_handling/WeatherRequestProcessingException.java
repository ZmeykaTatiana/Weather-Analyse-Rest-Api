package by.zmeyka.Weather.Analyse.Rest.Api.exception_handling;

public class WeatherRequestProcessingException extends RuntimeException{
    public WeatherRequestProcessingException(String message) {
        super(message);
    }
}
