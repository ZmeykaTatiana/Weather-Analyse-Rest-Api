package by.zmeyka.Weather.Analyse.Rest.Api.service;

import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import by.zmeyka.Weather.Analyse.Rest.Api.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeatherService {

    @Autowired
    public WeatherDataRepository weatherDataRepository;


    public WeatherData getCurrentActualInfo(){
        List<WeatherData> list=weatherDataRepository.findAll();
        list.sort(Comparator.comparingInt(WeatherData::getId).reversed());
        WeatherData lastWeatherData = list.get(0);

        return lastWeatherData;

    }

    public int calculateAvgTemperature(String from, String to){
        Date from1= Convert.convertToAnotherFormat(from);
        Date to1=Convert.convertToAnotherFormat(to);


        List<WeatherData> weatherDataList=weatherDataRepository.findByCreatedAtBetween(from1,to1);

        int avgTemperature =(int) weatherDataList.stream()
                .mapToInt(WeatherData::getTemperature)
                .average().orElse(0);

       return avgTemperature;

    }
}
