package sample.gof;

public class Singleton {
/*
	private static final Singleton INSTANCE = new Singleton();

	private Singleton() {}

	public static Singleton getInstance() {
		return INSTANCE;
	}
*/


	/* lazy initialization */
/*
	private static Singleton instance;

	private Singleton() {}

	public static synchronized Singleton getInstance() {
		if (instance == null) {
			instance = new Singleton();
		}
		return instance;
	}
*/


	/* double-checked locking */
	/* for Java 5+ */
/*
	private static volatile Singleton instance;

	private Singleton() {}

	public static Singleton getInstance() {
		if (instance == null) {
			synchronized (Singleton.class) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
*/


	/* initialization-on-demand holder idiom */
	private Singleton() {}

	public static Singleton getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final Singleton INSTANCE = new Singleton();
	}
}
