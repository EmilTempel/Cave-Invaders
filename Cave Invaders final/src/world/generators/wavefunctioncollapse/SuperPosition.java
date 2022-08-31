package world.generators.wavefunctioncollapse;

import java.util.ArrayList;
import java.util.Random;

public class SuperPosition {

	ArrayList<Object> possible;
	Object element;
	Random seed;

	public SuperPosition(Random seed, Object... possible) {
		assert possible.length > 0;

		this.possible = new ArrayList<>();
		for (int i = 0; i < possible.length; i++) {
			this.possible.add(possible[i]);
		}

		if (size() == 1) {
			element = possible[0];
		}

		this.seed = seed;
	}

	public boolean isCollapsed() {
		return element != null;
	}

	public void setElement(Object element) {
		this.element = element;
	}

	public Object getElement() {
		return element;
	}

	public void remove(Object p) {
		possible.remove(p);
		if (possible.size() == 1) {
			element = possible.get(0);
		}
	}

	public void collapse() {
		if (!isCollapsed()) {
			element = possible.get(seed.nextInt(0, possible.size()));
			possible = new ArrayList<>();
			possible.add(element);
		}
	}

	public ArrayList<Object> getPossible() {
		return possible;
	}

	public int size() {
		return possible.size();
	}
}
