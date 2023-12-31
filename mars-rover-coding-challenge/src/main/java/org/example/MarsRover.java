package org.example;

public class MarsRover {


    private Plateau plateau;
    private Location position;
    private Heading heading;
    private String name;

    public MarsRover(String name) {
        this.name = name;
    }

    public void dropRover(Plateau plateau, String args) {
        String[] parts = args.split(" ");
        int x = Integer.parseInt(parts[0]);// Character.getNumericValue(parts[0].toCharArray()[0]);
        int y = Integer.parseInt(parts[1]);// Character.getNumericValue(parts[1].toCharArray()[0]);
        Heading heading = ToHeading(parts[2].toCharArray()[0]);
        dropRover(plateau, new Location(x, y), heading);
    }

    public void dropRover(Plateau plateau, Location position, Heading heading) {
        if (!position.IsOnPlateau(plateau)) {
            throw new OutOfPlateauBoundsException(plateau, position);
        }

        if (plateau.isOccupied(position)) {
            throw new RuntimeException("Already occupied by a rover!");
        }

        this.plateau = plateau;
        this.position = position;
        this.heading = heading;

        plateau.addRover(this);
    }

    public void dropRover(Plateau plateau, int posX, int posY, char heading) {
        dropRover(plateau, new Location(posX, posY), ToHeading(heading));
    }

    public String reportStatus() {
        StringBuilder sb = new StringBuilder(name);
        sb.append(" ");
        sb.append(reportPosition());
        return sb.toString();
    }

    public String reportPosition() {
        if (position == null || heading == null) {
            return "Not dropped yet.";
        }
        return position.toString() + " " + FromHeading(heading);
    }

    public boolean hasPosition(Location pos) {
        return position.isEqual(pos);
    }

    public void processInstructions(Guidance[] instructions) {
        for (Guidance i : instructions) {
            processInstruction(i);
        }
    }

    private void processInstruction(Guidance instruction) {
        if (position == null || heading == null) {
            throw new UnDropppedError();
        }

        switch (instruction) {
            case LEFT: turnLeft(); break;
            case MOVE: moveForward(); break;
            case RIGHT: turnRight(); break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void turnLeft() {
        switch (heading) {
            case EAST: heading = Heading.NORTH; break;
            case NORTH: heading = Heading.WEST; break;
            case SOUTH: heading = Heading.EAST; break;
            case WEST: heading = Heading.SOUTH; break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void turnRight() {
        switch (heading) {
            case EAST: heading = Heading.SOUTH; break;
            case NORTH: heading = Heading.EAST; break;
            case SOUTH: heading = Heading.WEST; break;
            case WEST: heading = Heading.NORTH; break;
            default: throw new RuntimeException("Should not get here!");
        }
    }

    private void moveForward() {
        Location newPosition = position.moveForward(heading);
        if (!newPosition.IsOnPlateau(plateau)) {
            throw new OutOfPlateauBoundsException(plateau, newPosition);
        }
        position = newPosition;
    }

    private static Heading ToHeading(char heading) {
        switch (heading) {
            case 'N': return Heading.NORTH;
            case 'W': return Heading.WEST;
            case 'S': return Heading.SOUTH;
            case 'E': return Heading.EAST;
            default: throw new RuntimeException("Unsupported character '" + heading + "'!");
        }
    }

    private static char FromHeading(Heading heading) {
        switch (heading) {
            case NORTH: return 'N';
            case WEST: return 'W';
            case SOUTH: return 'S';
            case EAST: return 'E';
            default: throw new RuntimeException("Unsupported heading '" + heading + "'!");
        }
    }

}
