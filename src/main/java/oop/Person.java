package oop;

public class Person {
	int age;
	String name;

	public Person() {
		age = 0; // just born
		name = "new-born";
	}

	public Person(int a, String n) {
		age = a;
		name = n;
	}

	@Override
	public String toString() {
		return String.format("Person(%d, %s)", age, name);
	}

}