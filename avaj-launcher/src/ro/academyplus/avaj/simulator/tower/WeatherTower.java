package ro.academyplus.avaj.simulator.tower;

import ro.academyplus.avaj.simulator.aircraft.Coordinates;
import ro.academyplus.avaj.simulator.weather.WeatherProvider;

public class WeatherTower extends Tower {
    public String getWeather(Coordinates coordinates) {
        return WeatherProvider.getCurrentWeather(coordinates);
    }

    public void changeWeather() {
        this.conditionsChanged();
    }
}
