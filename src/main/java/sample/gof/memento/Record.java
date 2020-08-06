package sample.gof.memento;

public class Record {
	private Game game;
	private Object state;

	public Object getState() {
		return state;
	}

	public Record(Game game, Object state) {
		this.game = game;
		this.state = state;
	}
}
