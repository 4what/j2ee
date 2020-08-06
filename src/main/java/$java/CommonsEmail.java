package $java;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

public class CommonsEmail {
	private static String charset = "UTF-8";

	/**
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param attachments
	 * @throws EmailException
	 * @throws MalformedURLException
	 * @throws UnsupportedEncodingException
	 */
	public static void send(String host, int port, String username, String password, String from, Collection<InternetAddress> to, String subject, String content, String[] attachments) throws EmailException, MalformedURLException, UnsupportedEncodingException {
		// Create the email message
		HtmlEmail email = new HtmlEmail();

		//email.setDebug(true);

		email.setHostName(host);
		email.setSmtpPort(port);
		email.setAuthenticator(new DefaultAuthenticator(username, password));
		email.setCharset(charset);

		// 发件人
		email.setFrom(from, "");

		// 收件人
		email.setTo(to);
		// 抄送
		//email.setCc(cc);
		// 密送
		//email.setBcc(bcc);

		// 主题
		email.setSubject(subject);

		// 正文
		//email.setMsg(content);
		//email.setContent(content, "text/html; charset=" + charset);

		// set the html message
		email.setHtmlMsg(content);
		// set the alternative message
		//email.setTextMsg("Your email client does not support HTML messages");

		// inline
		// embed the image and get the content id
/*
		String cid = email.embed(
			new File("")
			//"http://", "name"
		);
		email.setHtmlMsg("<img src=\"cid:" + cid + "\" alt=\"\" />");
*/

		// 附件
		// Create the attachment
		EmailAttachment attachment = new EmailAttachment();

		//attachment.setDisposition(EmailAttachment.ATTACHMENT);

		for (String item : attachments) {
			String filename;

			String path = null;
			URL url = null;

			if (item.matches("^(ftp|https?)://.*?")) {
				url = new URL(item);

				filename = url.getFile();
			} else {
				path = item;

				filename = new File(path).getName();
			}

			attachment.setPath(path);
			attachment.setURL(url);

			//attachment.setDescription("");
			attachment.setName(MimeUtility.encodeText(filename));

			// add the attachment
			email.attach(attachment);
		}

		//email.setSentDate(new Date());

		// send the email
		email.send();
	}


	public static void main(String[] args) throws EmailException, MalformedURLException, UnsupportedEncodingException {
		// SMTP
		//192.168.238.144 (WAN: 61.145.113.31)
		String host = "localhost";
		int port = 25;

		//dev@service.pconline.com.cn
		String username = "root";
		//dev@#120510
		String password = "root";

		//support@pconline.com.cn
		String from = "root@example.com";

		Collection to = new ArrayList<InternetAddress>();
		to.add(new InternetAddress("user@localhost", ""));
		to.add(new InternetAddress("other@localhost", ""));

		String subject = "主题：" + new Date();
		String content = "正文：" + new Date();

		String[] attachments = {
			//"X:\\*.*",
			//"http://"
		};

		send(host, port, username, password, from, to, subject, content, attachments);
	}
}
