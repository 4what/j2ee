package sample.java.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Input {


	public static void main(String[] args) throws IOException {
		System.out.println("args: " + Arrays.toString(args));

		System.out.println(new BufferedReader(new InputStreamReader(System.in)).readLine());

		System.out.println(new Scanner(System.in).next());
	}
}
