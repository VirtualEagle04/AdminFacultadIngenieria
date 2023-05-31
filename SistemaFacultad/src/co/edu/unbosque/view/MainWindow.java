package co.edu.unbosque.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Clase que hereda de JFrame en la cual se muestra el inicio del programa y el
 * acceso a las demas funcionalidades del mismo
 * 
 * @param background      JPanel que permite asignar la coloracion base del
 *                        programa
 * @param logo            JLabel que permite cargar la imagen con el logo de la
 *                        universidad
 * @param botones         JLabel que permite cargar la imagen con el fondo
 *                        asignado al apartado de los botones que correspondel a
 *                        las demas funciones del programa
 * @param students        JButton que permite acceder al panel de registro de
 *                        estudiantes
 * @param activation      JButton que permite acceder al panel de activacion de
 *                        estudiantes
 * @param admin           JButton que permite acceder al panel de inicio de
 *                        sesion del administrador
 * 
 * @param creationpanel   Panel de la clase CrearEstudiante que muestra las
 *                        funciones de creacion de un estudiante
 * @param activationpanel Panel de la clase ActivationPanel que muestra las
 *                        funciones de activacion de un estudiante
 * @param adminpanel      Panel de la clase AdminAccesPanel que muestra las
 *                        funciones del acceso del administrador
 * @param admincontrol    Panel de la clase AdminControl que muestra las
 *                        funcones de control que tiene el administrador
 * @param excontrol       Objeto de la clase ExceptionControl encargada del
 *                        control de las excepciones del programa
 * 
 * 
 * 
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 *
 */

public class MainWindow extends JFrame {

	private JPanel background;
	private JLabel logo, botones;
	private JButton students, activation, admin;
	private CrearEstudiante creationpanel;
	private ActivationPanel activationpanel;
	private AdminAccesPanel adminpanel;
	private AdminControl admincontrol;
	private ExceptionControl excontrol;

	public MainWindow() {

		setSize(750, 480);
		setTitle("Administrador de Datos | Facultad de Ingeniería");
		ImageIcon icon = new ImageIcon("src/Assets/UEBlogo.png");
		setIconImage(icon.getImage());
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		creationpanel = new CrearEstudiante();
		activationpanel = new ActivationPanel();
		adminpanel = new AdminAccesPanel();
		admincontrol = new AdminControl();
		excontrol = new ExceptionControl();

		add(adminpanel).setVisible(false);
		add(activationpanel).setVisible(false);
		add(admincontrol).setVisible(false);
		add(creationpanel).setVisible(false);

		logo = new JLabel(new ImageIcon("src/Assets/ueb.png"));
		logo.setBounds(0, 0, 750, 480);
		add(logo);

		students = new JButton();
		students.setBounds(447, 100, 219, 47);
		students.setFocusable(false);
		students.setBorderPainted(false);
		students.setContentAreaFilled(false);
		students.setIcon(new ImageIcon("src/Assets/boton1.png"));
		add(students);

		activation = new JButton();
		activation.setBounds(447, 200, 219, 47);
		activation.setFocusable(false);
		activation.setBorderPainted(false);
		activation.setContentAreaFilled(false);
		activation.setIcon(new ImageIcon("src/Assets/boton2.png"));
		add(activation);

		admin = new JButton();
		admin.setBounds(447, 300, 219, 47);
		admin.setFocusable(false);
		admin.setBorderPainted(false);
		admin.setContentAreaFilled(false);
		admin.setIcon(new ImageIcon("src/Assets/boton3.png"));
		add(admin);

		botones = new JLabel(new ImageIcon("src/Assets/panelbotones.png"));
		botones.setBounds(0, 0, 750, 480);
		add(botones);

		background = new JPanel();
		background.setBounds(0, 0, 750, 480);
		background.setBackground(new Color(103, 216, 198));
		add(background);

		setVisible(true);
	}

	public ExceptionControl getExcontrol() {
		return excontrol;
	}

	public void setExcontrol(ExceptionControl excontrol) {
		this.excontrol = excontrol;
	}

	public void setAdmincontrol(AdminControl admincontrol) {
		this.admincontrol = admincontrol;
	}

	public AdminControl getAdmincontrol() {
		return admincontrol;
	}

	public void setAdmincontroll(AdminControl admincontrol) {
		this.admincontrol = admincontrol;
	}

	public ActivationPanel getActivationpanel() {
		return activationpanel;
	}

	public void setActivationpanel(ActivationPanel activationpanel) {
		this.activationpanel = activationpanel;
	}

	public AdminAccesPanel getAdminpanel() {
		return adminpanel;
	}

	public void setAdminpanel(AdminAccesPanel adminpanel) {
		this.adminpanel = adminpanel;
	}

	public void setBackground(JPanel background) {
		this.background = background;
	}

	public JLabel getLogo() {
		return logo;
	}

	public void setLogo(JLabel logo) {
		this.logo = logo;
	}

	public JLabel getBotones() {
		return botones;
	}

	public void setBotones(JLabel botones) {
		this.botones = botones;
	}

	public JButton getStudents() {
		return students;
	}

	public void setStudents(JButton students) {
		this.students = students;
	}

	public JButton getActivation() {
		return activation;
	}

	public void setActivation(JButton activation) {
		this.activation = activation;
	}

	public JButton getAdmin() {
		return admin;
	}

	public void setAdmin(JButton admin) {
		this.admin = admin;
	}

	public CrearEstudiante getCreationpanel() {
		return creationpanel;
	}

	public void setCreationpanel(CrearEstudiante creationpanel) {
		this.creationpanel = creationpanel;
	}

}
