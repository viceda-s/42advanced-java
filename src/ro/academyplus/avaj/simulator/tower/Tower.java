package ro.academyplus.avaj.simulator.tower;

import java.util.ArrayList;
import java.util.List;
import ro.academyplus.avaj.simulator.aircraft.Flyable;

public class Tower {
    private List<Flyable> observers = new ArrayList<>();

    public void register(Flyable flyable) {
        this.observers.add(flyable);
        System.out.println("Tower says: " + flyable + " registered to weather tower.");
    }

    public void unregister(Flyable flyable) {
        this.observers.remove(flyable);
        System.out.println("Tower says: " + flyable + " unregistered from weather tower.");
    }
    
    protected void conditionsChanged() {
        for (int i = observers.size() - 1; i >= 0; i--) {
            observers.get(i).updateConditions();
        }
    }
}
