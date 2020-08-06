package sample.java.servlet.listener;

import javax.servlet.http.HttpSessionBindingEvent;

public class HttpSessionAttributeListener implements javax.servlet.http.HttpSessionAttributeListener {
	@Override
	public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
		System.out.println("HttpSessionAttributeListener.attributeAdded()");
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
		System.out.println("HttpSessionAttributeListener.attributeRemoved()");
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {
		System.out.println("HttpSessionAttributeListener.attributeReplaced()");
	}
}
