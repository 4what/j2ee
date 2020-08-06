package sample.gof.visitor;

public class Test {


	public static void main(String[] args) {
		HTML html = new HTML();
		html.accept(new CrawlerVisitor());
	}
}
