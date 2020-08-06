package sample.gof.template_method;

public class Biohazard extends Game {
	@Override
	public void start() {
		System.out.println("Biohazard.start()");
	}

	@Override
	public void over() {
		System.out.println("Biohazard.over()");
	}
}
