package co.edu.unbosque.view;


import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainWindow extends JFrame{

	private JPanel background;
	private JLabel logo, botones;
	private JButton students,activation, admin;
	
	
	public MainWindow() {

		setSize(750, 480);
		setTitle("Sistema Facultad de Ingenieria");
		setLayout(null);
		setResizable(false);		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		logo = new JLabel(new ImageIcon("src/Assets/ueb.png"));
		logo.setBounds(0,0,750,480);
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
		admin.setBounds(447,300,219,47);
		admin.setFocusable(false);
		admin.setBorderPainted(false);
		admin.setContentAreaFilled(false);
		admin.setIcon(new ImageIcon("src/Assets/boton3.png"));
		add(admin);
		
		
				
		
		botones = new JLabel(new ImageIcon("src/Assets/panelbotones.png"));
		botones.setBounds(0,0,750,480);
		add(botones);
		
		background = new JPanel();
		background.setBounds(0,0,750,480);
		background.setBackground(new Color(103, 216, 198));
		add(background);
		
		
		
		
		setVisible(true);
	}
	

	

	
}
