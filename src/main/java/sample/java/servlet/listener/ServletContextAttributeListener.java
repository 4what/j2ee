package sample.java.servlet.listener;

import javax.servlet.ServletContextAttributeEvent;

public class ServletContextAttributeListener implements javax.servlet.ServletContextAttributeListener {
	@Override
	public void attributeAdded(ServletContextAttributeEvent servletContextAttributeEvent) {
		System.out.println("ServletContextAttributeListener.attributeAdded()");
	}

	@Override
	public void attributeRemoved(ServletContextAttributeEvent servletContextAttributeEvent) {
		System.out.println("ServletContextAttributeListener.attributeRemoved()");
	}

	@Override
	public void attributeReplaced(ServletContextAttributeEvent servletContextAttributeEvent) {
		System.out.println("ServletContextAttributeListener.attributeReplaced()");
	}
}
