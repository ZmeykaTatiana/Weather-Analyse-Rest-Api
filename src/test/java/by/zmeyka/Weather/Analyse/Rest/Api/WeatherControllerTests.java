package by.zmeyka.Weather.Analyse.Rest.Api;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.DTO.WeatherAvgDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.DTO.WeatherAvgResponse;
import by.zmeyka.Weather.Analyse.Rest.Api.controller.WeatherController;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherApiService;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WeatherControllerTests {

    @Mock
    private WeatherApiService weatherApiService;

    @Mock
    private WeatherService weatherService;

    @Mock
    private WeatherDataRepository dataRepository;

    @InjectMocks
    private WeatherController weatherController;


    @Test
    public void testGetWeather() {
        CurrentWeatherDTO mockWeatherDTO = new CurrentWeatherDTO();
        CurrentWeatherDTO.Location location=new CurrentWeatherDTO.Location("Minsk","01-12-2023");
        CurrentWeatherDTO.Current.Condition condition=new CurrentWeatherDTO.Current.Condition("cloudy");
        CurrentWeatherDTO.Current current=new CurrentWeatherDTO.Current(15,150,1005,18,condition);

        mockWeatherDTO.setLocation(location);
        mockWeatherDTO.setCurrent(current);
        when(weatherApiService.getWeatherByMinsk()).thenReturn(mockWeatherDTO);


        CurrentWeatherDTO result = weatherController.getWeather();


        verify(weatherApiService, times(1)).getWeatherByMinsk();


        verify(dataRepository, times(1)).save(any());


        assertEquals(mockWeatherDTO, result);
    }

    @Test
    public void testGetCurrentWeather() {

        WeatherData mockWeatherData = new WeatherData();
        when(weatherService.getCurrentActualInfo()).thenReturn(mockWeatherData);


        String result = weatherController.getCurrentWeather();


        verify(weatherService, times(1)).getCurrentActualInfo();


        assertEquals(true, result.contains("Температура"));


    }

    @Test
    public void testGetAvgTemperature() {

        int mockResult = 10;
        WeatherAvgDTO mockWeatherAvgDTO = new WeatherAvgDTO();
        mockWeatherAvgDTO.setFrom("28-11-2023");
        mockWeatherAvgDTO.setTo("30-11-2023");
        when(weatherService.calculateAvgTemperature(anyString(), anyString())).thenReturn(mockResult);


        WeatherAvgResponse response = weatherController.getAvgTemperature(mockWeatherAvgDTO);


        verify(weatherService, times(1)).calculateAvgTemperature(anyString(), anyString());


        assertEquals(mockResult, response.getAverage_temp());


    }
}
