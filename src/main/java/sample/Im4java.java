package sample;

import org.im4java.core.*;

import java.io.IOException;

public class Im4java {


	public static void main(String[] args) throws IM4JavaException, InterruptedException, IOException {
		IMOperation op;


		ConvertCmd convertCmd =
			//new ConvertCmd() // ImageMagick
			new ConvertCmd(true) // GraphicsMagick
		;
		//GraphicsMagickCmd convertCmd = new GraphicsMagickCmd("convert"); // GraphicsMagick

		op = new IMOperation();

		op.addImage("in.jpg");

		op.resize(100, 100);
		op.addImage("out.jpg");

		convertCmd.run(op);


		CompositeCmd compositeCmd = new CompositeCmd(true);

		op = new IMOperation();

		op.dissolve(50);

		op.geometry(100, 100, 100, 100);
		//op.gravity("SouthEast"); // NorthWest, North, NorthEast, West, Center, East, SouthWest, South, SouthEast

		op.addImage("watermark.jpg");

		op.addImage("in.jpg");
		op.addImage("out.jpg");

		compositeCmd.run(op);
	}
}
