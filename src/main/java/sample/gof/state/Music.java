package sample.gof.state;

public class Music {
	private State state;

	public void setState(State state) {
		this.state = state;

		this.state.handler();
	}
}
