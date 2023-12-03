package by.zmeyka.Weather.Analyse.Rest.Api;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherApiService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static javax.management.Query.eq;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

@SpringBootTest
public class WeatherApiServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherApiService weatherApiService;

    @Test
    public void getWeatherByMinsk_Success() {

        CurrentWeatherDTO mockResponse = new CurrentWeatherDTO();

        when(restTemplate.exchange(Mockito.anyString(), Mockito.any(HttpMethod.class), Mockito.any(HttpEntity.class), Mockito.eq(CurrentWeatherDTO.class)))
                .thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));
        CurrentWeatherDTO result = weatherApiService.getWeatherByMinsk();


        assertNotNull(result);

        }
    }
