package ro.academyplus.avaj.simulator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import ro.academyplus.avaj.simulator.aircraft.AircraftFactory;
import ro.academyplus.avaj.simulator.aircraft.Flyable;
import ro.academyplus.avaj.simulator.tower.WeatherTower;

public class Simulator {
    private static void fail(String message) {
        System.out.println(message);
        System.exit(1);
    }

    private static int parsePositiveInt(String token) {
        int value;
        try {
            value = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            fail("Expected a positive integer: " + token);
            return -1;
        }
        if (value <= 0) {
            fail("Expected a positive integer: " + token);
            return -1;
        }
        return value;
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            fail("Expected only one argument");
        }
        
        List<String> lines;
        try {
            lines = Files.readAllLines(Path.of(args[0]));
        } catch (IOException e) {
            fail("Cannot read file:" + args[0]);
            return;
        }
        if (lines.isEmpty()) {
            fail("File is empty.");
            return;
        }

        int weatherChanges;
        List<Flyable> aircraftList = new ArrayList<>();

        try {
            weatherChanges = Integer.parseInt(lines.get(0).trim());
        } catch (NumberFormatException e) {
            fail("First line must be a positive integer.");
            return;
        }
        if (weatherChanges <= 0) {
            fail("First line must be a positive integer.");
            return;
        }

        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] tokens = line.trim().split("\\s+");

            if (tokens.length != 5) {
                fail("Invalid aircraft line:" + line);
                return;
            }

            int longitude = parsePositiveInt(tokens[2]);
            int latitude = parsePositiveInt(tokens[3]);
            int height = parsePositiveInt(tokens[4]);
            Flyable aircraft;
            try {
                aircraft = AircraftFactory.newAircraft(tokens[0], tokens[1], longitude, latitude, height);
            } catch (IllegalArgumentException e) {
                fail("Unknown aircraft type: " + tokens[0]);
                return;
            }
            aircraftList.add(aircraft);
        }

        try {
            System.setOut(new PrintStream("simulation.txt"));
        } catch (FileNotFoundException e) {
            fail("Cannot create simulation.txt");
            return;
        }

        WeatherTower tower = new WeatherTower();
        for (Flyable aircraft : aircraftList) {
            aircraft.registerTower(tower);
        }

        for (int i = 0; i < weatherChanges; i++) {
            tower.changeWeather();
        }

    }
    
}
