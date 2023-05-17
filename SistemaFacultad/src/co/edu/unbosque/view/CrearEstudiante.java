package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JCalendar;

public class CrearEstudiante extends JPanel {

	private JLabel imgfondo;
	private JPanel fondo, campos, mcalend;
	private JCalendar calendario;
	private JTextField nombre, apellido, correo, documento, fecha;
	private JComboBox<String> programa, jornada, genero, nacional;
	private JButton calendar, volver, agregar;
	private JLabel indnombre, indapellido, indcorreo, inddocumento, indprograma, indjornada, indgenero, indfecha, indlugar;
	private Font franklin;

	public CrearEstudiante() {

		try {

			franklin = Font.createFont(Font.TRUETYPE_FONT,
					new File("src/Assets/Fonts/Franklin Gothic Demi Cond Regular.ttf"));

		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}

		setSize(750, 480);
		setLayout(null);

		JPanel fondo = new JPanel();
		fondo.setLayout(null);
		fondo.setSize(750, 480);

		JLabel imgfondo = new JLabel();
		imgfondo.setSize(734, 442);
		ImageIcon fondo2 = new ImageIcon("src/Assets/RegistroEstudiante.jpg");
		imgfondo.setIcon(new ImageIcon(fondo2.getImage().getScaledInstance(734, 442, Image.SCALE_SMOOTH)));

		campos = new JPanel();
		campos.setLayout(null);
		campos.setSize(750, 480);
		campos.setOpaque(false);

		nombre = new JTextField();
		nombre.setBounds(370, 128, 140, 25);
		nombre.setForeground(Color.WHITE);
		MatteBorder borde=BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		nombre.setBorder(borde);
		nombre.setOpaque(false);

		apellido = new JTextField();
		apellido.setBounds(548, 128, 140, 25);
		apellido.setForeground(Color.WHITE);
		apellido.setBorder(borde);
		apellido.setOpaque(false);

		correo = new JTextField();
		correo.setBounds(370, 188, 140, 25);
		correo.setForeground(Color.WHITE);
		correo.setBorder(borde);
		correo.setOpaque(false);

		documento = new JTextField();
		documento.setBounds(548, 188, 140, 25);
		documento.setForeground(Color.WHITE);
		documento.setBorder(borde);
		documento.setOpaque(false);

		String[] programas = { "Seleccionar", "Bioingeniería", "Ingeniería Ambiental", "Ingeniería Electrónica",
				"Ingeniería industrial", "Ingeniería en sistemas" };

		programa = new JComboBox(programas);
		programa.setBounds(370, 248, 140, 25);

		fecha = new JTextField();
		fecha.setBounds(545, 248, 140, 25);
		fecha.setForeground(Color.WHITE);
		fecha.setBorder(borde);
		fecha.setOpaque(false);
		fecha.setEditable(false);

		String[] generos = { "Seleccionar", "Masculino", "Femenino" };
		
		genero = new JComboBox(generos);
		genero.setBounds(370, 308, 140, 25);

		String[] jornadas = { "Seleccionar", "Diurno", "Nocturno" };

		jornada = new JComboBox(jornadas);
		jornada.setBounds(548, 308, 140, 25);

		String[] opciones = {"Nacional", "Extranjero" };
		
		nacional = new JComboBox(opciones);
		nacional.setBounds(370, 368, 140, 25);


		calendar = new JButton();
		calendar.setBounds(546, 248, 140, 25);
		ImageIcon logocalendar = new ImageIcon("src/Assets/logocalendar.png");
		calendar.setIcon(new ImageIcon(logocalendar.getImage().getScaledInstance(140, 25, Image.SCALE_SMOOTH)));
		calendar.setOpaque(false);
		calendar.setBorderPainted(false);
		calendar.setContentAreaFilled(false);

		volver = new JButton();
		volver.setBounds(15, 15, 100, 25);
		ImageIcon logovolver = new ImageIcon("src/Assets/VolverC.png");
		volver.setIcon(new ImageIcon(logovolver.getImage().getScaledInstance(100, 25, Image.SCALE_SMOOTH)));
		volver.setOpaque(false);
		volver.setBorderPainted(false);
		volver.setContentAreaFilled(false);

		agregar = new JButton();
		agregar.setBounds(562, 364, 115, 35);
		ImageIcon logoagregar = new ImageIcon("src/Assets/AgregarC.png");
		agregar.setIcon(new ImageIcon(logoagregar.getImage().getScaledInstance(115, 35, Image.SCALE_SMOOTH)));
		agregar.setOpaque(false);
		agregar.setBorderPainted(false);
		agregar.setContentAreaFilled(false);

		calendario = new JCalendar();
		calendario.setPreferredSize(new Dimension(256, 152));
		calendario.setBackground(new Color(103, 216, 198));

		mcalend = new JPanel();
		mcalend.setBounds(420, 244, 266, 182);
		mcalend.setBackground(new Color(103, 216, 198));
		mcalend.setVisible(false);
		
		indnombre=new JLabel("Nombre Completo");
		indnombre.setFont(franklin);
		indnombre.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indnombre.setBounds(370, 92, 140, 50);
		indnombre.setForeground(Color.WHITE);
		
		indapellido=new JLabel("Apellido Completo");
		indapellido.setFont(franklin);
		indapellido.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indapellido.setBounds(546, 92, 140, 50);
		indapellido.setForeground(Color.WHITE);
		
		indcorreo = new JLabel("Correo electrónico");
		indcorreo.setFont(franklin);
		indcorreo.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indcorreo.setBounds(370, 152, 140, 50);
		indcorreo.setForeground(Color.WHITE);
		
		inddocumento = new JLabel("Documento de identidad");
		inddocumento.setFont(franklin);
		inddocumento.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		inddocumento.setBounds(546, 152, 140, 50);
		inddocumento.setForeground(Color.WHITE);
		
		indprograma = new JLabel("Programa de estudio");
		indprograma.setFont(franklin);
		indprograma.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indprograma.setBounds(370, 212, 140, 50);
		indprograma.setForeground(Color.WHITE);
		
		indfecha = new JLabel("Fecha de nacimiento");
		indfecha.setFont(franklin);
		indfecha.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indfecha.setBounds(546, 212, 140, 50);
		indfecha.setForeground(Color.WHITE);
		
		indgenero = new JLabel("Género");
		indgenero.setFont(franklin);
		indgenero.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indgenero.setBounds(370, 272, 140, 50);
		indgenero.setForeground(Color.WHITE);

		indjornada = new JLabel("Jornada");
		indjornada.setFont(franklin);
		indjornada.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indjornada.setBounds(546, 272, 140, 50);
		indjornada.setForeground(Color.WHITE);
		
		indlugar = new JLabel("Nacional / Extranjero");
		indlugar.setFont(franklin);
		indlugar.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indlugar.setBounds(370, 332, 140, 50);
		indlugar.setForeground(Color.WHITE);
		

		mcalend.add(calendario);
		add(mcalend);
		add(calendar);
		add(volver);
		add(agregar);
		campos.add(indnombre);
		campos.add(nombre);
		campos.add(indapellido);
		campos.add(apellido);
		campos.add(indcorreo);
		campos.add(correo);
		campos.add(inddocumento);
		campos.add(documento);
		campos.add(indprograma);
		campos.add(programa);
		campos.add(indfecha);
		campos.add(fecha);
		campos.add(indgenero);
		campos.add(genero);
		campos.add(indjornada);
		campos.add(jornada);
		campos.add(indlugar);
		campos.add(nacional);

		add(campos);

		fondo.add(imgfondo);
		add(fondo);

		setVisible(true);
	}

}
