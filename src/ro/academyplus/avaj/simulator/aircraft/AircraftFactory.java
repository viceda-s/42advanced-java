package ro.academyplus.avaj.simulator.aircraft;

public class AircraftFactory {
    private AircraftFactory() {};

    private static long idCounter = 0;

    public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height) {
        Coordinates coordinates = new Coordinates(longitude, latitude, height);
        long id = ++idCounter;

        switch(type.toLowerCase()) {
            case "helicopter":
                return new Helicopter(id, name, coordinates);
            case "jetplane":
                return new JetPlane(id, name, coordinates);
            case "balloon":
                return new Balloon(id, name, coordinates);
            default:
                throw new IllegalArgumentException("Unknown aircraft type: " + type);
        }
    }
    
}
