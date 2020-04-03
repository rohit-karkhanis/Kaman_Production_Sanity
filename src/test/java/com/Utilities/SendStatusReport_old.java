package com.Utilities;

import com.sun.mail.smtp.SMTPTransport;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Date;
import java.util.Properties;
import java.util.Properties;
import javax.activation.*;
import javax.mail.*;

public class SendStatusReport_old {

	/*	// for example, smtp.mailgun.org
    private static final String SMTP_SERVER = "smtp.office365.com";
    private static final String USERNAME = "rohit.karkhanis@logixal.com";
    private static final String PASSWORD = "Rk@785634";

    private static final String EMAIL_FROM = "Rohit Karkhanis <rohit.karkhanis@logixal.com>";
    private static final String EMAIL_TO = "";
    private static final String EMAIL_TO_CC = "";

    private static final String EMAIL_SUBJECT = "Test Send Email via SMTP";
    private static final String EMAIL_TEXT = "Hello Java Mail \n ABC123";

    public static void main(String[] args) {

        Properties prop = System.getProperties();
        prop.put("mail.smtp.host", SMTP_SERVER); //optional, defined in SMTPTransport
        //prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.port", "587"); // default port 25
        prop.put ( "mail.smtp.starttls.enable", "true" );

        Session session = Session.getInstance(prop, null);
        Message msg = new MimeMessage(session);

        try {

			// from
            msg.setFrom(new InternetAddress(EMAIL_FROM));

			// to 
            msg.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(EMAIL_TO, false));

			// cc
            msg.setRecipients(Message.RecipientType.CC,
                    InternetAddress.parse(EMAIL_TO_CC, false));

			// subject
            msg.setSubject(EMAIL_SUBJECT);

			// content 
            msg.setText(EMAIL_TEXT);

            msg.setSentDate(new Date());

			// Get SMTPTransport
            SMTPTransport t = (SMTPTransport) session.getTransport("smtp");

			// connect
            t.connect(SMTP_SERVER, USERNAME, PASSWORD);

			// send
            t.sendMessage(msg, msg.getAllRecipients());

            System.out.println("Response: " + t.getLastServerResponse());

            t.close();

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }
}*/



	public static void main(String[] args) {

		final String username = "jenkins@logixal.com";
		final String password = "Qwert@123";
		//String to ="rohit.karkhanis@logixal.com,rohit.karkhanis1989@gmail.com,rskarkhaniss@gmail.com";
		String to ="Carlos.Camacho@kaman.com,darwin.pierre-louis@kaman.com,Howard.Blumenthal@kaman.com,kaman.golive@logixal.com,rohit.karkhanis@logixal.com,ameya.sawalkar@logixal.com";
		// BodyPart messageBodyPart;

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", true);
		props.put("mail.smtp.host", "smtp.office365.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jenkins@logixal.com"));
			/* message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("rohit.karkhanis@logixal.com","rohit.karkhanis1989@gmail.com","rskarkhaniss@gmail.com"));*/
			InternetAddress[] parse = InternetAddress.parse(to , true);
			message.setRecipients(javax.mail.Message.RecipientType.TO,  parse);
			//message.setSubject("Prod Sanity Build # 8 : Sanity Automation Test Status on Production - Successful!");
			  message.setSubject("Build # 25 : Sanity Automation Test Status on ECTEST - Successful!");

			message.setText("<h1> test email </h1>");
			//message.setContent("sdfsdsdd", "text/html; charset=utf-8");
			message.saveChanges();

			MimeBodyPart messageBodyPart = new MimeBodyPart();

			Multipart multipart = new MimeMultipart();

			messageBodyPart = new MimeBodyPart();
			String file = "D:\\ROhit\\Rohit\\Automation\\Demo\\KamanLogixalQA\\test-output\\TestSummary_Report.html";
			String fileName = "TestSummary_Report.html";
			DataSource source = new FileDataSource(file);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			//messageBodyPart.setText("sdsadsasdjbhsadbhasdbsa");
			multipart.addBodyPart(messageBodyPart);


			message.setContent(multipart);

			System.out.println("Sending");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}