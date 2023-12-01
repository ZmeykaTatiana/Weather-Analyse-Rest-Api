package by.zmeyka.Weather.Analyse.Rest.Api.service;


import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import by.zmeyka.Weather.Analyse.Rest.Api.service.WeatherService;
import by.zmeyka.Weather.Analyse.Rest.Api.util.Convert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class WeatherServiceTest {

    @Mock
    public WeatherDataRepository weatherDataRepository;
    @Mock
    public WeatherData weatherData;

     @InjectMocks
    public WeatherService weatherService;

    @Test
    public void getCurrentActualInfoTest() {
        List<WeatherData> list = new ArrayList<>();
        list.add(new WeatherData(1));
        list.add(new WeatherData(2));
        list.add(new WeatherData(3));
        // list.add(weatherData);
        when(weatherDataRepository.findAll()).thenReturn(list);
        assertEquals(list, weatherDataRepository.findAll());


        Iterator<WeatherData> iterator = list.iterator();
        int idLast = 0;
        while (iterator.hasNext()) {
            idLast++;
            iterator.next();
        }
        when(weatherDataRepository.findById(idLast)).thenReturn(Optional.ofNullable(list.get(2)));
        WeatherData result = weatherDataRepository.findById(idLast).get();


        assertNotNull(result);
        assertEquals(3, result.getId());
    }


    @Test
    public void calculateAvgTemperatureTest() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date from1 = dateFormat.parse("25-11-2023");
        Date to1 = dateFormat.parse("28-11-2023");
        String from ="25-11-2023";
        String to="28-11-2023";


        List<WeatherData> list = new ArrayList<>();
        WeatherData weatherData1 = new WeatherData(1);
        weatherData1.setCreatedAt(from1);
        weatherData1.setTemperature(7);
        WeatherData weatherData2 = new WeatherData(2);
        weatherData2.setTemperature(8);
        WeatherData weatherData3 = new WeatherData(3);
        weatherData3.setCreatedAt(to1);
        weatherData3.setTemperature(15);
        list.add(weatherData1);
        list.add(weatherData2);
        list.add(weatherData3);

        when(weatherDataRepository.findByCreatedAtBetween(from1,to1)).thenReturn(list);

       List<WeatherData>list1=weatherDataRepository.findByCreatedAtBetween(from1,to1);

        int result = (int) list1.stream()
                .mapToInt(WeatherData::getTemperature)
                .average().orElse(0);


        assertEquals(10, result);





    }
}

