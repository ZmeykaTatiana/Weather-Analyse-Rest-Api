package by.zmeyka.Weather.Analyse.Rest.Api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class WeatherAnalyseRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherAnalyseRestApiApplication.class, args);
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
