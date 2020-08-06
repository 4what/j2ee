package $java;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.Properties;

public class JavaMail {
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
	 * @throws MalformedURLException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public static void send(String host, int port, String username, String password, String from, String to, String subject, String content, String[] attachments) throws MalformedURLException, MessagingException, UnsupportedEncodingException {
		// create some properties and get the default Session
		Properties props = System.getProperties();

		//props.put("mail.debug", "true");

		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.smtp.auth", "true");

		// Get a Session object
		Session session = Session.getDefaultInstance(props, new Authenticator() {
			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		//session.setDebug(debug);

		// create a message
		MimeMessage msg = new MimeMessage(session);

		// 发件人
		msg.setFrom(new InternetAddress(from));

		// 收件人（,）
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
		// 抄送（,）
		//msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
		// 密送（,）
		//msg.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));

		// 主题
		msg.setSubject(subject);

		// create and fill the first message part
		MimeBodyPart mbp = new MimeBodyPart();

		// 正文
		//mbp.setText(content);
		// HTML
		mbp.setContent(content, "text/html; charset=UTF-8");

		// TODO: inline

		// create the second message part
		//MimeBodyPart mbp_file = new MimeBodyPart();

		// attach the file to the message
		//mbp_file.attachFile(filename);

		// create the Multipart and add its parts to it
		Multipart mp = new MimeMultipart();

		mp.addBodyPart(mbp);
		//mp.addBodyPart(mbp_file);

		// 附件
		/*
		 * Use the following approach instead of the above line if
		 * you want to control the MIME type of the attached file.
		 * Normally you should never need to do this.
		 *
		 */
		for (String item : attachments) {
			MimeBodyPart mbp_file = new MimeBodyPart();

			//mbp_file.setDisposition(MimeBodyPart.ATTACHMENT);

			DataHandler dataHandler;

			String filename;

			if (item.matches("^(ftp|https?)://.*?")) {
				URL url = new URL(item);

				dataHandler = new DataHandler(url);

				filename = url.getFile();
			} else {
				FileDataSource fds = new FileDataSource(item) {
					public String getContentType() {
						return "application/octet-stream";
					}
				};

				dataHandler = new DataHandler(fds);

				filename = fds.getName();
			}

			mbp_file.setDataHandler(dataHandler);

			//mbp_file.setDescription("");
			mbp_file.setFileName(MimeUtility.encodeText(filename));

			mp.addBodyPart(mbp_file);
		}

		// add the Multipart to the message
		msg.setContent(mp);

		// set the Date: header
		//msg.setSentDate(new Date());

		// send the message
		Transport.send(msg);
	}


	public static void main(String[] args) throws MalformedURLException, MessagingException, UnsupportedEncodingException {
		// SMTP
		String host = "localhost";
		int port = 25;

		String username = "root";
		String password = "root";

		String from = "root@example.com";
		String to = "user@localhost, other@localhost";

		String subject = "主题：" + new Date();
		String content = "正文：" + new Date();

		//String filename = "";
		String[] attachments = {
			//"X:\\*.*",
			//"X:\\*.*"
		};

		send(host, port, username, password, from, to, subject, content, attachments);
	}
}
