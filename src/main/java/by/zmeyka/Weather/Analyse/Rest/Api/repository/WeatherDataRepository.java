package by.zmeyka.Weather.Analyse.Rest.Api.repository;

import by.zmeyka.Weather.Analyse.Rest.Api.model.WeatherData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface WeatherDataRepository extends JpaRepository<WeatherData,Integer> {
    List<WeatherData> findByCreatedAtBetween(Date from, Date to);
}
