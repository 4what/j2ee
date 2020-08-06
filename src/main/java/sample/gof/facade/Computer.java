package sample.gof.facade;

public class Computer {
	private Hardware hardware;
	private Software software;

	public Computer() {
		hardware = new Hardware();
		software = new Software();
	}

	public void start() {
		hardware.init();
		software.init();

		System.out.println("Computer.start()");
	}
}
