package world.generators.wavefunctioncollapse.constraints;

import java.util.Random;

import world.generators.wavefunctioncollapse.SuperPosition;

public class ConnectionConstraints extends Constraints {

	static int[][] connecting = { { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	Object[][] connections;
	SuperPosition defaultSuperPosition;

	public ConnectionConstraints(int width, int height, Object[] selection, Object[][] connections) {
		this.width = width;
		this.height = height;

		this.selection = selection;
		this.connections = connections;
		this.defaultSuperPosition = new SuperPosition(new Random(), selection[0]);
	}

	public SuperPosition get(SuperPosition[][] field, int i, int j) {
		if (i < 0 || i >= width || j < 0 || j >= height) {
			return defaultSuperPosition;
		} else {
			return field[i][j];
		}
	}

	public int index(Object p) {
		for (int i = 0; i < selection.length; i++) {
			if (selection[i] == p) {
				return i;
			}
		}
		return -1;
	}
	@Override
	public boolean test(Object p, int i, int j, SuperPosition[][] field) {
		for (int x = -1; x < 2; x ++) {
			for (int y = -1; y < 2; y++) {
				SuperPosition sp = get(field, i + x, j + y);

				boolean flag = false;
				for (int l = 0; l < connections[index(p)].length; l++) {

					if (sp.getPossible().contains(connections[index(p)][l])) {

						flag = true;
					}
				}

				if (!flag) {

					return false;
				}
			}
		}
		return true;
	}

}
