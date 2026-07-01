package ro.academyplus.avaj.simulator.aircraft;

public class JetPlane extends Aircraft {
    public JetPlane(long id, String name, Coordinates coordinates) {
        super(id, name, coordinates);
    }

    public void updateConditions() {
        String weather = this.weatherTower.getWeather(this.coordinates);

        switch (weather) {
            case "SUN":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 10,
                        this.coordinates.getHeight() + 2);

                System.out.println(this + ": This is hot.");
                break;

            case "RAIN":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 5,
                        this.coordinates.getHeight());

                System.out.println(this + ": It's raining. Better watch out for lightings.");
                break;

            case "FOG":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude() + 1,
                        this.coordinates.getHeight());

                System.out.println(this + ": Can't see a thing in this fog.");
                break;

            case "SNOW":
                this.coordinates = new Coordinates(
                        this.coordinates.getLongitude(),
                        this.coordinates.getLatitude(),
                        this.coordinates.getHeight() - 7);

                System.out.println(this + ": OMG! Winter is coming!");
                break;

        }

        if (this.coordinates.getHeight() == 0) {
            System.out.println(this + " landing.");
            this.weatherTower.unregister(this);
        }
    }
}
