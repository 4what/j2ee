package sample.gof.chain_of_responsibility;

public abstract class Logger {
	public static int ERROR = 3;
	public static int DEBUG = 7;

	protected int level;

	private Logger next;

	public Logger setNext(Logger next) {
		this.next = next;
		return this;
	}

	public void message(String msg, int level) {
		if (this.level <= level) {
			write(msg);
		} else if (next != null) {
			next.message(msg, level);
		}
	}

	public abstract void write(String msg);
}
