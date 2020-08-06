package sample.gof.state;

public class StopState implements State {
	@Override
	public void handler() {
		System.out.println("StopState.handler()");
	}
}
