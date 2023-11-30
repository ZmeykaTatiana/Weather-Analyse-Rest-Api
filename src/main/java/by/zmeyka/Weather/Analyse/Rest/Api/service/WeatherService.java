package by.zmeyka.Weather.Analyse.Rest.Api.service;

import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import by.zmeyka.Weather.Analyse.Rest.Api.repository.WeatherDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class WeatherService {

    @Autowired
    public WeatherDataRepository weatherDataRepository;


    public WeatherData getCurrentActualInfo(){
        List<WeatherData> list=weatherDataRepository.findAll();
        Iterator<WeatherData> iterator=list.iterator();
        int idLast=0;
        while(iterator.hasNext()){
            idLast++;
            iterator.next();
        }
        WeatherData weatherData=weatherDataRepository.findById(idLast).get();
        return weatherData;

    }

    public int calculateAvgTemperature(Date from, Date to){
       //TODO
        int a=0;
        return a;

    }
}
