create database if not exists WeatherAnalyse;
use WeatherAnalyse;
CREATE TABLE IF NOT EXISTS WeatherAnalyse.WeatherData (
  id INT AUTO_INCREMENT PRIMARY KEY,
  temperature INT,
  wind_speed INT,
  Atmospheric_pressure INT,
  humidity INT,
  Weather_conditions VARCHAR(50),
  location VARCHAR(50),
  created_at DATETIME
);