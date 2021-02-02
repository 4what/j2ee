package $java.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

@Component
public class JavaMailSender {
	@Autowired
	private org.springframework.mail.javamail.JavaMailSender javaMailSender;

	/**
	 * @param from
	 * @param to
	 * @param subject
	 * @param content
	 * @param attachments
	 * @throws MalformedURLException
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void send(String from, String[] to, String subject, String content, String[] attachments) throws MalformedURLException, MessagingException, UnsupportedEncodingException {
		/* SimpleMailMessage */
		//SimpleMailMessage msg = new SimpleMailMessage();

		/* MimeMessagePreparator */
/*
		MimeMessagePreparator msg = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
			}
		};
*/

		/* MimeMessageHelper */
		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		/* 发件人 */
		helper.setFrom(from);

		/* 收件人 */
		helper.setTo(to);
		/* 抄送 */
		//helper.setCc(cc);
		/* 密送 */
		//helper.setBcc(bcc);

		/* 主题 */
		helper.setSubject(subject);

		/* 正文 */
		helper.setText(content, true);

		/* inline */
/*
		helper.setText("<img src=\"cid:cid\" alt=\"\" />", true);
		helper.addInline(
			"cid",
			new File("")
			//new UrlResource("")
		);
*/

		/* 附件 */
		for (String item : attachments) {
			if (item.matches("^(ftp|https?)://.*?")) {
				UrlResource url = new UrlResource(item);
				helper.addAttachment(url.getFilename(), url);
			} else {
				File file = new File(item);
				helper.addAttachment(MimeUtility.encodeText(file.getName(), "UTF-8", "Q"), file);
			}
		}

		javaMailSender.send(msg);
	}
}
