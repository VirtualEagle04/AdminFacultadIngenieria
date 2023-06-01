package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;

/**
 * 
 * Clase que hereda de JPanel y que contiene la lista de estudiantes, el filtro
 * de busqueda, nombres, apellidos, documentos, ordenamientos y generacion de
 * pdf
 * 
 * @param list_e      JList que permite mostrar de manera eficiente todo el
 *                    contenido relacionado a la gestion de los estudiantes
 * @param list_pdf    JList que permite mostrar de manera eficiente todo el
 *                    contenido relacionado a las versiones de los informes
 *                    generados en pdf
 * @param modelo      DefaultListModel permite almacenar de forma temporal la
 *                    informacion que sera impuesta en la lista de estudiantes
 * @param modelo2     DefaultListModel permite almacenar de forma temporal la
 *                    informacion que sera impuesta en la lista de pdfs
 * @param panel_list  JPanel que contiene la lista de estudiantes
 * @param panel_list2 JPanel que contiene la lista de versiones de los pdfs
 * @param panel_info  JPanel que contiene un JTextArea para mostrar la
 *                    informacion de cada estudiante
 * @param panel_pdfs  Jpanel que contiene el panel_list2 y botones para el
 *                    funcionamiento y generacion de pdfs
 * @param ind_close   JLabel para indicar el cerrado del panel_pdfs
 * @param ind_lista   JLabel para indicar el documento pdf a generar
 * @param ind1        JLabel para indicar la generacion del pdf actual
 * @param ind2        JLabel para indicar el acceso al panel_pdfs que permitira
 *                    la seleccion de las versiones de los pdfs
 * @param ind3        JLabel para indicar la eliminacion de un estudiante de la
 *                    lista
 * @param ind4        JLabel para indicar la activacion o inactivacion de un
 *                    estudiante
 * @param ind5        JLabel para indicar la generacion de un pdf seleccionado
 *                    de la lista de versiones de pdfs
 * @param area1       JTextArea que contiene la informacion de cada estudiante
 *                    seleccionado en la lista
 * @param filter      JTextField que contiene el acceso de la informacion
 *                    asociada al filtro de los estudiantes
 * @param close       JButton que permite cerrar el panel que muestra la lista
 *                    de pdfs
 * @param back3       JButton que permite volver al inicio del programa
 * @param delete      JButton que permite la confirmaicon frente a la
 *                    eliminacion de un estudiante
 * @param activate    JButton que permite activar o inactivar a un estudiante
 * @param actualpdf   JButton que perminte generar el pdf actual
 * @param generarpdf  JButton que permite acceder al panel que muestra la lista
 *                    de versiones de pdfs
 * @param generate    JButton que permite seleccionar y generar el pdf
 *                    seleccionado en la lista de pdfs
 * @param barra1      JScrollPane que permite mostrar todos los elementos de la
 *                    lista de estudiantes aun asi no quepan en el panel
 * @param barra2      JScrollPanle que permite mostrar todos los elementos de la
 *                    lista de pdfs aun asi no quepan en el panel
 * @param campotipo   JComboBox que permite seleccionar determinados filtrados
 *                    de la informacion en la lista de los estudiantes
 * @param franklin    Font fuente establecidad para ser cargada en los JLabel
 * @param sort        JToggleButton boton empleado para realizar los
 *                    ordenamientos correspondientes en la lista de estudiantes
 * 
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 *
 */

public class AdminControl extends JPanel {

	private JList<String> list_e;
	private JList<String> list_pdf;
	private DefaultListModel<String> modelo;
	private DefaultListModel<String> modelo2;
	private JPanel panel_list, panel_list2, panel_info, panel_pdfs;
	private JLabel ind_close, ind_lista, ind1, ind2, ind3, ind4, ind5;
	private JTextArea area1;
	private JTextField filter;
	private JButton close, back3, delete, activate, actualpdf, generatepdf, generate;
	private JScrollPane barra1, barra2;
	private JComboBox<String> campotipo;
	private Font franklin;
	private JToggleButton sort;

	public AdminControl() {

		try {

			franklin = Font.createFont(Font.TRUETYPE_FONT,
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

		panel_pdfs.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				setComponentZOrder(panel_pdfs, 0);
				panel_pdfs.repaint();
			}

			@Override
			public void mousePressed(MouseEvent e) {
				setComponentZOrder(panel_pdfs, 0);
				panel_pdfs.repaint();
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setComponentZOrder(panel_pdfs, 0);
				panel_pdfs.repaint();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				setComponentZOrder(panel_pdfs, 0);
				panel_pdfs.repaint();
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				setComponentZOrder(panel_pdfs, 0);
				panel_pdfs.repaint();
			}
		});

