package sample.java;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;


/**
 * https://docs.oracle.com/javase/tutorial/essential/environment/properties.html
 */
public class Properties {


	public static void main(String[] args) throws IOException {
		/* Properties */
		InputStream input =
			//new FileInputStream("src/main/resources/system.properties")
			Properties.class.getResourceAsStream("/system.properties")
		;
		try {
			java.util.Properties props = new java.util.Properties();

			props.load(new InputStreamReader(input, "UTF-8"));

			System.out.println("sys.debug: " + props.getProperty("sys.debug"));
		} finally {
			input.close();
		}


		/* PropertyResourceBundle */
		ResourceBundle bundle = PropertyResourceBundle.getBundle("system");

		/* ISO-8859-1 */
		System.out.println("sys.debug: " + bundle.getString("sys.debug"));
	}
}
