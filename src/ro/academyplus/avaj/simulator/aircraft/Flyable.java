package ro.academyplus.avaj.simulator.aircraft;

import ro.academyplus.avaj.simulator.tower.WeatherTower;

public abstract class Flyable {
    protected WeatherTower weatherTower;

    public void registerTower(WeatherTower tower) {
        this.weatherTower = tower;
        tower.register(this);
    }

    public abstract void updateConditions();
}