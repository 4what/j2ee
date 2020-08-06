package sample.gof.decorator;

public class TableView extends ViewDecorator {
	public TableView(View decoratedView) {
		super(decoratedView);
	}

	@Override
	public void init() {
		decoratedView.init();

		System.out.println("TableView.init()");

		reload();
	}

	private void reload() {
		System.out.println("TableView.reload()");
	}
}
