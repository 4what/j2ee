package sample.gof.abstract_factory;

public class WinFont implements Font {
	@Override
	public void init() {
		System.out.println("WinFont.init()");
	}
}
