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
import ro.academyplus.avaj.simulator.exceptions.InvalidAircraftCountException;
import ro.academyplus.avaj.simulator.exceptions.InvalidAircraftLineException;
import ro.academyplus.avaj.simulator.exceptions.InvalidScenarioException;
import ro.academyplus.avaj.simulator.tower.WeatherTower;

public class Simulator {
    private static void fail(String message) {
        System.out.println(message);
        System.exit(1);
    }

    private static int parsePositiveInt(String token) throws InvalidAircraftLineException {
        int value;
        try {
            value = Integer.parseInt(token);
        } catch (NumberFormatException e) {
            throw new InvalidAircraftLineException("Expected a positive integer: " + token);
        }
        if (value <= 0) {
            throw new InvalidAircraftLineException("Expected a positive integer: " + token);
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

        int weatherChanges;
        List<Flyable> aircraftList = new ArrayList<>();

        try {
            if (lines.isEmpty()) {
                throw new InvalidAircraftCountException("File is empty.");
            }

            try {
                weatherChanges = Integer.parseInt(lines.get(0).trim());
            } catch (NumberFormatException e) {
                throw new InvalidAircraftCountException("First line must be a positive integer.");
            }
            if (weatherChanges <= 0) {
                throw new InvalidAircraftCountException("First line must be a positive integer.");
            }

            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i);
                String[] tokens = line.trim().split("\\s+");

                if (tokens.length != 5) {
                    throw new InvalidAircraftLineException("Invalid aircraft line:" + line);
                }

                int longitude = parsePositiveInt(tokens[2]);
                int latitude = parsePositiveInt(tokens[3]);
                int height = parsePositiveInt(tokens[4]);
                Flyable aircraft = AircraftFactory.newAircraft(tokens[0], tokens[1], longitude, latitude, height);
                aircraftList.add(aircraft);
            }
        } catch (InvalidScenarioException e) {
            fail(e.getMessage());
            return;
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
