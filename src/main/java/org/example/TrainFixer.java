package org.example;

import org.example.model.CargoWagon;
import org.example.model.DiningWagon;
import org.example.model.Locomotive;
import org.example.model.PassengerWagon;
import org.example.model.SeatWagon;
import org.example.model.SleeperWagon;
import org.example.model.Wagon;
import org.example.linkedlist.Node;

public class TrainFixer {

    public static boolean validate(Train train) {
        int n = train.size();
        if (n == 0) return false;

        Node<Wagon> current = train.getWagons().getHead();

        // Rule 1: Locomotives
        if (!(current.data instanceof Locomotive)) return false;
        if (n > 10) {
            Node<Wagon> last = current;
            while (last.next != null) last = last.next;
            if (!(last.data instanceof Locomotive)) return false;
        }

        boolean cargoStarted = false;
        boolean inSleeperBlock = false;
        boolean sleeperBlockEnded = false;
        boolean diningSeen = false;
        boolean seatBeforeDining = false;

        while (current != null) {
            Wagon w = current.data;

            // Cargo rule
            if (w instanceof CargoWagon) {
                cargoStarted = true;
            }

            if (w instanceof PassengerWagon) {
                if (cargoStarted) return false;

                // Sleeper adjacency
                if (w instanceof SleeperWagon) {
                    if (sleeperBlockEnded) return false;
                    inSleeperBlock = true;

                    // Dining accessibility check
                    if (seatBeforeDining && !diningSeen) {
                        // seat already exists, dining not seen yet, sleeper appeared
                        return false;
                    }
                } else {
                    if (inSleeperBlock) {
                        inSleeperBlock = false;
                        sleeperBlockEnded = true;
                    }
                }

                if (w instanceof DiningWagon) {
                    diningSeen = true;
                }

                if (w instanceof SeatWagon && !diningSeen) {
                    seatBeforeDining = true;
                }
            }
            current = current.next;
        }

        return true;
    }

    public static Train fix(Train train) {
        Train fixed = new Train();
        int n = train.size();

        // Count wagons
        int seats = 0, sleepers = 0, cargo = 0, dining = 0;
        Node<Wagon> current = train.getWagons().getHead();
        while (current != null) {
            Wagon w = current.data;
            if (w instanceof SeatWagon) seats++;
            else if (w instanceof SleeperWagon) sleepers++;
            else if (w instanceof DiningWagon) dining++;
            else if (w instanceof CargoWagon) cargo++;
            // locomotives ignored, we'll add fresh ones
            current = current.next;
        }

        // Always one locomotive at front
        fixed.addWagon(new Locomotive());

        // Add passenger section in order: seats → dining → sleepers
        for (int i = 0; i < seats; i++) fixed.addWagon(new SeatWagon());

        // Ensure exactly one dining wagon
        fixed.addWagon(new DiningWagon());

        for (int i = 0; i < sleepers; i++) fixed.addWagon(new SleeperWagon());

        // Add cargo last
        for (int i = 0; i < cargo; i++) fixed.addWagon(new CargoWagon());

        // If long train, add locomotive at end
        if (n > 10) fixed.addWagon(new Locomotive());

        return fixed;
    }
}
