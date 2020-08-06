package sample.gof.abstract_factory;

public class WinFactory implements SystemFactory {
	@Override
	public Color createColor() {
		return new WinColor();
	}

	@Override
	public Font createFont() {
		return new WinFont();
	}
}
