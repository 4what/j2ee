package sample.java.concurrent.thread;

import java.lang.Thread;

public class Runnable implements java.lang.Runnable {
	@Override
	public void run() {
		System.out.println("Runnable.run()");
	}


	public static void main(String[] args) {
		(new Thread(new Runnable())).start();
	}
}
