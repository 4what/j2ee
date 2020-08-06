package sample.gof.builder;

import java.util.Scanner;

public class Test {


	public static void main(String[] args) {
		PhoneBuilder builder = null;

		System.out.print("Type: ");
		int type = new Scanner(System.in).nextInt();

		switch (type) {
			case 0:
				builder = new iPhoneBuilder();

				break;
			case 1:
				builder = new NexusBuilder();

				break;
			default:
				break;
		}

		PhoneBuildDirector director = new PhoneBuildDirector(builder);

		Phone phone = director.construct();
		System.out.println(phone);
	}
}
