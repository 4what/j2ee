package sample.java.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {


	public static void main(String[] args) throws IOException {
		String pathname = "file.txt";

		BufferedWriter writer = new BufferedWriter(new FileWriter(new java.io.File(pathname)));

		String text = "text";

		writer.write(text);

		writer.flush();

		writer.close();
	}
}
