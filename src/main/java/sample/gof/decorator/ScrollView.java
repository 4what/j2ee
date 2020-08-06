package sample.gof.decorator;

public class ScrollView extends ViewDecorator {
	public ScrollView(View decoratedView) {
		super(decoratedView);
	}

	@Override
	public void init() {
		decoratedView.init();

		System.out.println("ScrollView.init()");

		scroll();
	}

	private void scroll() {
		System.out.println("ScrollView.scroll()");
	}
}
