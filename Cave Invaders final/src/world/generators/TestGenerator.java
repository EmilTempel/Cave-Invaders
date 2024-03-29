package world.generators;

import java.util.Random;

import world.Scene;
import world.World;
import world.generators.wavefunctioncollapse.SuperPosable;
import world.generators.wavefunctioncollapse.WaveFunction;
import world.generators.wavefunctioncollapse.constraints.ConnectionConstraints;
import world.tiles.SimpleTile;

public class TestGenerator extends WorldGenerator{

	@Override
	public World generate(Seed seed) {
		World world = new World(60);
		Scene scene = new Scene(20, 20);

		ConnectionConstraints c = new ConnectionConstraints(20,20,new SimpleTile[] {SimpleTile.Roof, SimpleTile.Wall, SimpleTile.Floor}, new Object[][] {
			{SimpleTile.Roof, SimpleTile.Floor}, //SimpleTile.Roof 
			{SimpleTile.Roof,SimpleTile.Floor, SimpleTile.Wall},  //SimpleTile.Wall
			{SimpleTile.Roof, SimpleTile.Wall, SimpleTile.Floor},  //SimpleTile.Floor
			
		});
		
		Random r = new Random();
//		r.setSeed(100);
		
		WaveFunction w = new WaveFunction(c, r);
		System.out.println("finished");
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				SimpleTile tile = (SimpleTile) w.get(i, j);
				if(tile == SimpleTile.Wall && j+1 < 20 &&(SimpleTile) w.get(i, j+1) != SimpleTile.Floor) {
					tile = SimpleTile.Roof;
				}
				if(tile == SimpleTile.Wall && j-1 >= 0 && (SimpleTile) w.get(i, j-1) == SimpleTile.Floor) {
					tile = SimpleTile.Floor;
				}
				if(tile == SimpleTile.Roof && j-1 >= 0 && (SimpleTile) w.get(i, j+1) == SimpleTile.Floor) {
					tile = SimpleTile.Floor;
				}
				scene.set(i, j, tile, 0);
			}
		}
		
//		scene.set(2, 2, SimpleTile.values()[(int) (2 * Math.random())], 0);
//		scene.set(2, 3, SimpleTile.values()[(int) (2 * Math.random())], 0);
//		scene.set(3, 2, SimpleTile.values()[(int) (2 * Math.random())], 0);
		
		world.addScene(scene);
		world.setCurrentScene(0);
		
		return world;
	}

}
