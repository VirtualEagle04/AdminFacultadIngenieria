package co.edu.unbosque.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.MatteBorder;

import com.toedter.calendar.JCalendar;

/**
 * Clase que hereda de JPanel diseñada para el registro del estudiante
 * 
 * @param imgfondo        JLabel que permite cargar la imagen diseñada para el
 *                        fondo del panel de registro
 * @param fondo           JPanel para contener el JLabel que contiene la imagen
 *                        del fondo
 * @param campos          JPanel para organizar la distribucion de los
 *                        componentes de registro de estudiante
 * @param mcalend         JPanel para contener el JCalendar
 * @param fondolista      JPanel para agregar un fondo de diferente color al
 *                        panel que contiene la JList de paises y municipios
 * @param plista          JPanel para contener la JList de paises y municipios
 * @param calendario      JCalendar para seleccionar la fecha de nacimiento
 * @param paises          JTextField para asignar el pais seleccionado en la
 *                        JList de paises
 * @param nombre          JTextField para registrar el nombre del estudiante
 * @param apellido        JTextField para registrar el apellido del estudiente
 * @param correo          JTextField para registrar el correo del estudiante
 * @param documento       JTextField para registrar el documento del estudiante
 * @param fecha           JTextField para asignar la fecha seleccionada en el
 *                        JCalendar
 * 
 * @param programa        JComboBox permite seleccionar el programa al que
 *                        pertenece el estudiante
 * @param jornada         JComboBox permite seleccionar la jornada a la que
 *                        pertenece el estudiante
 * @param genero          JComboBox permite seleccionar el genero del estudiante
 * @param nacional        JComboBox permite seleccionar el origen del
 *                        estudiante, puede ser nacional o extranjero
 * @param confirmar       JButton permite confirmar la seleccion de la lista de
 *                        paises o municipios
 * @param bpais           JButton que da acceso a la lista de paises o
 *                        municipios
 * @param calendar        JButton que da acceso al calendario
 * @param volver          JButton que permite volver al inico del programa
 * @param agregar         JButton que permite agregar un estudiante
 * @param confirmar_fecha JButton permite la confirmacion de la fecha
 *                        seleccionada
 * 
 * @param indpais         JLabel para indicar el pais
 * @param indnombre       JLabel para indicar el nombre
 * @param indapellido     JLabel para indicar el apellido
 * @param indcorreo       JLabel para indicar el correo
 * @param inddocumento    JLabel para indicar el documento
 * @param indprograma     JLabel para indicar el programa
 * @param indjornada      JLabel para indicar la jornada
 * @param indgenero       JLabel para indicar el genero
 * @param indfecha        JLabel para indicar la fecha
 * @param indlugar        JLabel para indicar el lugar de nacimiento
 * 
 * @param franklin        Font fuente establecidad para ser cargada en los
 *                        JLabel
 * @param lista_lugar     Jlist que permite mostrar de manera eficiente todo el
 *                        contenido relacionado a los paises o los municipios
 * @param modelo_lugar    DefaultListModel permite almacenar de manera temporal
 *                        la informacion que sera impuesta en la lista_lugar
 * @param barra4          JSrollPane que permite mostrar todos los elementos de
 *                        la lista_lugar que no quepan en el panel
 * 
 * 
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 *
 */

public class CrearEstudiante extends JPanel {

	private JLabel imgfondo;
	private JPanel fondo, campos, mcalend, fondolista, plista;
	private JCalendar calendario;
	private JTextField paises, nombre, apellido, correo, documento, fecha;
	private JComboBox<String> programa, jornada, genero, nacional;
	private JButton confirmar, bpais, calendar, volver, agregar, confirmar_fecha;
	private JLabel indpais, indnombre, indapellido, indcorreo, inddocumento, indprograma, indjornada, indgenero,
			indfecha, indlugar;
	private Font franklin;
	private JList<String> lista_lugar;
	private DefaultListModel<String> modelo_lugar;
	private JScrollPane barra4;

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

		fondolista = new JPanel();
		fondolista.setBounds(465, 248, 268, 187);
		fondolista.setBackground(new Color(90, 191, 175));
		fondolista.setLayout(null);
		add(fondolista).setVisible(false);

		confirmar = new JButton();
		confirmar.setBounds(85, 155, 100, 25);
		ImageIcon logoconfirmar2 = new ImageIcon("src/Assets/confirmarFecha.png");
		confirmar.setIcon(new ImageIcon(logoconfirmar2.getImage().getScaledInstance(100, 25, Image.SCALE_SMOOTH)));
		confirmar.setOpaque(false);
		confirmar.setBorderPainted(false);
		confirmar.setContentAreaFilled(false);
		confirmar.setFocusable(false);
		fondolista.add(confirmar);

