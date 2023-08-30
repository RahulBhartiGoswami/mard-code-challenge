package org.example;

public class InstructionNotFoundException extends RuntimeException {

	public InstructionNotFoundException(char instruction) {
		super("Unknown instruction '" + instruction + "'!");
	}
}
