package sample.java.servlet.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlFilter implements Filter {
	private FilterConfig filterConfig;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		String from = "(/.+?)\\.html$";
		String to;

		Pattern pattern = Pattern.compile(from);
		Matcher matcher = pattern.matcher(((HttpServletRequest) request).getServletPath());

		if (matcher.matches()) {
			to = matcher.group(1) + ".jsp";
			System.out.println("to: " + to);

			request.getRequestDispatcher(to).forward(request, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		this.filterConfig = null;
	}
}
