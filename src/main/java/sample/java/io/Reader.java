package sample.java.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {


	public static void main(String[] args) throws IOException {
		String pathname = "file.txt";

		BufferedReader reader = new BufferedReader(new FileReader(new java.io.File(pathname)));


		/* char */
/*
		int character;

		while ((character = reader.read()) != -1) {
			// "\n", "\r"
			System.out.println((char) character);
		}
*/


		/* line */
		String line;

		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}


		reader.close();
	}
}
