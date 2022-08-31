package game.physics;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import geometry.Vector;
import math.Functions;
import world.tiles.Tile;

public abstract class PhysicsObject {

	protected Vector p, s, v, a;

	private TreeMap<Double, RectangleCollider> collisionMap;

	public PhysicsObject(int x, int y, int w, int h) {
		setPosition(x, y);
		setDimension(w, h);
	}

	public PhysicsObject() {
		this(0, 0, 0, 0);
	}

	public int x(int i) {
		return (int) Math.round(p.x(i));
	}

	public int x() {
		return x(0);
	}

	public void setX(double x) {
		p = new Vector(x, p.y());
	}

	public int y() {
		return x(1);
	}

	public void setY(double y) {
		p = new Vector(p.x(), y);
	}

	public Vector getPosition() {
		return p;
	}

	public void setPosition(double x, double y) {
		p = new Vector(x, y);
	}

	public int width() {
		return s(0);
	}

	public void setWidth(int width) {
		s = new Vector(width, s(1));
	}

	public int height() {
		return s(1);
	}

	public void setHeight(int height) {
		s = new Vector(s(0), height);
	}

	public int s(int i) {
		return (int) s.x(i);
	}

	public void setDimension(int width, int height) {
		s = new Vector(width, height);
	}

	public int v(int i) {
		return (int) Math.round(v.x(i));
	}

	public Vector getVelocity() {
		return v;
	}

	public void setVelocity(Vector v) {
		this.v = v;
	}

	public void setVelocity(int x, int y) {
		setVelocity(x, y);
	}

	public void stopVelocity() {
		setVelocity(new Vector(0, 0));
	}

	public void addVelocity(Vector v) {
		setVelocity(Vector.add(this.v, v));
	}

	public void addVelocity(int x, int y) {
		addVelocity(new Vector(x, y));
	}

	public void setAcceleration(Vector a) {
		this.a = a;
	}

	public void setAcceleration(int x, int y) {
		setAcceleration(new Vector(x, y));
	}

	public void stopAcceleration() {
		setAcceleration(new Vector(0, 0));
	}

	public void addAcceleration(Vector a) {
		setAcceleration(Vector.add(this.a, a));
	}

	public void addAcceleration(int x, int y) {
		addAcceleration(new Vector(x, y));
	}

	public boolean intersects(PhysicsObject o) {
		// TODO intersection
		return false;
	}

	public void addCollision(double t, RectangleCollider collider) {
		if (collisionMap == null) {
			collisionMap = new TreeMap<>();
		}
		collisionMap.put(t, collider);
	}

	public void collide(PhysicsObject o) {
		collide(new RectangleCollider(o.p, o.s));
	}

	public void collide(int i, int j) {
		collide(new RectangleCollider(new Vector(i * Tile.size, j * Tile.size), new Vector(Tile.size, Tile.size)));
	}

	public void collide(RectangleCollider collider) {
		Vector length = Vector.add(collider.s, s);
		double[] tNear = new double[2], tFar = new double[2];
		double[] direction = { 1, 1 };

		for (int i = 0; i < 2; i++) {
			tNear[i] = (collider.p.x(i) - length.x(i) / 2 - p.x(i)) / v.x(i);
			tFar[i] = (collider.p.x(i) + length.x(i) / 2 - p.x(i)) / v.x(i);

			if (tNear[i] > tFar[i]) {
				double temp = tNear[i];
				tNear[i] = tFar[i];
				tFar[i] = temp;
				direction[i] *= -1;
			}
		}
		if (tNear[0] < tFar[1] && tNear[1] < tFar[0]) {

			int index = Functions.max_index(tNear);
			double t = tNear[index];
			if (t >= 0 && t <= 1) {
				direction[1 - index] = 0;
				Vector normal = new Vector(direction);

				v = Vector.operate(v, normal, (a, b) -> a - (1 - t) * Math.abs(a) * b);
			}
		}

//		if (!(tNear[0] > tFar[1] || tNear[1] > tFar[0])) {
//
//			int index = Functions.max_index(tNear);
//			double t = tNear[index];
//			if (Functions.min(tFar) >= 0) {
//				direction[1 - index] = 0;
//				Vector normal = new Vector(direction);
//
//				addCollision(t, collider);
//			}
//		}
		
//		double t = Functions.max(tFar);
//		if (t >= 0)
//			addCollision(t, collider);
	}

	public void resolveCollision(RectangleCollider collider) {
		Vector length = Vector.add(collider.s, s);
		double[] tNear = new double[2], tFar = new double[2];
		double[] direction = { 1, 1 };

		for (int i = 0; i < 2; i++) {
			tNear[i] = (collider.p.x(i) - length.x(i) / 2 - p.x(i)) / v.x(i);
			tFar[i] = (collider.p.x(i) + length.x(i) / 2 - p.x(i)) / v.x(i);

			if (tNear[i] > tFar[i]) {
				double temp = tNear[i];
				tNear[i] = tFar[i];
				tFar[i] = temp;
				direction[i] *= -1;
			}
		}
		if(tNear[0] == Double.NaN || tNear[1] == Double.NaN) return;
		if(tFar[0] == Double.NaN || tFar[1] == Double.NaN) return;
		
		if (tNear[0] < tFar[1] && tNear[1] < tFar[0]) {

			int index = Functions.max_index(tNear);
			double t = tNear[index];
			if (t >= 0 && t <= 1) {
				direction[1 - index] = 0;
				Vector normal = new Vector(direction);
				System.out.println(t);
				v = Vector.operate(v, normal, (a, b) -> a - (1 - t) * Math.abs(a) * b);
			}
		}
	}

	public void resolveCollisions() {
		if (collisionMap != null) {
			Iterator<Entry<Double, RectangleCollider>> i = collisionMap.entrySet().iterator();
			System.out.println(collisionMap.keySet());
			while (i.hasNext()) {
				Entry<Double, RectangleCollider> e = i.next();
				
				resolveCollision(e.getValue());
			}
			collisionMap = null;
		}
	}
}
