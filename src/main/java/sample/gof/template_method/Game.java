package sample.gof.template_method;

public abstract class Game {
	public abstract void start();
	public abstract void over();

	public final void play() {
		start();
		over();
	}
}
