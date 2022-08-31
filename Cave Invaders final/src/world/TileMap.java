package world;

import game.physics.PhysicsObject;
import geometry.Vector;
import world.tiles.SimpleTile;
import world.tiles.Tile;

public class TileMap {

	Tile[][] grid;
	int width, length;

	public TileMap(int width, int length) {
		grid = new Tile[width][length];
		this.width = width;
		this.length = length;
	}

	public Tile get(int i, int j) {
		if (0 <= i && i < width && 0 <= j && j < length) {
			return grid[i][j];
		} else {
			return SimpleTile.Void;
		}

	}

	public Tile getNearest(int x, int y) {
		return get(getGridCoordinate(x), getGridCoordinate(y));
	}

	public void set(int i, int j, Tile t) {
		grid[i][j] = t;
	}

	public void setNearest(int x, int y, Tile t) {
		set(getGridCoordinate(x), getGridCoordinate(y), t);
	}

	public static int getGridCoordinate(double a) {
		return getGridCoordinate((int)Math.round(a));
	}
	
	public static int getGridCoordinate(int a) {
		return (a + Tile.size / 2) / Tile.size;
	}

	public static Vector distance(PhysicsObject p, int i, int j) {
		Vector d = Vector.sub(new Vector(p.x(), p.y()), new Vector(i * Tile.size, j * Tile.size));

		double[] v = new double[2];

		for (int k = 0; k < 2; k++) {
			v[k] = Math.abs(d.x(k)) - (p.s(k) + Tile.size) / 2;
		}

		return new Vector(v);
	}

	public static Vector sign(PhysicsObject p, int i, int j) {
		Vector d = Vector.sub(p.getPosition(), new Vector(i * Tile.size, j * Tile.size));
		return new Vector(-Math.signum(d.x()), -Math.signum(d.y()));
	}
	
	public static void main(String[] args) {
		Vector v = new Vector(0, 20);
		System.out.println(Vector.norm(v));
		
		
	}
}
