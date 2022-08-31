package world.generators.wavefunctioncollapse.constraints;

import world.generators.wavefunctioncollapse.SuperPosable;

public enum SudokuNumber implements SuperPosable{
	ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE;
	
	public String toString() {
		return "" + (ordinal()+1);
	}
}
