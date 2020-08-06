package sample.gof.visitor;

public interface Visitor {
	void visit(HTML html);
	void visit(Head head);
	void visit(Body body);
}
