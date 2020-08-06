package sample.gof.adapter;

public class Adapter {
	private AudioPlayer audioPlayer;
	private VideoPlayer videoPlayer;

	public Adapter() {
		audioPlayer = new AudioPlayer();
		videoPlayer = new VideoPlayer();
	}

	public void play(int type) {
		switch (type) {
			case 0:
				audioPlayer.play();

				break;
			case 1:
				videoPlayer.play();

				break;
			default:
				break;
		}
	}
}
