package sample.gof.chain_of_responsibility;

public abstract class Logger {
	public static int ERROR = 3;
	public static int DEBUG = 7;

	protected int level;
	private Logger next;

	public Logger setNext(Logger logger) {
		this.next = logger;
		return this;
	}

	public void message(String msg, int level) {
		if (level <= this.level) {
			write(msg);

			if (next != null) {
				next.message(msg, level);
			}
		}
	}

	public abstract void write(String msg);
}
