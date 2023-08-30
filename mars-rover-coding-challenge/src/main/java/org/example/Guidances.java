package org.example;

import java.util.ArrayList;

public class Guidances {
	private String instructions;
	public Guidances(String instructions) {
		this.instructions = instructions;
	}
	
	public Guidance[] getInstructions() {
		ArrayList<Guidance> result = new ArrayList<Guidance>();
		
		for (char c: instructions.toCharArray()) {
			switch (c) {
				case 'L': result.add(Guidance.LEFT); break;
				case 'M': result.add(Guidance.MOVE); break;
				case 'R': result.add(Guidance.RIGHT); break;
				default: throw new InstructionNotFoundException(c);
			}
		}
		
		return result.toArray(new Guidance[result.size()]);
	}
}
