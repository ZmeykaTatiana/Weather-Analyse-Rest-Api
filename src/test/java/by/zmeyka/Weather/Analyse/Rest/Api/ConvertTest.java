package by.zmeyka.Weather.Analyse.Rest.Api;

import by.zmeyka.Weather.Analyse.Rest.Api.DTO.CurrentWeatherDTO;
import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.util.Convert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class ConvertTest {


    @Test
    public void testConvert() throws ParseException {

        CurrentWeatherDTO currentWeatherDTO = new CurrentWeatherDTO();
        CurrentWeatherDTO.Location location=new CurrentWeatherDTO.Location("Minsk","2023-12-01");
        CurrentWeatherDTO.Current.Condition condition=new CurrentWeatherDTO.Current.Condition();
        condition.setText("cloudy");
        CurrentWeatherDTO.Current current=new CurrentWeatherDTO.Current(15,150,1005,18,condition);

        currentWeatherDTO.setLocation(location);
        currentWeatherDTO.setCurrent(current);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date expectedDate = dateFormat.parse("2023-12-01");


        WeatherData actualWeatherData = Convert.convert(currentWeatherDTO);


        assertEquals(15, actualWeatherData.getTemperature());
        assertEquals(150, actualWeatherData.getWind_speed());
        assertEquals(1005, actualWeatherData.getAtmospheric_pressure());
        assertEquals(18, actualWeatherData.getHumidity());
        assertEquals("cloudy", actualWeatherData.getWeatherCondition());
        assertEquals("Minsk", actualWeatherData.getLocation());


        assertEquals(expectedDate, actualWeatherData.getCreatedAt());
    }


    @Test
    public void testConvertToDate() throws ParseException {
        String dateString = "2023-12-01";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date expectedDate = dateFormat.parse(dateString);
        Date actualDate = Convert.convertToDate(dateString);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testConvertToDateWithInvalidFormat() {
        String invalidDateString = "invalid-date";

        Date actualDate = Convert.convertToDate(invalidDateString);

        assertEquals(null, actualDate);
    }

    @Test
    public void testConvertToAnotherFormat() throws ParseException {
        String dateString = "01-12-2023";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date expectedDate = dateFormat.parse(dateString);


        Date actualDate = Convert.convertToAnotherFormat(dateString);

        assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testConvertToAnotherFormatWithInvalidFormat() {

        String invalidDateString = "invalid-date";

        Date actualDate = Convert.convertToAnotherFormat(invalidDateString);

        assertEquals(null, actualDate);
    }




}


