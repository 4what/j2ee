package sample.gof.composite;

import java.util.ArrayList;
import java.util.List;

public class Layer {
	private List<Layer> sublayers;

	public List<Layer> getSublayers() {
		return sublayers;
	}

	public Layer() {
		sublayers = new ArrayList<>();
	}

	public void add(Layer layer) {
		sublayers.add(layer);
	}

	public void remove(Layer layer) {
		sublayers.remove(layer);
	}


	public static void main(String[] args) {
		Layer containerLayer = new Layer();
		System.out.println("containerLayer: " + containerLayer);

		Layer contentLayer = new Layer();
		System.out.println("contentLayer: " + contentLayer);

		containerLayer.add(contentLayer);

		System.out.println(containerLayer.getSublayers());
	}
}
