package sample.gof.iterator;

public class ListIterator implements Iterator {
	private int index;

	private Object[] data;

	public ListIterator(Object[] data) {
		this.data = data;
	}

	@Override
	public boolean hasNext() {
		return index < data.length;
	}

	@Override
	public Object next() {
		if (hasNext()) {
			return data[index++];
		}
		return null;
	}
}
