package sample.gof.visitor;

public class CrawlerVisitor implements Visitor {
	@Override
	public void visit(HTML html) {
		System.out.println("CrawlerVisitor.visit(HTML)");
	}

	@Override
	public void visit(Head head) {
		System.out.println("CrawlerVisitor.visit(Head)");
	}

	@Override
	public void visit(Body body) {
		System.out.println("CrawlerVisitor.visit(Body)");
	}
}
