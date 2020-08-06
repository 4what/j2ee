package sample.java.observer;

import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class Test {


	public static void main(String[] args) {
		Object object = new Object();

		object.addObserver(new Observer() {
			@Override
			public void update(Observable o, java.lang.Object arg) {
				System.out.println("o = [" + o + "], arg = [" + arg + "]");
			}
		});

		object.setValue(new Date());
	}
}
