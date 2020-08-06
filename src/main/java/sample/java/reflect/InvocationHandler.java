package sample.java.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvocationHandler implements java.lang.reflect.InvocationHandler {
	private Object target;

	public InvocationHandler(Object target) {
		this.target = target;
	}

	public static Object newInstance(Object target) {
		return Proxy.newProxyInstance(
			target.getClass().getClassLoader(),
			target.getClass().getInterfaces(),
			new InvocationHandler(target));
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		try {
			Object result;

			System.out.println("***** before *****");

			result = method.invoke(target, args);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("***** after *****");
		}

		return null;
	}
}
