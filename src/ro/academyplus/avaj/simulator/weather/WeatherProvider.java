package ro.academyplus.avaj.simulator.weather;

import ro.academyplus.avaj.simulator.aircraft.Coordinates;

public class WeatherProvider {

    private static String[] weather = {"RAIN", "FOG", "SUN", "SNOW"};

    private WeatherProvider() {}

    public static String getCurrentWeather(Coordinates c) {
        int index = c.getLongitude() + c.getLatitude() + c.getHeight();
        index %= weather.length;
        return weather[index];
    }
}