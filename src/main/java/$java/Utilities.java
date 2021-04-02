package $java;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Utilities {
	/**
	 * @param request
	 * @param name
	 * @return
	 */
	public static Cookie cookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}

		return null;
	}

	/**
	 * @param url
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static String encode(String url) throws UnsupportedEncodingException {
		return URLEncoder.encode(url, "UTF-8");
	}

	/**
	 * @param request
	 * @return
	 */
	public static String href(HttpServletRequest request) {
		return request.getRequestURL() + (request.getQueryString() != null ? ("?" + request.getQueryString()) : "");
	}

	/**
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static String httpget(String url) throws IOException {
		url += (!url.contains("?") ? "?" : "&") + "timestamp=" + System.currentTimeMillis();

		return HttpClient.request(null, "GBK", null, "GET", url, null);
	}

	/**
	 * @param request
	 * @return
	 */
	public static String ip(HttpServletRequest request) {
		String remoteAddr = request.getRemoteAddr();
		String xForwardedFor = request.getHeader("x-forwarded-for");
		String xRealIp = request.getHeader("x-real-ip");

		return xRealIp == null ? (
			xForwardedFor == null ?
				remoteAddr : // no proxy
				(xForwardedFor + ", " + remoteAddr)
		) : ( // for Nginx
			xForwardedFor == null ?
				xRealIp :
				(xForwardedFor.contains(xRealIp) ? xForwardedFor : (xForwardedFor + ", " + xRealIp))
		);
	}

	/**
	 * @param min
	 * @param max
	 * @return
	 */
	public static int random(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}

	/**
	 * @param size
	 * @param min
	 * @param max
	 * @return
	 */
	public static ArrayList<Integer> randoms(int size, int min, int max) {
		ArrayList<Integer> array = new ArrayList<>();
		int element;

		while (array.size() < size) {
			element = random(min, max);

			if (!array.contains(element)) {
				array.add(element);
			}
		}
		return array;
	}

	/**
	 * @param request
	 * @return
	 */
	public static String root(HttpServletRequest request) {
		return "//" + request.getHeader("host") + request.getContextPath();
	}

	/**
	 * @see com.javafx.tools.doclets.internal.toolkit.util.Util#stripHtml(java.lang.String)
	 *
	 * @param html
	 * @return
	 */
	public static String stripHtml(String html) {
        return html.replaceAll("\\<.*?>", " ").replaceAll("\\b\\s{2,}\\b", " ").trim();
	}
}
