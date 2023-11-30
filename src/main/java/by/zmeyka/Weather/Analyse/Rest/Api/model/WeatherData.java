package by.zmeyka.Weather.Analyse.Rest.Api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="weatherdata")
public class WeatherData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="temperature")
    private double temperature;

    @Column(name="wind_speed")
    private double wind_speed;

    @Column(name="atmospheric_pressure")
    private double atmospheric_pressure;

    @Column(name="humidity")
    private double humidity;

    @Column(name="weather_conditions")
    private String weatherCondition;

    @Column(name="location")
    private String location;

    @Column(name="created_at")
    private Date created_at;


}
