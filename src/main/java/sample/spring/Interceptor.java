package sample.spring;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Interceptor
	//implements HandlerInterceptor
	extends HandlerInterceptorAdapter
{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("Interceptor.preHandle()");
		System.out.println("handler: " + handler);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("Interceptor.postHandle()");
		System.out.println("handler: " + handler);
		System.out.println("modelAndView: " + modelAndView);

		//modelAndView.setViewName("view");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("Interceptor.afterCompletion()");
		System.out.println("handler: " + handler);
		System.out.println("ex: " + ex);
	}
}
