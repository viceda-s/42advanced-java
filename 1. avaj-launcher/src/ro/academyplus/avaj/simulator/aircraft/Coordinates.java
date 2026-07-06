package ro.academyplus.avaj.simulator.aircraft;

public class Coordinates {
    private int longitude;
    private int latitude;
    private int height;

    Coordinates(int longitude, int latitude, int height) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.height = clampHeight(height);
    }

    private int clampHeight(int value) {
        return Math.max(0, Math.min(value, 100));
    }

    public int getHeight() {
        return height;
    }

    public int getLatitude() {
        return latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public boolean isHeightZero() {
        return this.height == 0;
    }

    @Override
    public String toString() {
        return "Coordinates { " +
                " longitude = " + longitude +
                ", latitude = " + latitude +
                ", height = " + height + '}';
    }
}
