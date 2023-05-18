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

public class AdminAccesPanel extends JPanel {

	private Font Franklin;
	private JTextField user2, password;
	private JLabel ind_user2, ind_pass, background2, shadow2;
	private JButton join, back2;

	public AdminAccesPanel() {

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

		background2 = new JLabel(new ImageIcon("src/Assets/fondo registro.png"));
		background2.setBounds(194, 70, 350, 300);
		background2.setBackground(new Color(55, 73, 97));
		background2.setLayout(null);
		add(background2);

		shadow2 = new JLabel(new ImageIcon("src/Assets/sombra.png"));
		shadow2.setBounds(184, 80, 350, 300);
		shadow2.setBackground(new Color(55, 73, 97));
		shadow2.setLayout(null);
		add(shadow2);

		ind_user2 = new JLabel("Ingrese el Usuario");
		ind_user2.setFont(Franklin);
		ind_user2.setFont(ind_user2.getFont().deriveFont(Font.PLAIN, 20));
		ind_user2.setForeground(Color.WHITE);
		ind_user2.setBounds(50, 30, 150, 30);
		background2.add(ind_user2);

		user2 = new JTextField();
		user2.setBounds(50, 75, 250, 30);
		MatteBorder border = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		user2.setBorder(border);
		user2.setOpaque(false);
		user2.setForeground(Color.WHITE);
		user2.setFont(new Font("Consolas", Font.PLAIN, 20));
		background2.add(user2);

		ind_pass = new JLabel("Ingrese la Contrase�a");
		ind_pass.setFont(Franklin);
		ind_pass.setFont(ind_pass.getFont().deriveFont(Font.PLAIN, 20));
		ind_pass.setForeground(Color.WHITE);
		ind_pass.setBounds(50, 120, 170, 30);
		background2.add(ind_pass);

		password = new JTextField();
		password.setBounds(50, 165, 250, 30);
		MatteBorder border2 = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		password.setBorder(border2);
		password.setOpaque(false);
		password.setForeground(Color.WHITE);
		password.setFont(new Font("Consolas", Font.PLAIN, 20));
		background2.add(password);

		join = new JButton();
		join.setBounds(100, 230, 150, 30);
		join.setFocusable(false);
		join.setBorderPainted(false);
		join.setContentAreaFilled(false);
		join.setIcon(new ImageIcon("src/Assets/IngresarC.png"));
		background2.add(join);

		back2 = new JButton();
		back2.setBounds(10, 10, 150, 30);
		back2.setFocusable(false);
		back2.setBorderPainted(false);
		back2.setContentAreaFilled(false);
		back2.setIcon(new ImageIcon("src/Assets/VolverO.png"));
		add(back2);

		setVisible(true);

	}

	public Font getFranklin() {
		return Franklin;
	}

	public void setFranklin(Font franklin) {
		Franklin = franklin;
	}

	public JTextField getUser2() {
		return user2;
	}

	public void setUser2(JTextField user2) {
		this.user2 = user2;
	}

	public JTextField getPassword() {
		return password;
	}

	public void setPassword(JTextField password) {
		this.password = password;
	}

	public JLabel getInd_user2() {
		return ind_user2;
	}

	public void setInd_user2(JLabel ind_user2) {
		this.ind_user2 = ind_user2;
	}

	public JLabel getInd_pass() {
		return ind_pass;
	}

	public void setInd_pass(JLabel ind_pass) {
		this.ind_pass = ind_pass;
	}

	public JLabel getBackground2() {
		return background2;
	}

	public void setBackground2(JLabel background2) {
		this.background2 = background2;
	}

	public JLabel getShadow2() {
		return shadow2;
	}

	public void setShadow2(JLabel shadow2) {
		this.shadow2 = shadow2;
	}

	public JButton getJoin() {
		return join;
	}

	public void setJoin(JButton join) {
		this.join = join;
	}

	public JButton getBack2() {
		return back2;
	}

	public void setBack2(JButton back2) {
		this.back2 = back2;
	}

}