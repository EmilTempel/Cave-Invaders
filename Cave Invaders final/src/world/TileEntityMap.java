package world;
import java.util.ArrayList;

import world.entities.Entity;
import world.tiles.Tile;

public class TileEntityMap {
		TileMap tileMap;
		ArrayList<Entity> entityMap;

		TileEntityMap(int gridWidth, int gridLength) {
			tileMap = new TileMap(gridWidth, gridLength);
			entityMap = new ArrayList<>();
		}

		public void set(int i, int j, Tile t) {
			tileMap.set(i, j, t);
		}
		
		public void setNearest(int x, int y, Tile t) {
			tileMap.setNearest(x, y, t);
		}
		
		public Tile get(int i, int j) {
			return tileMap.get(i,j);
		}
		
		public Tile getNearest(int x, int y) {
			return tileMap.getNearest(x, y);
		}

		public void add(Entity e) {
			entityMap.add(e);
		}
		
		public Entity get(int i) {
			return entityMap.get(i);
		}
		
		public ArrayList<Entity> getEntities(){
			return entityMap;
		}
	}
