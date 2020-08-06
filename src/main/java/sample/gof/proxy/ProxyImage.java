package sample.gof.proxy;

public class ProxyImage implements Image {
	private Picture image;

	@Override
	public void display() {
		if (image == null) {
			image = new Picture();
		}
		image.display();
	}
}
