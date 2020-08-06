package sample.gof.template_method;

public class MetalGearSolid extends Game {
	@Override
	public void start() {
		System.out.println("MetalGearSolid.start()");
	}

	@Override
	public void over() {
		System.out.println("MetalGearSolid.over()");
	}
}
