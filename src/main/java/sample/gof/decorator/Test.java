package sample.gof.decorator;

public class Test {


	public static void main(String[] args) {
		View tableView = new TableView(new ScrollView(new UIView()));
		tableView.init();
	}
}
