package sample.gof.observer;

import java.util.ArrayList;
import java.util.List;

public class Object {
	private java.lang.Object value;

	public java.lang.Object getValue() {
		return value;
	}

	public void setValue(java.lang.Object value) {
		this.value = value;

		notifyObservers(value);
	}

	private List<Observer> observers;

	public Object() {
		observers = new ArrayList<>();
	}

	public void addObserver(Observer observer) {
		if (!observers.contains(observer)) {
			observers.add(observer);
		}
	}

	private void notifyObservers(java.lang.Object arg) {
		for (Observer observer : observers) {
			observer.update(this, arg);
		}
	}

	public void removeObserver(Observer observer) {
		observers.remove(observer);
	}
}
