package sample.gof.iterator;

public class Test {


	public static void main(String[] args) {
		Object[] data = new Object[]{
			"foo", "bar"
		};

		List list = new List(data);

		Iterator iterator = list.getIterator();

		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
