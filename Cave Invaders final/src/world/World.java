package world;

import java.util.ArrayList;

import geometry.Vector;
import input.Listener;
import world.entities.Entity;

public class World {

	static int defaultEntityLayer = 10;

	double ticksPerSecond, timeStep;
	boolean running;

	ArrayList<Scene> scenes;
	int currentScene;

	Listener input;

	public World(double ticksPerSecond) {
		this.scenes = new ArrayList<>();
		this.ticksPerSecond = ticksPerSecond;
		this.timeStep = 1/ticksPerSecond;
	}

	public void update() {
		getCurrentScene().tick(this);
	}

	public void spawn(Entity e, double x, double y) {
		e.setPosition(x, y);
		getCurrentScene().add(e, defaultEntityLayer);
	}
	
	public void addInput(Listener input) {
		this.input = input;
	}

	public void addScene(Scene scene) {
		scenes.add(scene);
	}

	public void setCurrentScene(int i) {
		currentScene = i;
	}

	public Scene getCurrentScene() {
		return scenes.get(currentScene);
	}

	public double getTicksPerSecond() {
		return ticksPerSecond;
	}

	public double getTimeStep() {
		return timeStep;
	}
}
