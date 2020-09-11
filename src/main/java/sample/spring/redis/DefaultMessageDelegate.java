package sample.spring.redis;

import java.io.Serializable;

public class DefaultMessageDelegate implements MessageDelegate {
	@Override
	public void handleMessage(Serializable message, String channel) {
		System.out.println("message: " + message);
		System.out.println("channel: " + channel);
	}
}
