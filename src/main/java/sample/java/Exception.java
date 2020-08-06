package sample.java;

public class Exception extends java.lang.Exception {


	public static void main(String[] args) {
		try {
			throw new Exception();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
