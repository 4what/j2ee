package sample.gof.adapter;

public class MediaPlayer {
	private Adapter adapter;

	public MediaPlayer() {
		adapter = new Adapter();
	}

	public void play(int type) {
		adapter.play(type);
	}
}
