package sample.gof.abstract_factory;

public class MacFactory implements SystemFactory {
	@Override
	public Color createColor() {
		return new MacColor();
	}

	@Override
	public Font createFont() {
		return new MacFont();
	}
}
