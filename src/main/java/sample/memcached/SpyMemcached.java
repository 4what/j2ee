package sample.memcached;

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;

import java.io.IOException;

public class SpyMemcached {


	public static void main(String[] args) throws IOException {
		MemcachedClient mc = new MemcachedClient(
			//new BinaryConnectionFactory(),
			AddrUtil.getAddresses(
				//"localhost:11211, 192.168.0.1:11211, example.com:11211"
				"localhost:11211"
			)
		);

		String key = "key";
		String counter = "counter";

		mc.set(key, 0, "value");
		System.out.println("set: " + mc.get(key));

		mc.add(key, 0, "foo");
		System.out.println("add: " + mc.get(key));

		mc.replace(key, 0, "bar");
		System.out.println("replace: " + mc.get(key));

		mc.prepend(key, "prefix-");
		System.out.println("prepend: " + mc.get(key));

		mc.append(key, "-suffix");
		System.out.println("append: " + mc.get(key));

		System.out.println("incr: " + mc.incr(counter, 0, 0));
		System.out.println("incr: " + mc.incr(counter, 100));

		System.out.println("decr: " + mc.decr(counter, 0, 0));
		System.out.println("decr: " + mc.decr(counter, 100));

		mc.delete(key);
		System.out.println("delete: " + mc.get(key));

		mc.flush();
	}
}
