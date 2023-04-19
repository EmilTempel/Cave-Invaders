package world;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import world.entities.Entity;
import world.tiles.Tile;

public class Scene {
	
	static int FLOOR_LAYER = 0, ENTITY_LAYER = 1, WALL_LAYER = 2;

	TreeMap<Integer, TileEntityMap> mapLayered;

	int gridWidth, gridLength;

	public Scene(int gridWidth, int gridLength) {
		mapLayered = new TreeMap<>();
		this.gridWidth = gridWidth;
		this.gridLength = gridLength;
	}

	public void set(int i, int j, Tile t, int layer) {
		if (mapLayered.get(layer) == null) {
			mapLayered.put(layer, new TileEntityMap(gridWidth, gridLength));
		}
		mapLayered.get(layer).set(i, j, t);
	}

	public void setNearest(int x, int y, Tile t, int layer) {
		if (mapLayered.get(layer) == null) {
			mapLayered.put(layer, new TileEntityMap(gridWidth, gridLength));
		}
		mapLayered.get(layer).set(x, y, t);
	}

	public void add(Entity e, int layer) {
		if (mapLayered.get(layer) == null) {
			mapLayered.put(layer, new TileEntityMap(gridWidth, gridLength));
		}
		mapLayered.get(layer).add(e);
	}

	public void tick(World world) {
		for (TileEntityMap map : getMapLayers()) {

			for (int i = 0; i < gridWidth; i++) {
				for (int j = 0; j < gridLength; j++) {
					Tile tile = map.get(i, j);
					if (tile != null) {
						map.get(i, j).tick();
					}

				}
			}

			for (Entity e : map.getEntities()) {
				e.tick(world);
				e.move(world);
			}

		}
	}

	public boolean checkIntersections(Rectangle rect, TileChecker checker) {
		for (TileEntityMap map : getMapLayers()) {

			for (int i = TileMap.getGridCoordinate(rect.x) ; i < TileMap.getGridCoordinate(rect.x + rect.width); i++) {
				for (int j = TileMap.getGridCoordinate(rect.y) ; j < TileMap.getGridCoordinate(rect.y + rect.height)  ; j++) {

					Tile tile = map.get(i, j);
					
					if (checker.check(tile)) {
						System.out.println(tile);
						return true;
					}
				}
			}
		}

		return false;
	}

	public Collection<TileEntityMap> getMapLayers() {
		return mapLayered.values();
	}

	public Set<Entry<Integer, TileEntityMap>> getMapLayersIndexed() {
		return mapLayered.entrySet();
	}

	public interface TileChecker {
		public abstract boolean check(Tile t);
	}
}
