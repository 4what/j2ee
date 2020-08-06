package sample.java.reflect;

public class Proxy {


	public static void main(String[] args) {
		Class cls = (Class) InvocationHandler.newInstance(new Clazz());
		cls.method();
	}
}
