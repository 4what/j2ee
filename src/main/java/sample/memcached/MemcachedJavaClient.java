package sample.memcached;

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

/**
 * Lets say you have 3 servers.  Server 1 and server 2 have 3GB of space
 * and server 3 has 2GB of space for cache.  Here is how I would set up
 * my client.
 */
public class MemcachedJavaClient {
	// create a static client as most installs only need
	// a single instance
	protected static MemCachedClient mc = new MemCachedClient();

	// set up connection pool once at class load
	static {
		// server list and weights
		String[] servers = {
			//"localhost:11211", "192.168.0.1:11211", "example.com:11211"
			"localhost:11211"
		};

		Integer[] weights = {
			//5, 3, 1
			5
		};

		// grab an instance of our connection pool
		SockIOPool pool = SockIOPool.getInstance();

		// set the servers and the weights
		pool.setServers(servers);
		pool.setWeights(weights);

		// set some basic pool settings
		// 5 initial, 5 min, and 250 max conns
		// and set the max idle time for a conn
		// to 6 hours
		pool.setInitConn(5);
		pool.setMinConn(5);
		pool.setMaxConn(250);
		pool.setMaxIdle(1000 * 60 * 60 * 6);

		// set the sleep for the maint thread
		// it will wake up every x seconds and
		// maintain the pool size
		pool.setMaintSleep(30);

		// set some TCP settings
		// disable nagle
		// set the read timeout to 3 secs
		// and don't set a connect timeout
		pool.setNagle(false);
		pool.setSocketTO(1000 * 3);
		pool.setSocketConnectTO(0);

		// initialize the connection pool
		pool.initialize();

		// lets set some compression on for the client
		// compress anything larger than 64k
		//mc.setCompressEnable(true);
		//mc.setCompressThreshold(1024 * 64);
	}

/*
	// from here on down, you can call any of the client calls
	public static void examples() {
		mc.set("foo", "This is a test String");
		String bar = (String) mc.get("foo");
	}
*/


	public static void main(String[] args) {
		String key = "key";
		String counter = "counter";

		mc.set(key, "value");
		System.out.println("set: " + mc.get(key));

		mc.add(key, "foo");
		System.out.println("add: " + mc.get(key));

		mc.replace(key, "bar");
		System.out.println("replace: " + mc.get(key));

		//mc.storeCounter(counter, 0);
		//System.out.println("getCounter: " + mc.getCounter(counter));

		//System.out.println("incr: " + mc.incr(counter));
		//System.out.println("incr: " + mc.incr(counter, 100));

		//System.out.println("decr: " + mc.decr(counter));
		//System.out.println("decr: " + mc.decr(counter, 100));

		// thread safe
		System.out.println("addOrIncr: " + mc.addOrIncr(counter));
		System.out.println("addOrIncr: " + mc.addOrIncr(counter, 100));

		System.out.println("addOrDecr: " + mc.addOrDecr(counter));
		System.out.println("addOrDecr: " + mc.addOrDecr(counter, 100));

		mc.delete(key);
		System.out.println("keyExists: " + mc.keyExists(key));

		mc.flushAll();
	}
}
