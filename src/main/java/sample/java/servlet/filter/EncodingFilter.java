package sample.java.servlet.filter;

import javax.servlet.*;
import java.io.IOException;

public class EncodingFilter implements Filter {
	private FilterConfig filterConfig;

	private String encoding;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;

		this.encoding = filterConfig.getInitParameter("encoding");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(encoding);

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		this.filterConfig = null;

		this.encoding = null;
	}
}
