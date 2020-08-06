package $java;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class ZXing {
	private static String charset = "UTF-8";

	/**
	 * @param image
	 * @return
	 * @throws ChecksumException
	 * @throws FormatException
	 * @throws NotFoundException
	 */
	public static Result decode(BufferedImage image) throws ChecksumException, FormatException, NotFoundException {
		MultiFormatReader reader = new MultiFormatReader();

		LuminanceSource source = new BufferedImageLuminanceSource(image);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

		Hashtable<DecodeHintType, Object> hints = new Hashtable<>();
		hints.put(DecodeHintType.CHARACTER_SET, charset);

/*
		List<BarcodeFormat> formats = new ArrayList<>();
		formats.add(BarcodeFormat.QR_CODE);

		hints.put(DecodeHintType.POSSIBLE_FORMATS, formats);
*/

		//hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);

		Result result = reader.decode(bitmap, hints);
		//System.out.println("text: " + result.getText());
		//System.out.println("format: " + result.getBarcodeFormat());

		return result;
	}

	/**
	 * @param content
	 * @param width
	 * @param height
	 * @return
	 * @throws WriterException
	 */
	public static BufferedImage encode(String content, int width, int height) throws WriterException {
		return MatrixToImageWriter.toBufferedImage(toMatrix(content, BarcodeFormat.QR_CODE, width, height));
	}

	/**
	 * @param content
	 * @param width
	 * @param height
	 * @param format
	 * @param pathname
	 * @return
	 * @throws IOException
	 * @throws WriterException
	 */
	public static String encode(String content, int width, int height, String format, String pathname) throws IOException, WriterException {
		MatrixToImageWriter.writeToPath(toMatrix(content, BarcodeFormat.QR_CODE, width, height), format, new File(pathname).toPath());

		return pathname;
	}

	/**
	 * @param content
	 * @param format
	 * @param width
	 * @param height
	 * @return
	 * @throws WriterException
	 */
	public static BitMatrix toMatrix(String content, BarcodeFormat format, int width, int height) throws WriterException {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, charset);
		hints.put(EncodeHintType.MARGIN, 0);

		BitMatrix matrix = new MultiFormatWriter().encode(content, format, width, height, hints);

		return matrix;
	}
}
