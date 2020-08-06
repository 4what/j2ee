package sample.gof.prototype;

public class Test {


	public static void main(String[] args) throws CloneNotSupportedException {
		Class clazz = new Clazz();
		System.out.println("clazz: " + clazz);

		Class clone = clazz.clone();
		System.out.println("clone: " + clone);
	}
}
