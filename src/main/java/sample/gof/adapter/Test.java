package sample.gof.adapter;

import java.util.Scanner;

public class Test {


	public static void main(String[] args) {
		MediaPlayer mediaPlayer = new MediaPlayer();

		System.out.print("Type: ");
		int type = new Scanner(System.in).nextInt();

		mediaPlayer.play(type);
	}
}
