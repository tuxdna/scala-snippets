package monads;

public class Monadic {

	class Baz {
		public int compute() {
			return 0;
		}
	}

	class Bar {

		public Baz getBaz() {
			return new Baz();
		}
	}

	class Foo {
		public Bar getBar() {
			return new Bar();
		}
	}

	public Integer comute(Foo foo) {
		if (foo != null) {
			Bar bar = foo.getBar();
			if (bar != null) {
				Baz baz = bar.getBaz();
				if (baz != null) {
					return baz.compute();
				} else {
					return null;
				}
			} else {
				return null;
			}
		} else
			return null;
	}
}