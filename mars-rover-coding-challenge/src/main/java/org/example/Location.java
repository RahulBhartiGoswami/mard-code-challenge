package org.example;

public class Location {

	public int x;
	public int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Location(Location other) {
		this.x = other.x;
		this.y = other.y;
	}
	
	public boolean isEqual(Location other) {
		return x == other.x && y == other.y;
	}
	
	@Override
	public String toString() {
		return x + " " + y;
	}
	
	public boolean IsOnPlateau(Plateau p) {
		if (x < 0 || x > p.dimX) {
			return false;
		}
		
		if (y < 0 || y > p.dimY) {
			return false;
		}
		
		return true;
	}
	
	public Location moveForward(Heading heading) {
		switch (heading) {
			case EAST: return new Location(x + 1, y);
			case NORTH: return new Location(x, y + 1);
			case SOUTH: return new Location(x, y - 1);
			case WEST: return new Location(x - 1, y);
			default: throw new RuntimeException("Should not get here!");
		}
	}
}
