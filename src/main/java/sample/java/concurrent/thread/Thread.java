package sample.java.concurrent.thread;

public class Thread extends java.lang.Thread {
	@Override
	public void run() {
		System.out.println("Thread.run()");
	}


	public static void main(String[] args) {
		(new Thread()).start();
	}
}
