package sample;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

import java.io.File;
import java.io.IOException;

public class Jsoup {


	public static void main(String[] args) throws IOException {
		/* Parsing and traversing a Document */
/*
		String html = "<html><head><title></title></head><body></body></html>";
		Document doc = org.jsoup.Jsoup.parse(html);
		System.out.println("doc: " + doc);
*/


		/* Parsing a body fragment */
/*
		String html = "<div>Lorem ipsum...</div>";
		Document doc = org.jsoup.Jsoup.parseBodyFragment(html);
		Element body = doc.body();
		System.out.println("body: " + body);
*/


		/* Load a Document from a URL */
		Document doc = org.jsoup.Jsoup.connect("http://www.example.com/").get();
		String title = doc.title();
		System.out.println("title: " + title);


		/* Load a Document from a File */
/*
		File input = new File("/input.html");
		Document doc = org.jsoup.Jsoup.parse(input, "UTF-8", "http://www.example.com/");
		System.out.println("doc: " + doc);
*/


		/* Sanitize untrusted HTML (to prevent XSS) */
/*
		String unsafe = "<a href='http://www.example.com/' onclick='stealCookies();'>Link</a>";
		String safe = org.jsoup.Jsoup.clean(unsafe, Whitelist.none());
		System.out.println("safe: " + safe);
*/
	}
}
