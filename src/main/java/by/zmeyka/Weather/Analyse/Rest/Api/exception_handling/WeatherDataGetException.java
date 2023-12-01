package by.zmeyka.Weather.Analyse.Rest.Api.exception_handling;

public class WeatherDataGetException extends RuntimeException{
    public WeatherDataGetException(String message) {
        super(message);
    }
}
