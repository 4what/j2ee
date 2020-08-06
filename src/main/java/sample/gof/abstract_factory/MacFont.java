package sample.gof.abstract_factory;

public class MacFont implements Font {
	@Override
	public void init() {
		System.out.println("MacFont.init()");
	}
}
