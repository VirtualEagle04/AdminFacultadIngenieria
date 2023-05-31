package co.edu.unbosque.model.persistance;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.SubCategoryAxis;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.Marker;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.ValueMarker;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.jfree.chart.ChartPanel;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.util.MTC;

/**
 * 
 * Clase encargada de la manipulacion de archivos
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class FileHandler {

	private static Scanner lector;
	private static File archivo;
	private static PrintWriter escritor;
	private static ObjectInputStream ois;
	private static ObjectOutputStream oos;
	private static FileOutputStream fos;
	private static PdfWriter pdfesc;
	private static PdfContentByte pcb;

	public FileHandler() {
	}

	/**
	 * Metodo encargado de crear y escribir el serializado requerido
	 * 
	 * @param nombre_archivo String que almacena el nombre que se le dara al archivo
	 * @param obj            Objeto a serializar y escribir en el archivo
	 */
	public static void escribirSerializado(String nombre_archivo, Object obj) {

		try {
			oos = new ObjectOutputStream(
					new FileOutputStream("src/co/edu/unbosque/model/persistance/" + nombre_archivo));
			oos.writeObject(obj);
			oos.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error de Entrada: No se ha encontrado el archivo. (Serializado/Entrada).");
		} catch (IOException e) {
			System.err.println("Error de Entrada: Revise Permisos. (Serializado/Entrada).");
		}

	}

	/**
	 * Metodo encargado de leer el serializado que pida
	 * 
	 * @param nombre_archivo String que almacena el nombre del serializado que piden
	 * @return Objeto almacenado en el serializado
	 */
	public static Object leerSerializado(String nombre_archivo) {
		try {
			ois = new ObjectInputStream(new FileInputStream("src/co/edu/unbosque/model/persistance/" + nombre_archivo));
			Object o = ois.readObject();
			ois.close();
			return o;
		} catch (FileNotFoundException e) {
			System.err.println("Error de Lectura: No se ha encontrado el archivo. (Serializado/Salida).");

		} catch (IOException e) {
			System.err.println("Error de Lectura: Revise Permisos. (Serializado/Salida).");
		} catch (ClassNotFoundException e) {
			System.err.println("Error de Lectura: No se ha encontrado el archivo. (Serializado/Salida).");
		}

		return null;
	}

	/**
	 * Metodo encargado de abrir un archivo
	 * 
	 * @param nombre_archivo String que almacena el nombre del archivo a abrir
	 * @return String que almacena el contenido del archivo abierto
	 */
	public static String abrirArchivo(String nombre_archivo) {

		archivo = new File("src/co/edu/unbosque/model/persistance/" + nombre_archivo);
		StringBuilder contenido = new StringBuilder();
		try {
			lector = new Scanner(archivo);
			while (lector.hasNext()) {
				contenido.append(lector.nextLine() + "\n");
			}
			lector.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error de Lectura: Archivo no encontrado.");
		} catch (IOException e1) {
			System.err.println("Error de Lectura: Revise permisos.");
		}

		return contenido.toString();
	}

	/**
	 * Metodo encargado de crear y escribir un archivo con el contenido que quieran
	 * 
	 * @param nombre_archivo String que almacena el nombre del archivo que quieren
	 *                       usar
	 * @param contenido      String que almacena el contenido a escribir en el
	 *                       archivo
	 */
	public static void escribirArchivo(String nombre_archivo, String contenido) {
		archivo = new File("src/co/edu/unbosque/model/persistance/" + nombre_archivo);
		try {
			escritor = new PrintWriter(archivo);
			escritor.println(contenido);
			escritor.close();
		} catch (FileNotFoundException e) {
			System.err.println("Error de Escritura: Archivo no encontrado.");
			try {
				archivo.createNewFile();
			} catch (IOException e1) {
			}
		} catch (IOException e2) {
			System.err.println("Error de Escritura: Revise permisos.");
		}
	}

	/**
	 * Metodo encargado de abrir el archivo y traer el contenido del mismo
	 * 
	 * @param nombre String que almacena el nombre de el archivo a abrir
	 * @return ArrayList con el contenido del archivo
	 */
	public static ArrayList<String> cargarDesdeArchivo(String nombre) {
		ArrayList<String> desde_archivo = new ArrayList<>();
		String contenido = FileHandler.abrirArchivo(nombre);
		String[] lineas = contenido.split("\n");
		for (String linea : lineas) {
			desde_archivo.add(linea);
		}
		return desde_archivo;
	}

	/**
	 * Metodo encargado de crear el respectivo pdf con las graficas creadas
	 * 
	 * @param nombre_pdf String Nombre del pdf que desea crear
	 * @param chart      Array de chart que almacena las graficas a añadir al pdf
	 */
	public static void crearPdf(String nombre_pdf, JFreeChart[] chart) {
		Document documento = new Document();
		ByteArrayOutputStream[] baos = new ByteArrayOutputStream[chart.length];
		Image[] img = new Image[chart.length];

		int ind = 0;
		for (JFreeChart jfc : chart) {
			baos[ind] = new ByteArrayOutputStream();
			try {
				ChartUtilities.writeChartAsPNG(baos[ind], jfc, 500, 300);

				img[ind] = Image.getInstance(baos[ind].toByteArray());

			} catch (Exception e) {
				e.printStackTrace();
			}
			ind++;
		}
		try {

			fos = new FileOutputStream("src/co/edu/unbosque/model/persistance/" + nombre_pdf);
			pdfesc = PdfWriter.getInstance(documento, fos);
			documento.open();

			for (Image i : img) {
				documento.add(i);
			}

			documento.close();

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	// GENERAL
	/**
	 * Metodo encargado de crear graficas y introducirlas a un array
	 */
	public static JFreeChart[] crearGraficas(ArrayList<EstudianteDTO> lista_est) {
		JFreeChart[] graficas = new JFreeChart[6];
		graficas[0] = crearGraficaEdad(MTC.mediaEdad(lista_est), MTC.medianaEdad(lista_est), MTC.modaEdad(lista_est));
		graficas[1] = crearGraficaGenero(MTC.modaGenero(lista_est));
		graficas[2] = crearGraficaPrograma(MTC.modaPrograma(lista_est));
		graficas[3] = crearGraficaJornada(MTC.modaJornada(lista_est));
		graficas[4] = crearGraficaActivoInactivo(MTC.modaActivoInactivo(lista_est));
		graficas[5] = crearGraficaNacionalidad(MTC.modaNacionalidad(lista_est));

		return graficas;
	}

	// EDAD
	/**
	 * Metodo encargado de crear una grafica de la edad de los estudiantes
	 * 
	 * @param media     Int encargado de almacenar el dato de la media
	 * @param mediana   Int encargado de almacenar el dato de la media
	 * @param mapa_moda HashMap Encargado de almacenar los datos de la moda
	 * @return JfreeChart que almacena la grafica creada
	 */
	public static JFreeChart crearGraficaEdad(int media, int mediana, HashMap<Integer, Integer> mapa_moda) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HashMap.Entry<Integer, Integer> parejas : mapa_moda.entrySet()) {

			dataset.setValue(parejas.getValue(), "", parejas.getKey());

		}
		JFreeChart chart = ChartFactory.createBarChart("Medidas de Tendencia Central de las Edades de los Estudiantes",
				"Edades", "Valor", dataset, PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getPlot().setBackgroundPaint(Color.white);

		Font fuente = new Font("Arial", Font.BOLD, 10);

		CategoryMarker marker_media = new CategoryMarker(media);
		marker_media.setPaint(Color.red);
		marker_media.setDrawAsLine(true);
		marker_media.setLabel("Media");
		marker_media.setLabelFont(fuente);

		CategoryMarker marker_mediana = new CategoryMarker(mediana);
		marker_mediana.setPaint(new Color(3, 17, 159));
		marker_mediana.setDrawAsLine(true);
		marker_mediana.setLabel("Mediana");
		marker_mediana.setLabelFont(fuente);

		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(22, 224, 70));
		renderer.setBarPainter(new StandardBarPainter());

		plot.addDomainMarker(marker_media);
		plot.addDomainMarker(marker_mediana);
		plot.setRangeGridlinePaint(Color.gray);
		plot.setDomainGridlinePaint(Color.gray);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setCategoryMargin(0.05);

		return chart;
	}
