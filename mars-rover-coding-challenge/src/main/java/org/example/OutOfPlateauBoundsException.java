package org.example;

public class OutOfPlateauBoundsException extends RuntimeException {

	private Plateau plateau;
	private Location position;
	
	public OutOfPlateauBoundsException(Plateau plateau, Location position) {
		super("Position is not on the plateau!");
		
		this.plateau = plateau;
		this.position = position;
	}

	public Plateau getPlateau() {
		return plateau;
	}

	public Location getPosition() {
		return position;
	}
}
