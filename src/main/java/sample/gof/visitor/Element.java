package sample.gof.visitor;

public interface Element {
	void accept(Visitor visitor);
}
