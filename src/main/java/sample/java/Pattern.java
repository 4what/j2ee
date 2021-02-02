package sample.java;

import java.util.regex.Matcher;

public class Pattern {


	public static void main(String[] args) {
		String input = "-123456789";

		java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("\\d");
		Matcher matcher = pattern.matcher(input);


		/* find */
		while (matcher.find()) {
			System.out.println("find: " + matcher.group());
		}


		/* lookingAt */
		matcher.usePattern(java.util.regex.Pattern.compile("-\\d"));

		if (matcher.lookingAt()) {
			System.out.println("lookingAt: " + matcher.group());
		}


		/* matches */
		matcher.usePattern(java.util.regex.Pattern.compile("-\\d+"));

		if (matcher.matches()) {
			System.out.println("matches: " + matcher.group());
		}
	}
}
