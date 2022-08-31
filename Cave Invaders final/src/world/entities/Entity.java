package world.entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import game.physics.PhysicsObject;
import geometry.Vector;
import graphics.Camera;
import world.TileEntityMap;
import world.TileMap;
import world.World;
import world.tiles.Tile;

public abstract class Entity extends PhysicsObject {

	boolean solid;

	Camera camera;

	public Rectangle xHitbox, yHitbox;

	public void move(World world) {
		v = Vector.add(v, a);

		if (!v.equals(new Vector(0, 0))) {

			Camera.loaded = new ArrayList<>();
			int[] dir = new int[] { Math.signum(v.x()) == 0 ? 1 : (int) Math.signum(v.x()),
					Math.signum(v.y()) == 0 ? 1 : (int) Math.signum(v.y()) };

			for (TileEntityMap map : world.getCurrentScene().getMapLayers()) {

				for (int i = TileMap.getGridCoordinate(x()) - dir[0] * 1; i != TileMap.getGridCoordinate(x())
						+ dir[0] * 4; i += dir[0]) {
					for (int j = TileMap.getGridCoordinate(y()) - dir[1] * 1; j != TileMap.getGridCoordinate(y())
							+ dir[1] * 4; j += dir[1]) {

						Tile tile = map.get(i, j);

						if (tile != null && tile.isSolid()) {
							Camera.loaded.add(new int[] { i, j });
							collide(i, j);
						}

					}
				}
			}
			resolveCollisions();
		}

		p = Vector.add(p, v);

		if (camera != null)

		{
			camera.setPosition(x(), y());
		}
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public abstract void tick(World world);

	public abstract void draw(Graphics g);

}
