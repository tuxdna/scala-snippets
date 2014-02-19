package oop;

public class MyEngines {

	enum FuelLevel {
		Empty, Reserve, Half, Full
	}

	interface Make {
		public String model();
	}

	interface Startable {
		public void start();
	}

	interface Stoppable {
		public void stop();
	}

	abstract class Engine implements Startable, Stoppable {
		private int horsePower;
		private FuelLevel fuelLevel;
		private boolean running = false;

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
					"Engine(running: %s, fuelLevel: %s, horsePower: %d)",
					running, fuelLevel, horsePower);
		}
	}

	class SteamEngine extends Engine implements Make {

		@Override
		public String model() {
			// TODO Auto-generated method stub
			return null;
		}

	}
}
