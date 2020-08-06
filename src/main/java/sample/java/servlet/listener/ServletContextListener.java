package sample.java.servlet.listener;

import javax.servlet.ServletContextEvent;

public class ServletContextListener implements javax.servlet.ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener.contextInitialized()");
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("ServletContextListener.contextDestroyed()");
	}
}
