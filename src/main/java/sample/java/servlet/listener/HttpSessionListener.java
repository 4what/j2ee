package sample.java.servlet.listener;

import javax.servlet.http.HttpSessionEvent;

public class HttpSessionListener implements javax.servlet.http.HttpSessionListener {
	@Override
	public void sessionCreated(HttpSessionEvent httpSessionEvent) {
		System.out.println("HttpSessionListener.sessionCreated()");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
		System.out.println("HttpSessionListener.sessionDestroyed()");
	}
}
