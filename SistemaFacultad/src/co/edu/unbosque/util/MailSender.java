package co.edu.unbosque.util;

import java.util.Properties;
import java.util.UUID;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Clase encargada de las pruebas unitarias del MailSender
 * 
 * @param emailFrom    String que almacena el correo desde donde se envia el
 *                     correo
 * @param passwordFrom String que almacena la contraseña del correo desde donde
 *                     se envia el correo
 * @param prop         Objeto de la clase {@link Properties}
 * @param session      Objeto de la clase {@link Session}
 * @param mimeMessage  Objeto de la clase {@link MimeMessage}
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class MailSender {

	private static String emailFrom = "automatonJavaSender@gmail.com";
	private static String passwordFrom = "lmnxukpiduqypaur";

	private static Properties prop = new Properties();
	private static Session session;
	private static MimeMessage mimeMessage;

	/**
	 * Metodo encargado de el envio de el correo electronico
	 * 
	 * @return booleano que representa si se realiza o no correctamente el envio del
	 *         correo
	 * @throws MessagingException excepcion que salta en caso de que no se haya
	 *                            podido enviar el correo
	 */
	public static boolean sendEmail() throws MessagingException {
		Transport transport = session.getTransport("smtp");
		transport.connect(emailFrom, passwordFrom);
		transport.sendMessage(mimeMessage, mimeMessage.getRecipients(Message.RecipientType.TO));
		transport.close();

		return true;
	}

	// CREACION DE CODIGO, ASUNTO Y EL DESTINATARIO
	/**
	 * Metodo encargado de crear el mensaje que ira en el correo y establecer
	 * destinatarios del correo
	 * 
	 * @param emailTo String que almacena el correo electronico del destinatario
	 * @return String que almacena el uuid
	 * @throws AddressException   excepcion que salta en caso de que el correo sea
	 *                            invalido
	 * @throws MessagingException excepcion que salta en caso de que no se haya
	 *                            podido enviar el correo
	 */
	public static String createAuthEmail(String emailTo) throws AddressException, MessagingException {
		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		prop.setProperty("mail.smtp.starttls.enable", "true");
		prop.setProperty("mail.smtp.port", "587");
		prop.setProperty("mail.smtp.user", emailFrom);
		prop.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
		prop.setProperty("mail.smtp.auth", "true");
		session = Session.getDefaultInstance(prop);

		UUID uuid = UUID.randomUUID();
		String codigo = uuid.toString();
		codigo = codigo.replace("-", "");
		codigo = codigo.substring(0, 10);
		String content = "Bienvenido a la Facultad de Ingenieria, por favor ingresa "
				+ "el codigo a continuacion en el apartado 'Activar Cuenta' en el "
				+ "Administrador de Datos de la Facultad de Ingenieria.<br><br>" + "CODIGO DE ACTIVACION: <br>" + codigo
				+ "<br>" + "Por favor no responda a este correo, se envía desde una dirección que no se supervisa.";

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

		return codigo;
	}

	public static String getEmailFrom() {
		return emailFrom;
	}

	public static void setEmailFrom(String emailFrom) {
		MailSender.emailFrom = emailFrom;
	}

	public static String getPasswordFrom() {
		return passwordFrom;
	}

	public static void setPasswordFrom(String passwordFrom) {
		MailSender.passwordFrom = passwordFrom;
	}

	public static Properties getProp() {
		return prop;
	}

	public static void setProp(Properties prop) {
		MailSender.prop = prop;
	}

	public static Session getSession() {
		return session;
	}

	public static void setSession(Session session) {
		MailSender.session = session;
	}

	public static MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	public static void setMimeMessage(MimeMessage mimeMessage) {
		MailSender.mimeMessage = mimeMessage;
	}

}