/**
 * Metodo encargado de la creacion de la grafica de genero
 * 
 * @param mapa_moda HashMap almacena el genero y el valor
 * @return JfreeChart que almacena la grafica creada con los valores dados
 */
	// GENERO
	public static JFreeChart crearGraficaGenero(HashMap<Character, Integer> mapa_moda) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HashMap.Entry<Character, Integer> parejas : mapa_moda.entrySet()) {

			dataset.setValue(parejas.getValue(), "", parejas.getKey());

		}

		JFreeChart chart = ChartFactory.createBarChart("Estudiantes por Genero", "Genero", "Valor", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getPlot().setBackgroundPaint(Color.white);

		Font fuente = new Font("Consolas", Font.BOLD, 12);

		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(22, 224, 70));
		renderer.setBarPainter(new StandardBarPainter());

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(fuente);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		plot.setRangeGridlinePaint(Color.gray);
		plot.setDomainGridlinePaint(Color.gray);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.2);
		domainAxis.setUpperMargin(0.2);
		domainAxis.setCategoryMargin(0.05);

		return chart;
	}

	// PROGRAMA
	/**
	 * Metodo encargado de crear la grafica de el programa academico
	 * @param mapa_moda HashMap encargado de almacenar la etiqueta con su respectivo valor
	 * @return JFreeChart encargado de almacenar la grafica creada
	 */
	public static JFreeChart crearGraficaPrograma(HashMap<String, Integer> mapa_moda) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HashMap.Entry<String, Integer> parejas : mapa_moda.entrySet()) {

			dataset.setValue(parejas.getValue(), "", parejas.getKey());

		}

		JFreeChart chart = ChartFactory.createBarChart("Estudiantes por Programa Académico", "Programa Académico",
				"Valor", dataset, PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getPlot().setBackgroundPaint(Color.white);

		Font fuente = new Font("Consolas", Font.BOLD, 12);

		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(22, 224, 70));
		renderer.setBarPainter(new StandardBarPainter());

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(fuente);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		plot.setRangeGridlinePaint(Color.gray);
		plot.setDomainGridlinePaint(Color.gray);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.1);
		domainAxis.setUpperMargin(0.1);
		domainAxis.setCategoryMargin(0.1);
		domainAxis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);

		return chart;
	}

	// ACTIVO / INACTIVO
	/**
	 * Metodo encargado de crear la grafica de actividad o inactividad
	 * @param mapa_moda HashMap encargado de almacenar la etiqueta con su respectivo valor
	 * @return JFreeChart encargado de almacenar  la grafica creada
	 */
	public static JFreeChart crearGraficaActivoInactivo(HashMap<String, Integer> mapa_moda) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HashMap.Entry<String, Integer> parejas : mapa_moda.entrySet()) {

			dataset.setValue(parejas.getValue(), "", parejas.getKey());

		}

		JFreeChart chart = ChartFactory.createBarChart("Estudiantes por Estado", "Estado", "Valor", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getPlot().setBackgroundPaint(Color.white);

		Font fuente = new Font("Consolas", Font.BOLD, 12);

		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(22, 224, 70));
		renderer.setBarPainter(new StandardBarPainter());

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(fuente);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		plot.setRangeGridlinePaint(Color.gray);
		plot.setDomainGridlinePaint(Color.gray);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.2);
		domainAxis.setUpperMargin(0.2);
		domainAxis.setCategoryMargin(0.05);

		return chart;
	}

	// JORNADA
	/**
	 * Metodo encargado de crear la grafica de jornada
	 * @param mapa_moda HashMap en cargado de almacenar las respectivas etiquetas con su valor
	 * @return JFreeChart encargado de almacenar la grafica creada
	 */
	public static JFreeChart crearGraficaJornada(HashMap<String, Integer> mapa_moda) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HashMap.Entry<String, Integer> parejas : mapa_moda.entrySet()) {

			dataset.setValue(parejas.getValue(), "", parejas.getKey());

		}

		JFreeChart chart = ChartFactory.createBarChart("Estudiantes por Jornada", "Jornada", "Valor", dataset,
				PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getPlot().setBackgroundPaint(Color.white);

		Font fuente = new Font("Consolas", Font.BOLD, 12);

		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(22, 224, 70));
		renderer.setBarPainter(new StandardBarPainter());

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(fuente);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		plot.setRangeGridlinePaint(Color.gray);
		plot.setDomainGridlinePaint(Color.gray);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.2);
		domainAxis.setUpperMargin(0.2);
		domainAxis.setCategoryMargin(0.05);

		return chart;
	}

	// NACIONALIDAD
	/**
	 * Metodo encargado de crear la grafica de nacionalidad
	 * @param mapa_moda HashMap en cargado de almacenar las respectivas etiquetas con su valor
	 * @return JFreeChart encargado de almacenar la grafica creada
	 */
	public static JFreeChart crearGraficaNacionalidad(HashMap<String, Integer> mapa_moda) {

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		for (HashMap.Entry<String, Integer> parejas : mapa_moda.entrySet()) {

			dataset.setValue(parejas.getValue(), "", parejas.getKey());

		}

		JFreeChart chart = ChartFactory.createBarChart("Estudiantes Nacionales / Extranjeros", "Origen", "Valor",
				dataset, PlotOrientation.VERTICAL, false, false, false);
		chart.setBackgroundPaint(Color.white);
		chart.getPlot().setBackgroundPaint(Color.white);

		Font fuente = new Font("Consolas", Font.BOLD, 12);

		CategoryPlot plot = chart.getCategoryPlot();

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setSeriesPaint(0, new Color(22, 224, 70));
		renderer.setBarPainter(new StandardBarPainter());

		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBaseItemLabelFont(fuente);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.TOP_CENTER);
		renderer.setBasePositiveItemLabelPosition(position);

		plot.setRangeGridlinePaint(Color.gray);
		plot.setDomainGridlinePaint(Color.gray);

		CategoryAxis domainAxis = plot.getDomainAxis();
		domainAxis.setLowerMargin(0.2);
		domainAxis.setUpperMargin(0.2);
		domainAxis.setCategoryMargin(0.05);

		return chart;
	}

}