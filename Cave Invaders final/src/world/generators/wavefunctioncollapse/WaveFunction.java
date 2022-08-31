package world.generators.wavefunctioncollapse;

import java.util.ArrayList;
import java.util.Random;

import world.generators.wavefunctioncollapse.constraints.ConnectionConstraints;
import world.generators.wavefunctioncollapse.constraints.Constraints;
import world.generators.wavefunctioncollapse.constraints.SudokuNumber;

public class WaveFunction {

	SuperPosition[][] field;
	Constraints constraints;
	Random seed;

	public WaveFunction(Constraints constraints, Random seed) {
		field = new SuperPosition[constraints.getWidth()][constraints.getHeight()];
		for (int i = 0; i < constraints.getWidth(); i++) {
			for (int j = 0; j < constraints.getHeight(); j++) {
				field[i][j] = new SuperPosition(seed, constraints.getSelection());
			}
		}
		this.constraints = constraints;
		this.seed = seed;
		run();
	}

	public boolean isCollapsed() {
		for (int i = 0; i < constraints.getWidth(); i++) {
			for (int j = 0; j < constraints.getHeight(); j++) {
				if (!field[i][j].isCollapsed()) {
					return false;
				}
			}
		}
		return true;
	}

	private void run() {
		constraints.apply(field);

		while (!isCollapsed()) {

			int min = constraints.getSelection().length;
			ArrayList<int[]> indeces = new ArrayList<>();

			for (int i = 0; i < constraints.getWidth(); i++) {
				for (int j = 0; j < constraints.getHeight(); j++) {
					SuperPosition sp = field[i][j];
					if (!sp.isCollapsed() && sp.size() < min) {
						min = sp.size();
						indeces = new ArrayList<>();
					}
					if (sp.size() == min) {
						indeces.add(new int[] { i, j });
					}
				}
			}
			System.out.println(indeces.size());
			int[] index = indeces.get(seed.nextInt(0, indeces.size()));
			field[index[0]][index[1]].collapse();

			constraints.apply(field);
		}

	}

	public Object get(int i, int j) {
		return field[i][j].getElement();
	}

	public static void main(String[] args) {
		Random seed = new Random();
		seed.setSeed(69);
		WaveFunction w = new WaveFunction(new ConnectionConstraints(9,9,SudokuNumber.values(), new Object[][] {
			
			
		}), seed);
		System.out.println("finished");

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(w.field[i][j].getElement() + " ");
			}
			System.out.println();
		}
	}
}
