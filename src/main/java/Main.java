import java.util.Collections;
import java.util.Vector;

public class Main {
	public static void main(String args[]) {
		Vector<Integer> vec = new Vector<Integer>();
		System.out.println("Befor sorting");
		for (int index = 0; index < 10; index++) {
			int rand = (int) (1000 * Math.random());
			vec.add(rand);
			System.out.println(rand);
		}

		Collections.sort(vec);

		System.out.println("After sorting");
		for (Integer intnum : vec) {
			System.out.println(intnum);
		}
	}
}