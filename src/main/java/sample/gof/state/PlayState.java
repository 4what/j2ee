package sample.gof.state;

public class PlayState implements State {
	@Override
	public void handler() {
		System.out.println("PlayState.handler()");
	}
}
