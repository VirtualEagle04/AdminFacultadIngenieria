package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

/**
 * Clase que hereda de JPanel diseñada para la activacion del estudiante
 * mediante la confirmacion de un codigo y usuario
 * 
 * @param Franklin   Font fuente establecidad para ser cargada en los JLabel su
 *                   asignacion en los JLabel
 * @param user       JTextField para el ingreso del usuario del estudiante
 * @param code       JTextField para el ingreso del codigo de activacion del
 *                   estudiante
 * @param ind_user   JLabel para indicar donde ingresar el nombre del usuario
 *                   del estudiante
 * @param ind_code   JLabel para indicar donde ingresar el codigo de activacion
 *                   del estudiante
 * @param background JLabel que contiene el fondo del espacio de registro del
 *                   estudiante
 * @param shadow     JLabel que actua como sombra del fondo que contiene el
 *                   espacio de registro del estudiante
 * 
 * @param activate   JButton que permite gestionar la activacion de la cuenta
 *                   del estudiante
 * @param back       boton que permite volver al inicio del programa
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */

public class ActivationPanel extends JPanel {

	private Font Franklin;
	private JTextField user, code;
	private JLabel ind_user, ind_code, background1, shadow;
	private JButton activate, back;

	public ActivationPanel() {

		try {

			Franklin = Font.createFont(Font.TRUETYPE_FONT,
					new File("src/Assets/Fonts/Franklin Gothic Demi Cond Regular.ttf"));

		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBounds(0, 0, 750, 480);
		setBackground(new Color(103, 216, 198));
		setLayout(null);

		background1 = new JLabel(new ImageIcon("src/Assets/fondo registro.png"));
		background1.setBounds(194, 70, 350, 300);
		background1.setBackground(new Color(55, 73, 97));
		background1.setLayout(null);
		add(background1);

		shadow = new JLabel(new ImageIcon("src/Assets/sombra.png"));
		shadow.setBounds(184, 80, 350, 300);
		shadow.setBackground(new Color(55, 73, 97));
		shadow.setLayout(null);
		add(shadow);

		ind_user = new JLabel("Ingrese el Usuario");
		ind_user.setFont(Franklin);
		ind_user.setFont(ind_user.getFont().deriveFont(Font.PLAIN, 20));
		ind_user.setForeground(Color.WHITE);
		ind_user.setBounds(50, 30, 150, 30);
		background1.add(ind_user);

		user = new JTextField();
		user.setBounds(50, 75, 250, 30);
		MatteBorder border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		user.setBorder(border);
		user.setOpaque(false);
		user.setForeground(Color.WHITE);
		user.setFont(new Font("Consolas", Font.PLAIN, 20));
		background1.add(user);

		ind_code = new JLabel("Ingrese el Codigo");
		ind_code.setFont(Franklin);
		ind_code.setFont(ind_code.getFont().deriveFont(Font.PLAIN, 20));
		ind_code.setForeground(Color.WHITE);
		ind_code.setBounds(50, 120, 150, 30);
		background1.add(ind_code);

		code = new JTextField();
		code.setBounds(50, 165, 250, 30);
		MatteBorder border2 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		code.setBorder(border2);
		code.setOpaque(false);
		code.setForeground(Color.WHITE);
		code.setEditable(true);
		code.setFont(new Font("Consolas", Font.PLAIN, 20));
		background1.add(code);

		activate = new JButton();
		activate.setBounds(100, 230, 150, 30);
		activate.setFocusable(false);
		activate.setBorderPainted(false);
		activate.setContentAreaFilled(false);
		activate.setIcon(new ImageIcon("src/Assets/ActivarC.png"));
		background1.add(activate);

		back = new JButton();
		back.setBounds(10, 10, 150, 30);
		back.setFocusable(false);
		back.setBorderPainted(false);
		back.setContentAreaFilled(false);
		back.setIcon(new ImageIcon("src/Assets/VolverO.png"));
		add(back);

		setVisible(true);

	}

	public Font getFranklin() {
		return Franklin;
	}

	public void setFranklin(Font franklin) {
		Franklin = franklin;
	}

	public JTextField getUser() {
		return user;
	}

	public void setUser(JTextField user) {
		this.user = user;
	}

	public JTextField getCode() {
		return code;
	}

	public void setCode(JTextField code) {
		this.code = code;
	}

	public JLabel getInd_user() {
		return ind_user;
	}

	public void setInd_user(JLabel ind_user) {
		this.ind_user = ind_user;
	}

	public JLabel getInd_code() {
		return ind_code;
	}

	public void setInd_code(JLabel ind_code) {
		this.ind_code = ind_code;
	}

	public JLabel getBackground1() {
		return background1;
	}

	public void setBackground1(JLabel background1) {
		this.background1 = background1;
	}

	public JLabel getShadow() {
		return shadow;
	}

	public void setShadow(JLabel shadow) {
		this.shadow = shadow;
	}

	public JButton getActivate() {
		return activate;
	}

	public void setActivate(JButton activate) {
		this.activate = activate;
	}

	public JButton getBack() {
		return back;
	}

	public void setBack(JButton back) {
		this.back = back;
	}

}
