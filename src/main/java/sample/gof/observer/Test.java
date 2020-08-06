package sample.gof.observer;

import java.util.Date;

public class Test {


	public static void main(String[] args) {
		Object o = new Object();

		o.addObserver(new ObjectObserver());

		o.setValue(new Date());
	}
}
