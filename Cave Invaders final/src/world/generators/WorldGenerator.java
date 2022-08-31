package world.generators;

import input.Listener;
import world.World;

public abstract class WorldGenerator {
	
	public abstract World generate(Seed seed);
}
