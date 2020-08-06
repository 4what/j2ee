package sample.gof.template_method;

public class Test {


	public static void main(String[] args) {
		Game bio = new Biohazard();
		bio.play();

		Game mgs = new MetalGearSolid();
		mgs.play();
	}
}
