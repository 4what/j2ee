package sample.gof.decorator;

public abstract class ViewDecorator implements View {
	protected View decoratedView;

	public ViewDecorator(View decoratedView) {
		this.decoratedView = decoratedView;
	}
}
