package sample.java;

import java.math.RoundingMode;

public class DecimalFormat {


	public static void main(String[] args) {
		java.text.DecimalFormat df = new java.text.DecimalFormat(".##");
		df.setRoundingMode(RoundingMode.HALF_UP);

		System.out.println(df.format(123456789.987654321));
	}
}