		lista_lugar = new JList<>();
		lista_lugar.setBounds(10, 10, 247, 140);
		lista_lugar.setFont(franklin.deriveFont(Font.PLAIN, 15));
		modelo_lugar = new DefaultListModel<>();
		lista_lugar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		barra4 = new JScrollPane(lista_lugar);
		barra4.setBounds(0, 0, 248, 140);

		plista = new JPanel();
		plista.setBounds(10, 10, 247, 140);
		plista.setLayout(null);
		plista.add(barra4);
		add(plista);
		fondolista.add(plista);

		fondo = new JPanel();
		fondo.setBackground(Color.red);
		fondo.setLayout(null);
		fondo.setSize(750, 480);

		imgfondo = new JLabel(new ImageIcon("src/Assets/RegistroEstudiante.jpg"));
		imgfondo.setBounds(0, 0, 750, 480);

		campos = new JPanel();
		campos.setLayout(null);
		campos.setSize(750, 480);
		campos.setOpaque(false);

		nombre = new JTextField();
		nombre.setBounds(315, 128, 140, 25);
		nombre.setForeground(Color.WHITE);
		nombre.setFont(new Font("Consolas", Font.PLAIN, 15));
		MatteBorder borde = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.WHITE);
		nombre.setBorder(borde);
		nombre.setOpaque(false);

		apellido = new JTextField();
		apellido.setBounds(548, 128, 140, 25);
		apellido.setForeground(Color.WHITE);
		apellido.setFont(new Font("Consolas", Font.PLAIN, 15));
		apellido.setBorder(borde);
		apellido.setOpaque(false);

		correo = new JTextField();
		correo.setBounds(315, 188, 140, 25);
		correo.setForeground(Color.WHITE);
		correo.setFont(new Font("Consolas", Font.PLAIN, 15));
		correo.setBorder(borde);
		correo.setOpaque(false);

		documento = new JTextField();
		documento.setBounds(548, 188, 140, 25);
		documento.setForeground(Color.WHITE);
		documento.setFont(new Font("Consolas", Font.PLAIN, 15));
		documento.setBorder(borde);
		documento.setOpaque(false);

		String[] programas = { "Seleccionar", "Bioingenieria", "Ingenieria Ambiental", "Ingenieria Electronica",
				"Ingenieria Industrial", "Ingenieria de Sistemas" };

		programa = new JComboBox(programas);
		programa.setBounds(315, 248, 140, 25);

		fecha = new JTextField();
		fecha.setBounds(545, 248, 140, 25);
		fecha.setForeground(Color.WHITE);
		fecha.setFont(new Font("Consolas", Font.PLAIN, 15));
		fecha.setBorder(borde);
		fecha.setOpaque(false);
		fecha.setEditable(false);

		indpais = new JLabel("Lugar de Nacimiento");
		indpais.setFont(franklin);
		indpais.setFont(indpais.getFont().deriveFont(Font.PLAIN, 15));
		indpais.setBounds(546, 332, 140, 50);
		indpais.setForeground(Color.WHITE);

		paises = new JTextField();
		paises.setBounds(545, 368, 140, 25);
		paises.setForeground(Color.WHITE);
		paises.setFont(new Font("Consolas", Font.PLAIN, 15));
		paises.setBorder(borde);
		paises.setOpaque(false);
		paises.setEditable(false);

		String[] generos = { "Seleccionar", "Masculino", "Femenino" };

		genero = new JComboBox(generos);
		genero.setBounds(315, 308, 140, 25);

		String[] jornadas = { "Diurno" };

		jornada = new JComboBox(jornadas);
		jornada.setBounds(545, 308, 140, 25);

		String[] opciones = { "Nacional", "Extranjero" };

		nacional = new JComboBox(opciones);
		nacional.setBounds(315, 368, 140, 25);

		calendar = new JButton();
		calendar.setBounds(655, 248, 30, 25);
		calendar.setIcon(new ImageIcon("src/Assets/logocalendar.png"));
		calendar.setOpaque(false);
		calendar.setBorderPainted(false);
		calendar.setContentAreaFilled(false);

		bpais = new JButton();
		bpais.setBounds(655, 368, 30, 25);
		bpais.setFocusable(false);
		bpais.setBorderPainted(false);
		bpais.setContentAreaFilled(false);
		bpais.setIcon(new ImageIcon("src/Assets/paises.png"));

		volver = new JButton();
		volver.setBounds(15, 15, 100, 25);
		ImageIcon logovolver = new ImageIcon("src/Assets/VolverC.png");
		volver.setIcon(new ImageIcon(logovolver.getImage().getScaledInstance(100, 25, Image.SCALE_SMOOTH)));
		volver.setOpaque(false);
		volver.setBorderPainted(false);
		volver.setFocusable(false);
		volver.setContentAreaFilled(false);

		agregar = new JButton();
		agregar.setBounds(435, 405, 130, 30);
		agregar.setOpaque(false);
		agregar.setBorderPainted(false);
		agregar.setFocusable(false);
		agregar.setContentAreaFilled(false);
		agregar.setIcon(new ImageIcon("src/Assets/AgregarC.png"));

		calendario = new JCalendar();
		calendario.setBounds(5, 10, 256, 132);
		calendario.setBackground(new Color(90, 191, 175));

		mcalend = new JPanel();
		mcalend.setLayout(null);
		mcalend.setBounds(465, 248, 268, 187);
		mcalend.setBackground(new Color(90, 191, 175));
		mcalend.add(calendario);
		mcalend.setVisible(false);

		confirmar_fecha = new JButton();
		confirmar_fecha.setBounds(80, 145, 100, 25);
		ImageIcon logoconfirmar = new ImageIcon("src/Assets/confirmarFecha.png");
		confirmar_fecha.setIcon(new ImageIcon(logoconfirmar.getImage().getScaledInstance(100, 25, Image.SCALE_SMOOTH)));
		confirmar_fecha.setOpaque(false);
		confirmar_fecha.setBorderPainted(false);
		confirmar_fecha.setContentAreaFilled(false);
		confirmar_fecha.setFocusable(false);
		mcalend.add(confirmar_fecha, JLayeredPane.DRAG_LAYER);

		indnombre = new JLabel("Nombres Completos");
		indnombre.setFont(franklin);
		indnombre.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indnombre.setBounds(315, 92, 140, 50);
		indnombre.setForeground(Color.WHITE);

		indapellido = new JLabel("Apellidos Completos");
		indapellido.setFont(franklin);
		indapellido.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indapellido.setBounds(546, 92, 140, 50);
		indapellido.setForeground(Color.WHITE);

		indcorreo = new JLabel("Correo electrónico");
		indcorreo.setFont(franklin);
		indcorreo.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indcorreo.setBounds(315, 152, 140, 50);
		indcorreo.setForeground(Color.WHITE);

		inddocumento = new JLabel("Documento de identidad");
		inddocumento.setFont(franklin);
		inddocumento.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		inddocumento.setBounds(546, 152, 140, 50);
		inddocumento.setForeground(Color.WHITE);

		indprograma = new JLabel("Programa de estudio");
		indprograma.setFont(franklin);
		indprograma.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indprograma.setBounds(315, 212, 140, 50);
		indprograma.setForeground(Color.WHITE);

		indfecha = new JLabel("Fecha de nacimiento");
		indfecha.setFont(franklin);
		indfecha.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indfecha.setBounds(546, 212, 140, 50);
		indfecha.setForeground(Color.WHITE);

		indgenero = new JLabel("Género");
		indgenero.setFont(franklin);
		indgenero.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indgenero.setBounds(315, 272, 140, 50);
		indgenero.setForeground(Color.WHITE);

		indjornada = new JLabel("Jornada");
		indjornada.setFont(franklin);
		indjornada.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indjornada.setBounds(546, 272, 140, 50);
		indjornada.setForeground(Color.WHITE);

		indlugar = new JLabel("Nacional / Extranjero");
		indlugar.setFont(franklin);
		indlugar.setFont(indnombre.getFont().deriveFont(Font.PLAIN, 15));
		indlugar.setBounds(315, 332, 140, 50);
		indlugar.setForeground(Color.WHITE);

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
		campos.add(bpais);
		campos.add(paises);
		campos.add(indpais);
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

	public JPanel getFondolista() {
		return fondolista;
	}

	public void setFondolista(JPanel fondolista) {
		this.fondolista = fondolista;
	}

	public JPanel getPlista() {
		return plista;
	}

	public void setPlista(JPanel plista) {
		this.plista = plista;
	}

	public JTextField getPaises() {
		return paises;
	}

	public void setPaises(JTextField paises) {
		this.paises = paises;
	}

	public JButton getConfirmar() {
		return confirmar;
	}

	public void setConfirmar(JButton confirmar) {
		this.confirmar = confirmar;
	}

	public JButton getBpais() {
		return bpais;
	}

	public void setBpais(JButton bpais) {
		this.bpais = bpais;
	}

	public JLabel getIndpais() {
		return indpais;
	}

	public void setIndpais(JLabel indpais) {
		this.indpais = indpais;
	}

	public JList<String> getLista_lugar() {
		return lista_lugar;
	}

	public void setLista_lugar(JList<String> lista_lugar) {
		this.lista_lugar = lista_lugar;
	}

	public DefaultListModel<String> getModelo_lugar() {
		return modelo_lugar;
	}

	public void setModelo_lugar(DefaultListModel<String> modelo_lugar) {
		this.modelo_lugar = modelo_lugar;
	}

	public JScrollPane getBarra4() {
		return barra4;
	}

	public void setBarra4(JScrollPane barra4) {
		this.barra4 = barra4;
	}

	public JLabel getImgfondo() {
		return imgfondo;
	}

	public void setImgfondo(JLabel imgfondo) {
		this.imgfondo = imgfondo;
	}

	public JPanel getFondo() {
		return fondo;
	}

	public void setFondo(JPanel fondo) {
		this.fondo = fondo;
	}

	public JPanel getCampos() {
		return campos;
	}

	public void setCampos(JPanel campos) {
		this.campos = campos;
	}

	public JPanel getMcalend() {
		return mcalend;
	}

	public void setMcalend(JPanel mcalend) {
		this.mcalend = mcalend;
	}

	public JCalendar getCalendario() {
		return calendario;
	}

	public void setCalendario(JCalendar calendario) {
		this.calendario = calendario;
	}

	public JTextField getNombre() {
		return nombre;
	}

	public void setNombre(JTextField nombre) {
		this.nombre = nombre;
	}

	public JTextField getApellido() {
		return apellido;
	}

	public void setApellido(JTextField apellido) {
		this.apellido = apellido;
	}

	public JTextField getCorreo() {
		return correo;
	}

	public void setCorreo(JTextField correo) {
		this.correo = correo;
	}

	public JTextField getDocumento() {
		return documento;
	}

	public void setDocumento(JTextField documento) {
		this.documento = documento;
	}

	public JTextField getFecha() {
		return fecha;
	}

	public void setFecha(JTextField fecha) {
		this.fecha = fecha;
	}

	public JComboBox<String> getPrograma() {
		return programa;
	}

	public void setPrograma(JComboBox<String> programa) {
		this.programa = programa;
	}

	public JComboBox<String> getJornada() {
		return jornada;
	}

	public void setJornada(JComboBox<String> jornada) {
		this.jornada = jornada;
	}

	public JComboBox<String> getGenero() {
		return genero;
	}

	public void setGenero(JComboBox<String> genero) {
		this.genero = genero;
	}

	public JComboBox<String> getNacional() {
		return nacional;
	}

	public void setNacional(JComboBox<String> nacional) {
		this.nacional = nacional;
	}

	public JButton getCalendar() {
		return calendar;
	}

	public void setCalendar(JButton calendar) {
		this.calendar = calendar;
	}

	public JButton getVolver() {
		return volver;
	}

	public void setVolver(JButton volver) {
		this.volver = volver;
	}

	public JButton getAgregar() {
		return agregar;
	}

	public void setAgregar(JButton agregar) {
		this.agregar = agregar;
	}

	public JLabel getIndnombre() {
		return indnombre;
	}

	public void setIndnombre(JLabel indnombre) {
		this.indnombre = indnombre;
	}

	public JLabel getIndapellido() {
		return indapellido;
	}

	public void setIndapellido(JLabel indapellido) {
		this.indapellido = indapellido;
	}

	public JLabel getIndcorreo() {
		return indcorreo;
	}

	public void setIndcorreo(JLabel indcorreo) {
		this.indcorreo = indcorreo;
	}

	public JLabel getInddocumento() {
		return inddocumento;
	}

	public void setInddocumento(JLabel inddocumento) {
		this.inddocumento = inddocumento;
	}

	public JLabel getIndprograma() {
		return indprograma;
	}

	public void setIndprograma(JLabel indprograma) {
		this.indprograma = indprograma;
	}

	public JLabel getIndjornada() {
		return indjornada;
	}

	public void setIndjornada(JLabel indjornada) {
		this.indjornada = indjornada;
	}

	public JLabel getIndgenero() {
		return indgenero;
	}

	public void setIndgenero(JLabel indgenero) {
		this.indgenero = indgenero;
	}

	public JLabel getIndfecha() {
		return indfecha;
	}

	public void setIndfecha(JLabel indfecha) {
		this.indfecha = indfecha;
	}

	public JLabel getIndlugar() {
		return indlugar;
	}

	public void setIndlugar(JLabel indlugar) {
		this.indlugar = indlugar;
	}

	public Font getFranklin() {
		return franklin;
	}

	public void setFranklin(Font franklin) {
		this.franklin = franklin;
	}

	public JButton getConfirmar_fecha() {
		return confirmar_fecha;
	}

	public void setConfirmar_fecha(JButton confirmar_fecha) {
		this.confirmar_fecha = confirmar_fecha;
	}

}
