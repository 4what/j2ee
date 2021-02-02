package sample;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Dom4j {


	public static void main(String[] args) throws DocumentException {
		/* parse */
		Document document = DocumentHelper.parseText("<root><node id=\"1\">data</node></root>");

		Element root = document.getRootElement();

		for (Object item : root.elements()) {
			Element element = (Element) item;
			System.out.println("element: " + element);

			System.out.println("name: " + element.getName());
			System.out.println("value: " + element.getText());

			System.out.println("attribute: " + element.attribute("id").getValue());
		}
	}
}
