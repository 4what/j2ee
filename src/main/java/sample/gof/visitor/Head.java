package sample.gof.visitor;

public class Head implements Element {
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Head.accept()");

		visitor.visit(this);
	}
}
