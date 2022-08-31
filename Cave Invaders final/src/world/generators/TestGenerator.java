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

		ConnectionConstraints c = new ConnectionConstraints(20,20,SimpleTile.values(), new Object[][] {
			{SimpleTile.Void, SimpleTile.Floor}, //SimpleTile.Void 
			{SimpleTile.Void,SimpleTile.Floor, SimpleTile.Wall},  //SimpleTile.Wall
			{SimpleTile.Wall, SimpleTile.Floor}  //SimpleTile.Floor
		});
		
		Random r = new Random();
//		r.setSeed(100);
		
		WaveFunction w = new WaveFunction(c, r);
		System.out.println("finished");
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				scene.set(i, j, (SimpleTile) w.get(i, j), 0);
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
