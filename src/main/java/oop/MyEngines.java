package oop;

import scala.actors.threadpool.Arrays;

public class MyEngines {

	enum FuelLevel {
		Empty, Reserve, Half, Full
	}

	interface Startable {
		public void start();
	}

	interface Stoppable {
		public void stop();
	}

	abstract class Engine implements Startable, Stoppable {
		int horsePower;
		FuelLevel fuelLevel;
		boolean running = false;

		public Engine(int hp, boolean state, FuelLevel fl) {
			this.horsePower = hp;
			this.running = state;
			this.fuelLevel = fl;
		}

		@Override
		public void start() {
			switch (fuelLevel) {
			case Empty:
				System.out.println("Cannot start without fuel.");
			default: {
				if (!running) {
					running = true;
					System.out.println("Engine started");
				} else {
					System.out.println("Engine already started");
				}
			}
			}
		}

		@Override
		public void stop() {
			if (running) {
				running = false;
				System.out.println("Engine stopped");
			}
		}

		@Override
		public String toString() {
			return String.format(
					"Engine(running: %s, fuelLevel: %s, horsePower: %d BHP)",
					running, fuelLevel, horsePower);
		}
	}

	class TwoStrokeEngine extends Engine {
		String model;

		public TwoStrokeEngine(int hp, boolean state, FuelLevel fl) {
			super(hp, state, fl);
		}

		@Override
		public String toString() {
			return "TwoStroke" + super.toString();
		}

	}

	class FourStrokeEngine extends Engine {
		String model;

		public FourStrokeEngine(int hp, boolean state, FuelLevel fl) {
			super(hp, state, fl);
		}

		@Override
		public String toString() {
			return "FourStroke" + super.toString();
		}

	}

	interface Make {
		public String make();
	}

	class SteamEngine extends Engine implements Make {
		String model = "IndianRailways";

		public SteamEngine(int hp, boolean state, FuelLevel fl) {
			super(hp, state, fl);
		}

		public SteamEngine(int hp, boolean state, FuelLevel fl, String modelName) {
			this(hp, state, fl);
			this.model = modelName;
		}

		@Override
		public String make() {
			return "SteamEngine - " + model;
		}

		@Override
		public String toString() {
			return "Steam" + super.toString();
		}

	}

	public static void main(String[] args) {

		MyEngines me = new MyEngines();

		FourStrokeEngine e1 = me.new FourStrokeEngine(40, false,
				FuelLevel.Empty);
		System.out.println(e1);
		e1.start(); // won't start
		e1.fuelLevel = FuelLevel.Full;
		e1.start(); // will start: fuel-tank is now full
		System.out.println(e1 + "\n");

		TwoStrokeEngine e2 = me.new TwoStrokeEngine(26, false, FuelLevel.Full);
		System.out.println(e2);
		e2.start();
		System.out.println(e2 + "\n");

		SteamEngine e3 = me.new SteamEngine(1800, true, FuelLevel.Reserve);
		System.out.println(e3);
		System.out.println(e3.make() + "\n");

		Engine[] myengines = { e1, e2, e3 };
		System.out.println(Arrays.asList(myengines) + "\n");
	}
}
