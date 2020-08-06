package sample.gof.bridge;

public class RectShape extends Shape {
	public RectShape(DrawAPI drawAPI) {
		super(drawAPI);
	}

	@Override
	public void draw() {
		drawAPI.drawRect();
	}
}
