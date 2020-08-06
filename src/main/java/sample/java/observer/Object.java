package sample.java.observer;

import java.util.Observable;

public class Object extends Observable {
	private java.lang.Object value;

	public java.lang.Object getValue() {
		return value;
	}

	public void setValue(java.lang.Object value) {
		this.value = value;

		setChanged();

		notifyObservers(value);
	}
}
