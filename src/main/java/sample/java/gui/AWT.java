package sample.java.gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWT extends Frame {
	private TextArea textArea = new TextArea("TextArea");

	private TextField textField = new TextField("TextField");

	public void launch() {
		setTitle("Title");

		Rectangle bounds = getBounds();

		setLocation(bounds.x, bounds.y);

		setSize(640, 480);

		add(textArea, BorderLayout.NORTH);

		add(textField, BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		pack();

		setVisible(true);
	}

	public static void main(String[] args) {
		new AWT().launch();
	}
}
