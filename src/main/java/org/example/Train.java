package org.example;

import org.example.linkedlist.MyLinkedList;
import org.example.model.Wagon;

public class Train {
    private MyLinkedList<Wagon> wagons = new MyLinkedList<>();

    public void addWagon(Wagon wagon) {
        wagons.add(wagon);
    }

    public MyLinkedList<Wagon> getWagons() {
        return wagons;
    }

    public int size() {
        return wagons.size();
    }
}
