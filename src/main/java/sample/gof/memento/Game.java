package sample.gof.memento;

public class Game {
	private Object state;

	public void setState(Object state) {
		this.state = state;
		System.out.println("set: " + state);
	}

	public Record save() {
		System.out.println("save: " + state);
		return new Record(this, state);
	}

	public void load(Record record) {
		state = record.getState();
		System.out.println("load: " + state);
	}
}
