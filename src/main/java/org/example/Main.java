package org.example;


import org.example.model.*;

public class Main {
    public static void main(String[] args) {
        final int RUNS = 100_000000; // number of times to test operations

        // Benchmark adding wagons
        long start = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            Train train = new Train();
            train.addWagon(new Locomotive());
            train.addWagon(new SeatWagon());
            train.addWagon(new SeatWagon());
            train.addWagon(new SleeperWagon());
            train.addWagon(new DiningWagon());
            train.addWagon(new SleeperWagon());
            train.addWagon(new CargoWagon());
        }
        long end = System.nanoTime();
        System.out.println("Time to add wagons " + RUNS + " times: " + (end - start) / 1_000_000 + " ms");

        // Benchmark validate
        Train sampleTrain = new Train();
        sampleTrain.addWagon(new Locomotive());
        sampleTrain.addWagon(new SeatWagon());
        sampleTrain.addWagon(new SeatWagon());
        sampleTrain.addWagon(new SleeperWagon());
        sampleTrain.addWagon(new DiningWagon());
        sampleTrain.addWagon(new SleeperWagon());
        sampleTrain.addWagon(new CargoWagon());

        start = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            TrainFixer.validate(sampleTrain);
        }
        end = System.nanoTime();
        System.out.println("Time to validate train " + RUNS + " times: " + (end - start) / 1_000_000 + " ms");

        // Benchmark fix
        start = System.nanoTime();
        for (int i = 0; i < RUNS; i++) {
            TrainFixer.fix(sampleTrain);
        }
        end = System.nanoTime();
        System.out.println("Time to fix train " + RUNS + " times: " + (end - start) / 1_000_000 + " ms");
    }
}