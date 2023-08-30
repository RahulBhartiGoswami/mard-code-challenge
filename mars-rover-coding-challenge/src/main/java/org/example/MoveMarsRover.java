package org.example;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class MoveMarsRover {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(in);

        out.print("Please enter the dimensions of the plateau (in the form x y):");
        String dimensions = scanner.nextLine();
        Plateau plateau = createPlateauFromDimensions(dimensions);

        int i = 0;
        while (true) {
            i++;

            String name = "MarsRover " + i;
            out.print("Enter drop information for " + name + " (in the form x y h):");
            String dropInfo = scanner.nextLine();

            try {
                MarsRover rover = dropRover(name, plateau, dropInfo);
                out.println("Report: " + rover.reportStatus());

                out.print("Enter guidances for " + name + " (in the form LMRMMMLMM):");
                String instructions = scanner.nextLine();

                Guidance[] instructionsCollection = new Guidances(instructions).getInstructions();
                rover.processInstructions(instructionsCollection);
                out.println("Report: " + rover.reportStatus());
            } catch (Exception ex) {
                out.println(ex.getMessage());
            }
        }

    }

    private static MarsRover dropRover(String id, Plateau plateau, String dropInfo) {
        MarsRover rover = new MarsRover(id);
        rover.dropRover(plateau, dropInfo);
        return rover;
    }

    private static Plateau createPlateauFromDimensions(String dimensions) {
        String[] parts = dimensions.split(" ");
        int dimX = Integer.parseInt(parts[0]);
        int dimY = Integer.parseInt(parts[1]);
        return new Plateau(dimX, dimY);
    }
}