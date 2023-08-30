package org.example;

import java.util.ArrayList;
import java.util.List;

public class Plateau {

    public int dimX;
    public int dimY;

    private List<MarsRover> rovers = new ArrayList<MarsRover>();

    public Plateau(int dimX, int dimY) {
        this.dimX = dimX;
        this.dimY = dimY;
    }

    public void addRover(MarsRover rover) {
        rovers.add(rover);
    }

    public boolean isOccupied(Location position) {
        for (MarsRover r : rovers) {
            if (r.hasPosition(position)) {
                return true;
            }
        }

        return false;
    }
}
