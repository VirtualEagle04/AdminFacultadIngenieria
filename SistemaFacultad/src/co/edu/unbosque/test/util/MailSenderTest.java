package co.edu.unbosque.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import co.edu.unbosque.util.MailSender;

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)
public class MailSenderTest {
	
	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;
	
	private static String emailFrom = MailSender.getEmailFrom();
	private static String passwordFrom = MailSender.getPasswordFrom();
	private static String emailTo = emailFrom;
	
	private static Properties prop = new Properties();
	private static Session session;
	private static MimeMessage mimeMessage;
	
	@BeforeAll
	public static void inicializacion() {
		System.out.println("Iniciando pruebas unitarias: AdminDAO");
	}
	
	@AfterAll
	public static void finalizacion() {
		System.out.print("\u001B[0m");
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: "+passed+"/2\n-Fallido: "+failed+"/2\n<----------------------------------->");
	}
	
	@Test
	@Order(1)
	public void createAuthEmailTest() {
		cont++;
		
		try {
			prop.put("mail.smtp.host", "smtp.gmail.com");
			prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			prop.setProperty("mail.smtp.starttls.enable", "true");
			prop.setProperty("mail.smtp.port", "587");
			prop.setProperty("mail.smtp.user", emailFrom );
			prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
			prop.setProperty("mail.smtp.auth", "true");
			session = Session.getDefaultInstance(prop);
			
			UUID uuid = UUID.randomUUID();
			String codigo = uuid.toString();
			codigo = codigo.replace("-", "");
			codigo = codigo.substring(0, 10);
			String content = "Bienvenido a la Facultad de Ingenieria, por favor ingresa "
					+ "el codigo a continuacion en el apartado 'Activar Cuenta' en el "
					+ "Administrador de Datos de la Facultad de Ingenieria.<br><br>"
					+ "CODIGO DE ACTIVACION: <br>"+codigo+"<br>"
					+ "Por favor no responda a este correo, se envía desde una dirección que no se supervisa.";
			
			String subject = "Activacion de Usuario | Administrador de Datos de la Facultad de Ingenieria";
			mimeMessage = new MimeMessage(session);
			mimeMessage.setFrom(new InternetAddress(emailFrom));
			mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
			mimeMessage.setSubject(subject);
			
			MimeBodyPart mimeBodyPart = new MimeBodyPart();
			mimeBodyPart.setContent(content, "text/html; charset=utf-8");
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(mimeBodyPart);
			
			mimeMessage.setContent(multipart);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (MessagingException e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}
		System.out.print("\u001B[32m");
		System.out.println("Test "+cont+" pasado.");
	
	}
	
	@Test
	@Order(2)
	public void sendEmailTest() {
		cont++;
		
		try {
			Transport transport = session.getTransport("smtp");
			transport.connect(emailFrom, passwordFrom);
			transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
			transport.close();
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (MessagingException e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}

	}
	
}
