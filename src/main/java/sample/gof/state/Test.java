package sample.gof.state;

public class Test {


	public static void main(String[] args) {
		Music music = new Music();

		music.setState(new PlayState());

		music.setState(new StopState());
	}
}
