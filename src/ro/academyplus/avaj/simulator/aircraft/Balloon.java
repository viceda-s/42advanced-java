package ro.academyplus.avaj.simulator.aircraft;

public class Balloon extends Aircraft {
    public Balloon(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude() + 2,
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() + 4);

                System.out.println(this + ": This is hot.");
                break;

            case "RAIN":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 5);

                System.out.println(
                        this + ": Damn you rain! You messed up my balloon.");
                break;

            case "FOG":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 3);

                System.out.println(this + ": Can't see a thing in this fog.");
                break;

            case "SNOW":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 15);

                System.out.println(this + ": It's snowing. We're gonna crash.");
                break;

        }

        if (this.coordinates.getHeight() == 0) {
            System.out.println(this + " landing.");
            this.weatherTower.unregister(this);
        }
    }
}
