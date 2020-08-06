package sample.gof.proxy;

public class Picture implements Image {
	public Picture() {
		load();
	}

	@Override
	public void display() {
		System.out.println("Picture.display()");
	}

	private void load() {
		System.out.println("Picture.load()");
	}
}
