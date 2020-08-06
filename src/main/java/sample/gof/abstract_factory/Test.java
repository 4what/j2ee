package sample.gof.abstract_factory;

import java.util.Scanner;

public class Test {


	public static void main(String[] args) {
		SystemFactory factory = null;

		System.out.print("Type: ");
		int type = new Scanner(System.in).nextInt();

		switch (type) {
			case 0:
				factory = new MacFactory();

				break;
			case 1:
				factory = new WinFactory();

				break;
			default:
				break;
		}

		Color color = factory.createColor();
		color.init();

		Font font = factory.createFont();
		font.init();
	}
}
