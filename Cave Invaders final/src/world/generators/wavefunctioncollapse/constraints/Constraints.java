package world.generators.wavefunctioncollapse.constraints;

import world.generators.wavefunctioncollapse.SuperPosable;
import world.generators.wavefunctioncollapse.SuperPosition;

public abstract class Constraints {
	
	int width, height;
	Object[] selection;
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public Object[] getSelection() {
		return selection;
	}
	
	public void apply(SuperPosition[][] field) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				SuperPosition sp = field[i][j];
				
				if(!sp.isCollapsed()) {
					for(int k = 0; k < selection.length; k++) {
						if(!test(selection[k], i, j, field)) {
							sp.remove(selection[k]);
						}else if(!sp.getPossible().contains(selection[k])) {
							sp.getPossible().add(selection[k]);
						}
					}
				}
			}
		}
	}
	
	public abstract boolean test(Object p, int i, int j, SuperPosition[][] field);
}
