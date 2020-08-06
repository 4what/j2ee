package sample.gof.bridge;

public class DrawRectAPI implements DrawAPI {
	@Override
	public void drawRect() {
		System.out.println("DrawRectAPI.drawRect()");
	}
}
