package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainFixerTest {

    // ---------------- LOCOMOTIVE TESTS ----------------
    @Test
    void validTrainWithOneLocomotiveAtFront() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SeatWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void invalidTrainWithoutLocomotiveAtFront() {
        Train train = new Train();
        train.addWagon(new SeatWagon());
        train.addWagon(new Locomotive());
        assertFalse(TrainFixer.validate(train));
    }

    @Test
    void validLongTrainWithLocomotivesAtBothEnds() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        for (int i = 0; i < 10; i++) train.addWagon(new SeatWagon());
        train.addWagon(new Locomotive());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void invalidLongTrainWithoutLocomotiveAtEnd() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        for (int i = 0; i < 11; i++) train.addWagon(new SeatWagon());
        assertFalse(TrainFixer.validate(train));
    }

    // ---------------- PASSENGER VS CARGO ----------------
    @Test
    void validPassengerBeforeCargo() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SeatWagon());
        train.addWagon(new CargoWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void invalidCargoBeforePassenger() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new CargoWagon());
        train.addWagon(new SeatWagon());
        assertFalse(TrainFixer.validate(train));
    }

    // ---------------- SLEEPER WAGON RULE ----------------
    @Test
    void validNoSleepers() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SeatWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void validOneSleeper() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SleeperWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void validTwoAdjacentSleepers() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SleeperWagon());
        train.addWagon(new SleeperWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void invalidSeparatedSleepers() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SleeperWagon());
        train.addWagon(new SeatWagon());
        train.addWagon(new SleeperWagon());
        assertFalse(TrainFixer.validate(train));
    }

    // ---------------- DINING WAGON RULE ----------------
    @Test
    void validSeatBeforeDining() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SeatWagon());
        train.addWagon(new DiningWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void validDiningBeforeSleeper() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SeatWagon());
        train.addWagon(new DiningWagon());
        train.addWagon(new SleeperWagon());
        assertTrue(TrainFixer.validate(train));
    }

    @Test
    void invalidSeatBlockedBySleeperBeforeDining() {
        Train train = new Train();
        train.addWagon(new Locomotive());
        train.addWagon(new SeatWagon());
        train.addWagon(new SleeperWagon());
        train.addWagon(new DiningWagon());
        assertFalse(TrainFixer.validate(train));
    }

    // ---------------- FIX FUNCTION ----------------
    @Test
    void fixInvalidTrainProducesValidOne() {
        Train train = new Train();
        // Invalid: cargo before passengers, no dining
        train.addWagon(new Locomotive());
        train.addWagon(new CargoWagon());
        train.addWagon(new SeatWagon());

        assertFalse(TrainFixer.validate(train));

        Train fixed = TrainFixer.fix(train);
        assertTrue(TrainFixer.validate(fixed));
    }
}
