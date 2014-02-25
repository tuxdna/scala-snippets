package oop;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import scala.actors.threadpool.Arrays;

public class OOPExamples {

	public static void main(String[] args) {
		Person[] persons = { new Person(2, "amar"), new Person(3, "akbar"),
				new Person(), new Person(1, "anthony")

		};

		List<Person> list = Arrays.asList(persons);
		System.out.println(list);
		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.age - o2.age;
			}
		});
		System.out.println(list);
		Collections.sort(list, new Comparator<Person>() {
			@Override
			public int compare(Person o1, Person o2) {
				return o1.name.compareTo(o2.name);
			}
		});
		System.out.println(list);
	}
}
