package sample.java.servlet.listener;

import javax.servlet.ServletRequestEvent;

public class ServletRequestListener implements javax.servlet.ServletRequestListener {
	@Override
	public void requestInitialized(ServletRequestEvent servletRequestEvent) {
		System.out.println("ServletRequestListener.requestInitialized()");
	}

	@Override
	public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
		System.out.println("ServletRequestListener.requestDestroyed()");
	}
}
