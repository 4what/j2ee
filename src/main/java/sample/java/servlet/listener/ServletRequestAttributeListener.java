package sample.java.servlet.listener;

import javax.servlet.ServletRequestAttributeEvent;

public class ServletRequestAttributeListener implements javax.servlet.ServletRequestAttributeListener {
	@Override
	public void attributeAdded(ServletRequestAttributeEvent servletRequestAttributeEvent) {
		System.out.println("ServletRequestAttributeListener.attributeAdded()");
	}

	@Override
	public void attributeRemoved(ServletRequestAttributeEvent servletRequestAttributeEvent) {
		System.out.println("ServletRequestAttributeListener.attributeRemoved()");
	}

	@Override
	public void attributeReplaced(ServletRequestAttributeEvent servletRequestAttributeEvent) {
		System.out.println("ServletRequestAttributeListener.attributeReplaced()");
	}
}
