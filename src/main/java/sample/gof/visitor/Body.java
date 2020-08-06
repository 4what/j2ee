package sample.gof.visitor;

public class Body implements Element {
	@Override
	public void accept(Visitor visitor) {
		System.out.println("Body.accept()");

		visitor.visit(this);
	}
}
