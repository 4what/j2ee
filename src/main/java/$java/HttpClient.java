package $java;

import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpClient {
	private static PoolingHttpClientConnectionManager manager = new PoolingHttpClientConnectionManager();

	static {
		manager.setDefaultMaxPerRoute(10);
		manager.setMaxTotal(100);
	}

	/**
	 * @param proxy
	 * @return
	 */
	public static CloseableHttpClient getClient(Map<String, String> proxy) {
		HttpClientBuilder builder = HttpClients.custom().setConnectionManager(manager).setConnectionManagerShared(true);

		// proxy
		if (proxy != null) {
			builder.setProxy(new HttpHost(proxy.get("host"), Integer.parseInt(proxy.get("port")))); // TODO: ?
		}

		return builder.build();
	}

	/**
	 * @param proxy
	 * @param charset
	 * @param header
	 * @param type
	 * @param url
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static String request(Map<String, String> proxy, String charset, Map<String, String> header, String type, String url, Map<String, String> data) throws IOException {
		CloseableHttpClient client = getClient(proxy);
		try {
			charset = charset == null ? "UTF-8" : charset;

			HttpRequestBase method = new HttpGet(url);

			// POST
			if (type.equals("POST")) {
				HttpPost post = new HttpPost(url);

				// data
				if (data != null) {
					List<NameValuePair> params = new ArrayList<>();

					for (Map.Entry<String, String> entry : data.entrySet()) {
						params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
					}

					post.setEntity(new UrlEncodedFormEntity(params, charset));
				}

				method = post;
			}

			// header
			if (header != null) {
				for (Map.Entry<String, String> entry : header.entrySet()) {
					method.setHeader(entry.getKey(), entry.getValue());
				}
			}

			CloseableHttpResponse response = client.execute(method);
			try {
				return EntityUtils.toString(response.getEntity(), charset);
			} finally {
				response.close();
			}
		} finally {
			client.close();
		}
	}


	public static void main(String[] args) throws IOException {
		Map<String, String> proxy = new HashMap<String, String>() {
			{
				put("host", "192.168.90.254");
				put("port", "8080");
			}
		};
		proxy = null;

		String charset = "GBK";

		Map<String, String> header = new HashMap<String, String>() {
			{
				put("name", "value");
			}
		};

		String type = "GET";

		String url = "http://localhost:8080/" + "?timestamp=" + System.currentTimeMillis();

		Map<String, String> data = new HashMap<String, String>() {
			{
				put("name", "value");
			}
		};

		String result = request(proxy, charset, header, type, url, data);
		System.out.println("result: " + result);


/*
		// Fluent

		// GET
		System.out.println(
			Request.Get(url)
				.setHeader("name", "value")
				//.viaProxy(new HttpHost("127.0.0.1", 8080))
				.execute()
				.returnContent().asString()
		);

		// POST
		System.out.println(
			Request.Post(url)
				.bodyForm(Form.form().add("name", "value").build(), Charset.forName("UTF-8"))
				//.bodyString("{\"name\": \"value\"}", ContentType.APPLICATION_JSON)
				.execute()
				.returnContent().asString()
		);
*/
	}
}
