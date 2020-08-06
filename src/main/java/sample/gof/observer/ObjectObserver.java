package sample.gof.observer;

public class ObjectObserver implements Observer {
	@Override
	public void update(Object o, java.lang.Object arg) {
		System.out.println("o = [" + o + "], arg = [" + arg + "]");
	}
}
