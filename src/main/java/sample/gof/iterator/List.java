package sample.gof.iterator;

public class List implements Collection {
	private Object[] data;

	public List(Object[] data) {
		this.data = data;
	}

	@Override
	public Iterator getIterator() {
		return new ListIterator(data);
	}
}
