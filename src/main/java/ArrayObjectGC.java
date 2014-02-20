import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class A {
	public A(int i, String n) {
		id = i;
		name = n;
	}

	int id;
	String name;

	@Override
	public String toString() {
		return String.format("A(%d, %s)", id, name);
	}
}

public class ArrayObjectGC {
	public static void printMemory() {
		int mb = 1024 * 1024;

		// Getting the runtime reference from system
		Runtime runtime = Runtime.getRuntime();

		System.out.println("##### Heap utilization statistics [MB] #####");

		// Print used memory
		System.out.println("Used Memory:"
				+ (runtime.totalMemory() - runtime.freeMemory()) / mb);

		// Print free memory
		System.out.println("Free Memory:" + runtime.freeMemory() / mb);

		// Print total available memory
		System.out.println("Total Memory:" + runtime.totalMemory() / mb);

		// Print Maximum available memory
		System.out.println("Max Memory:" + runtime.maxMemory() / mb);
	}

	public static Map<Integer, A> getMap() {
		int size = 10000000;
		A[] array = new A[size];

		Map<Integer, A> m = new HashMap<Integer, A>();

		for (int i = 0; i < size; i++) {
			Random r = new Random();
			array[i] = new A(i, r.nextInt(1000) + "");
			if (i == 10 || i == 199) {
				m.put(i, array[i]);
			}
		}
		System.out.println("Just after allocation");
		printMemory();

		return m;

	}

	public static void main(String[] args) {
		System.out.println("Before allocation");
		printMemory();
		Map<Integer, A> m = getMap();
		System.out.println(m);
		System.out.println("Just before gc");
		printMemory();
		System.gc();
		System.out.println("Just after gc");
		printMemory();
	}
}
