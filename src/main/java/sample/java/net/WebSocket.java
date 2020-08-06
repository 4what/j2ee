package sample.java.net;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;

/**
 * Java 7+ & Tomcat 7+
 *
 * http://docs.oracle.com/javaee/7/api/index.html?javax/websocket/package-summary.html
 * http://www.oracle.com/technetwork/articles/java/jsr356-1937161.html
 */
@ServerEndpoint("/websocket/{id}")
public class WebSocket {
	private static final HashMap<String, Session> clients = new HashMap<>();

	@OnClose
	public void onClose(CloseReason closeReason) {
		System.out.println("onClose");
	}

	@OnError
	public void onError(Throwable throwable) {
		System.out.println("onError");
	}

	@OnMessage
	public void onMessage(String message, Session session, @PathParam("id") String id) throws IOException {
		System.out.println("onMessage");

		String to = message;

		clients.get(to).getBasicRemote().sendText(id + ": hello");
	}

	@OnOpen
	public void onOpen(EndpointConfig endpointConfig, Session session, @PathParam("id") String id) throws IOException {
		System.out.println("onOpen");

		clients.put(id, session);

		session.getBasicRemote().sendText("server: hello");
	}
}
