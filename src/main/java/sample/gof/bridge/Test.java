package sample.gof.bridge;

public class Test {


	public static void main(String[] args) {
		Shape shape = new RectShape(new DrawRectAPI());
		shape.draw();
	}
}
