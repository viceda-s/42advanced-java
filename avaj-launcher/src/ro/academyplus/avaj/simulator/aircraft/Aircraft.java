package ro.academyplus.avaj.simulator.aircraft;

public abstract class Aircraft extends Flyable {
    protected long id;
    protected String name;
    protected Coordinates coordinates;

    protected Aircraft(long id, String name, Coordinates coordinates) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '#' + name + '(' + id + ')';
    }
}
