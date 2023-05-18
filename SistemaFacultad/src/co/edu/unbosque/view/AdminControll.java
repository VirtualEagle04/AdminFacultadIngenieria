package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

public class AdminControll extends JPanel {

	private JList<String> list_e;
	private JList<String> list_pdf;
	private DefaultListModel<String> modelo;
	private DefaultListModel<String> modelo2;
	private JPanel panel_list, panel_list2, panel_info, panel_pdfs;
	private JLabel ind_lista, ind1, ind2, ind3, ind4, ind5;
	private JTextArea area1;
	private JTextField filter;
	private JButton back3, ascendant, descending, delete, activate, actualpdf, generatepdf, generate;
	private JScrollPane barra1, barra2;
	private JComboBox<String> campotipo;
	private Font Franklin;

	public AdminControll() {

		try {

			Franklin = Font.createFont(Font.TRUETYPE_FONT,
					new File("src/Assets/Fonts/Franklin Gothic Demi Cond Regular.ttf"));

		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		setBounds(0, 0, 750, 480);
		setBackground(new Color(55, 73, 97));
		setLayout(null);

		back3 = new JButton();
		back3.setBounds(10, 10, 150, 30);
		back3.setFocusable(false);
		back3.setBorderPainted(false);
		back3.setContentAreaFilled(false);
		back3.setIcon(new ImageIcon("src/Assets/VolverC.png"));
		add(back3);

		panel_pdfs = new JPanel();
		panel_pdfs.setBounds(170, 30, 400, 390);
		panel_pdfs.setBackground(new Color(81, 114, 157));
		panel_pdfs.setLayout(null);
		add(panel_pdfs).setVisible(false);

		ind_lista = new JLabel("Seleccione Documento a Generar");
		ind_lista.setBounds(70, 5, 270, 30);
		ind_lista.setFont(Franklin);
		ind_lista.setFont(ind_lista.getFont().deriveFont(Font.PLAIN, 20));
		ind_lista.setForeground(Color.WHITE);
		panel_pdfs.add(ind_lista);

		ind5 = new JLabel("Generar");
		ind5.setFont(Franklin);
		ind5.setFont(ind5.getFont().deriveFont(Font.PLAIN, 17));
		ind5.setBounds(169, 340, 120, 30);
		ind5.setForeground(Color.WHITE);
		add(ind5);
		panel_pdfs.add(ind5);

		generate = new JButton();
		generate.setBounds(135, 340, 120, 30);
		generate.setIcon(new ImageIcon("src/Assets/boton4.png"));
		generate.setFocusable(false);
		generate.setBorderPainted(false);
		generate.setContentAreaFilled(false);
		panel_pdfs.add(generate);

		list_pdf = new JList<>();
		list_pdf.setBounds(20, 0, 320, 360);
		modelo2 = new DefaultListModel<>();
		list_pdf.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		barra2 = new JScrollPane(list_pdf);
		barra2.setBounds(0, 0, 330, 365);

		panel_list2 = new JPanel();
		panel_list2.setBounds(35, 45, 328, 280);
		panel_list2.setLayout(null);
		panel_list2.add(barra2);
		add(panel_list2);
		panel_pdfs.add(panel_list2);

		ind1 = new JLabel("Generar Actual");
		ind1.setFont(Franklin);
		ind1.setFont(ind1.getFont().deriveFont(Font.PLAIN, 17));
		ind1.setBounds(500, 340, 120, 30);
		ind1.setForeground(Color.WHITE);
		add(ind1);

		ind2 = new JLabel("Generar PDF");
		ind2.setFont(Franklin);
		ind2.setFont(ind2.getFont().deriveFont(Font.PLAIN, 17));
		ind2.setBounds(509, 385, 120, 30);
		ind2.setForeground(Color.WHITE);
		add(ind2);

		ind3 = new JLabel("Eliminar Estudiante");
		ind3.setFont(Franklin);
		ind3.setFont(ind3.getFont().deriveFont(Font.PLAIN, 15));
		ind3.setBounds(425, 250, 150, 30);
		ind3.setForeground(Color.WHITE);
		add(ind3);

		ind4 = new JLabel("Activar/Inactivar");
		ind4.setFont(Franklin);
		ind4.setFont(ind4.getFont().deriveFont(Font.PLAIN, 15));
		ind4.setBounds(570, 250, 150, 30);
		ind4.setForeground(Color.WHITE);
		add(ind4);

		list_e = new JList<>();
		list_e.setBounds(20, 0, 320, 360);
		modelo = new DefaultListModel<>();
		list_e.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		barra1 = new JScrollPane(list_e);
		barra1.setBounds(0, 0, 330, 365);

		panel_list = new JPanel();
		panel_list.setBounds(40, 80, 328, 335);
		panel_list.setLayout(null);
		panel_list.add(barra1);
		add(panel_list);

		filter = new JTextField();
		filter.setBounds(40, 50, 130, 20);
		filter.setBorder(null);
		filter.setFont(new Font("Consolas", Font.PLAIN, 15));
		add(filter);

		String[] orden = { "Activo", "Inactivo", "Top3 mas estudiantes", "Top3 mas colombianos" };

		campotipo = new JComboBox<>(orden);
		campotipo.setBounds(175, 50, 142, 20);
		campotipo.setFocusable(false);
		add(campotipo);

		panel_info = new JPanel();
		panel_info.setBounds(410, 50, 287, 180);
		panel_info.setLayout(null);
		add(panel_info);

		area1 = new JTextArea();
		area1.setBounds(0, 0, 287, 180);
		area1.setBackground(Color.LIGHT_GRAY);
		area1.setEditable(false);
		panel_info.add(area1);

		ascendant = new JButton();
		ascendant.setBounds(322, 50, 20, 20);
		ascendant.setFocusable(false);
		ascendant.setBorderPainted(false);
		ascendant.setIcon(new ImageIcon("src/Assets/ArrowUP.png"));
		add(ascendant);

		descending = new JButton();
		descending.setBounds(347, 50, 20, 20);
		descending.setFocusable(false);
		descending.setBorderPainted(false);
		descending.setIcon(new ImageIcon("src/Assets/ArrowD.png"));
		add(descending);

		delete = new JButton();
		delete.setBounds(420, 250, 120, 30);
		delete.setIcon(new ImageIcon("src/Assets/boton4.png"));
		delete.setFocusable(false);
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		add(delete);

		activate = new JButton();
		activate.setBounds(560, 250, 120, 30);
		activate.setIcon(new ImageIcon("src/Assets/boton4.png"));
		activate.setFocusable(false);
		activate.setBorderPainted(false);
		activate.setContentAreaFilled(false);
		add(activate);

		generatepdf = new JButton();
		generatepdf.setBounds(490, 385, 120, 30);
		generatepdf.setIcon(new ImageIcon("src/Assets/boton4.png"));
		generatepdf.setFocusable(false);
		generatepdf.setBorderPainted(false);
		generatepdf.setContentAreaFilled(false);
		add(generatepdf);

		actualpdf = new JButton();
		actualpdf.setBounds(490, 340, 120, 30);
		actualpdf.setIcon(new ImageIcon("src/Assets/boton4.png"));
		actualpdf.setFocusable(false);
		actualpdf.setBorderPainted(false);
		actualpdf.setContentAreaFilled(false);
		add(actualpdf);

	}

	public JButton getBack3() {
		return back3;
	}

	public void setBack3(JButton back3) {
		this.back3 = back3;
	}

	public JList<String> getList_e() {
		return list_e;
	}

	public void setList_e(JList<String> list_e) {
		this.list_e = list_e;
	}

	public JList<String> getList_pdf() {
		return list_pdf;
	}

	public void setList_pdf(JList<String> list_pdf) {
		this.list_pdf = list_pdf;
	}

	public DefaultListModel<String> getModelo() {
		return modelo;
	}

	public void setModelo(DefaultListModel<String> modelo) {
		this.modelo = modelo;
	}

	public DefaultListModel<String> getModelo2() {
		return modelo2;
	}

	public void setModelo2(DefaultListModel<String> modelo2) {
		this.modelo2 = modelo2;
	}

	public JPanel getPanel_list() {
		return panel_list;
	}

	public void setPanel_list(JPanel panel_list) {
		this.panel_list = panel_list;
	}

	public JPanel getPanel_list2() {
		return panel_list2;
	}

	public void setPanel_list2(JPanel panel_list2) {
		this.panel_list2 = panel_list2;
	}

	public JPanel getPanel_info() {
		return panel_info;
	}

	public void setPanel_info(JPanel panel_info) {
		this.panel_info = panel_info;
	}

	public JPanel getPanel_pdfs() {
		return panel_pdfs;
	}

	public void setPanel_pdfs(JPanel panel_pdfs) {
		this.panel_pdfs = panel_pdfs;
	}

	public JLabel getInd_lista() {
		return ind_lista;
	}

	public void setInd_lista(JLabel ind_lista) {
		this.ind_lista = ind_lista;
	}

	public JLabel getInd1() {
		return ind1;
	}

	public void setInd1(JLabel ind1) {
		this.ind1 = ind1;
	}

	public JLabel getInd2() {
		return ind2;
	}

	public void setInd2(JLabel ind2) {
		this.ind2 = ind2;
	}

	public JLabel getInd3() {
		return ind3;
	}

	public void setInd3(JLabel ind3) {
		this.ind3 = ind3;
	}

	public JLabel getInd4() {
		return ind4;
	}

	public void setInd4(JLabel ind4) {
		this.ind4 = ind4;
	}

	public JLabel getInd5() {
		return ind5;
	}

	public void setInd5(JLabel ind5) {
		this.ind5 = ind5;
	}

	public JTextArea getArea1() {
		return area1;
	}

	public void setArea1(JTextArea area1) {
		this.area1 = area1;
	}

	public JTextField getFilter() {
		return filter;
	}

	public void setFilter(JTextField filter) {
		this.filter = filter;
	}

	public JButton getAscendant() {
		return ascendant;
	}

	public void setAscendant(JButton ascendant) {
		this.ascendant = ascendant;
	}

	public JButton getDescending() {
		return descending;
	}

	public void setDescending(JButton descending) {
		this.descending = descending;
	}

	public JButton getDelete() {
		return delete;
	}

	public void setDelete(JButton delete) {
		this.delete = delete;
	}

	public JButton getActivate() {
		return activate;
	}

	public void setActivate(JButton activate) {
		this.activate = activate;
	}

	public JButton getActualpdf() {
		return actualpdf;
	}

	public void setActualpdf(JButton actualpdf) {
		this.actualpdf = actualpdf;
	}

	public JButton getGeneratepdf() {
		return generatepdf;
	}

	public void setGeneratepdf(JButton generatepdf) {
		this.generatepdf = generatepdf;
	}

	public JButton getGenerate() {
		return generate;
	}

	public void setGenerate(JButton generate) {
		this.generate = generate;
	}

	public JScrollPane getBarra1() {
		return barra1;
	}

	public void setBarra1(JScrollPane barra1) {
		this.barra1 = barra1;
	}

	public JScrollPane getBarra2() {
		return barra2;
	}

	public void setBarra2(JScrollPane barra2) {
		this.barra2 = barra2;
	}

	public JComboBox<String> getCampotipo() {
		return campotipo;
	}

	public void setCampotipo(JComboBox<String> campotipo) {
		this.campotipo = campotipo;
	}

	public Font getFranklin() {
		return Franklin;
	}

	public void setFranklin(Font franklin) {
		Franklin = franklin;
	}

}