		ind_lista = new JLabel("Seleccione Documento a Generar");
		ind_lista.setBounds(70, 5, 270, 30);
		ind_lista.setFont(franklin);
		ind_lista.setFont(ind_lista.getFont().deriveFont(Font.PLAIN, 20));
		ind_lista.setForeground(Color.WHITE);
		panel_pdfs.add(ind_lista);

		ind5 = new JLabel("Generar");
		ind5.setFont(franklin);
		ind5.setFont(ind5.getFont().deriveFont(Font.PLAIN, 17));
		ind5.setBounds(93, 340, 120, 30);
		ind5.setForeground(Color.WHITE);
		add(ind5);
		panel_pdfs.add(ind5);

		ind_close = new JLabel("Cerrar");
		ind_close.setFont(franklin);
		ind_close.setFont(ind_close.getFont().deriveFont(Font.PLAIN, 17));
		ind_close.setBounds(257, 340, 120, 30);
		ind_close.setForeground(Color.WHITE);
		add(ind_close);
		panel_pdfs.add(ind_close);

		generate = new JButton();
		generate.setBounds(60, 340, 120, 30);
		generate.setIcon(new ImageIcon("src/Assets/boton4.png"));
		generate.setFocusable(false);
		generate.setBorderPainted(false);
		generate.setContentAreaFilled(false);
		panel_pdfs.add(generate);

		close = new JButton();
		close.setBounds(220, 340, 120, 30);
		close.setIcon(new ImageIcon("src/Assets/boton4.png"));
		close.setFocusable(false);
		close.setBorderPainted(false);
		close.setContentAreaFilled(false);
		panel_pdfs.add(close);

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
		ind1.setFont(franklin);
		ind1.setFont(ind1.getFont().deriveFont(Font.PLAIN, 17));
		ind1.setBounds(500, 340, 120, 30);
		ind1.setForeground(Color.WHITE);
		add(ind1);

		ind2 = new JLabel("Generar PDF");
		ind2.setFont(franklin);
		ind2.setFont(ind2.getFont().deriveFont(Font.PLAIN, 17));
		ind2.setBounds(509, 385, 120, 30);
		ind2.setForeground(Color.WHITE);
		add(ind2);

		ind3 = new JLabel("Eliminar Estudiante");
		ind3.setFont(franklin);
		ind3.setFont(ind3.getFont().deriveFont(Font.PLAIN, 15));
		ind3.setBounds(425, 250, 150, 30);
		ind3.setForeground(Color.WHITE);
		add(ind3);

		ind4 = new JLabel("Activar/Inactivar");
		ind4.setFont(franklin);
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
		panel_list.setBounds(40, 75, 328, 362);
		panel_list.setLayout(null);
		panel_list.add(barra1);
		add(panel_list);

		filter = new JTextField();
		filter.setBounds(40, 50, 130, 20);
		filter.setBorder(null);
		filter.setFont(new Font("Consolas", Font.PLAIN, 15));
		add(filter);

		String[] orden = { "Nombre", "Apellido", "Documento", "Activo", "Inactivo", "Top 3 Más Estudiantes",
				"Top 3 Más Nacionales" };

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
		area1.setHighlighter(null);
		area1.setFont(new Font("Consolas", Font.PLAIN, 12));
		panel_info.add(area1);

		sort = new JToggleButton();
		sort.setBounds(335, 50, 20, 20);
		sort.setFocusable(false);
		sort.setBorderPainted(false);
		sort.setIcon(new ImageIcon("src/Assets/ArrowUP.png"));
		add(sort);

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

	public JLabel getInd_close() {
		return ind_close;
	}

	public void setInd_close(JLabel ind_close) {
		this.ind_close = ind_close;
	}

	public JButton getClose() {
		return close;
	}

	public void setClose(JButton close) {
		this.close = close;
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

	public JToggleButton getSort() {
		return sort;
	}

	public void setSort(JToggleButton sort) {
		this.sort = sort;
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
		return franklin;
	}

	public void setFranklin(Font franklin) {
		franklin = franklin;
	}

}
