package world.generators.wavefunctioncollapse.constraints;

import world.generators.wavefunctioncollapse.SuperPosition;

public class Sudoku extends Constraints{

	public Sudoku() {
		width = SudokuNumber.values().length;
		height = SudokuNumber.values().length;
		selection = SudokuNumber.values();
	}
	
	@Override
	public boolean test(Object p, int i, int j, SuperPosition[][] field) {
		for(int c = 0; c < height; c++) {
			if(field[i][c].isCollapsed() && field[i][c].getElement() == p) {
				return false;
			}
		}
		
		for(int r = 0; r < width; r++) {
			if(field[r][j].isCollapsed() && field[r][j].getElement() == p) {
				return false;
			}
		}
		
//		for(int c = 0; c < 3; c++) {
//			for(int r = 0; r < 3; r++) {
//				SuperPosition sp = field[i%3 + c][j%3 + r];
//				if(sp.isCollapsed() && sp.getElement() == p) {
//					return false;
//				}
//			}
//		}
		
		return true;
	}

}
