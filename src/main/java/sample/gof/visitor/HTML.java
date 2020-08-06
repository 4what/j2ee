package sample.gof.visitor;

public class HTML implements Element {
	private Element[] elements;

	public HTML() {
		elements = new Element[] {
			new Head(),
			new Body()
		};
	}

	@Override
	public void accept(Visitor visitor) {
		System.out.println("HTML.accept()");

		visitor.visit(this);

		for (Element element : elements) {
			element.accept(visitor);
		}
	}
}
