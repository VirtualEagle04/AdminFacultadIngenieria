package co.edu.unbosque.test.persistance;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryMarker;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.TextAnchor;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.model.persistance.FileHandler;
import co.edu.unbosque.util.MTC;

/**
 * Clase encargada de las pruebas unitarias del FileHandler
 * 
 * @param cont   Int que almacena el contador de pruebas
 * @param passed Int que almacena el contador de pruebas pasadas
 * @param failed Int que almacena el contador de pruebas erroneas
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */

@TestMethodOrder(org.junit.jupiter.api.MethodOrderer.OrderAnnotation.class)

public class FileHandlerTest {

	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;

	private static String nombre_test_serializado = "test.txt";
	private static String nombre_test_csv = "test.csv";
	private static String datos_test = "datos texto";
	private static ArrayList<EstudianteDTO> lista = (ArrayList<EstudianteDTO>) FileHandler
			.leerSerializado("estudiantes.txt");;

	@BeforeAll
	/**
	 * Metodo que inicia e introduce
	 */
	public static void inicializacion() throws ParseException {
		System.out.println("Iniciando pruebas unitarias: FileHandler");
	}

	@AfterAll
	/**
	 * Metodo que finaliza y despide
	 */
	public static void finalizacion() {
		System.out.print("\u001B[0m");
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: " + passed + "/13\n-Fallido: " + failed
				+ "/13\n<----------------------------------->");
	}

	/**
	 * Inicia el archivo de serializado de la prueba
	 */
	public void inicializacionArchivoPruebaSerializado() {
		File file = new File("src/co/edu/unbosque/model/persistance/" + nombre_test_serializado);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * Termina el archivo de serializado de la prueba
	 */
	public void terminacionArchivoPruebaSerializado() {
		File file = new File("src/co/edu/unbosque/model/persistance/" + nombre_test_serializado);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	@Order(1)
	/**
	 * prueba unitaria de la funcion escribirSerializado()
	 */
	public void escribirSerializadoTest() {
		inicializacionArchivoPruebaSerializado();
		Object obj = datos_test;
		FileHandler.escribirSerializado(nombre_test_serializado, obj);
		File file = new File("src/co/edu/unbosque/model/persistance/" + nombre_test_serializado);
		cont++;
		try {
			assertTrue(file.exists());
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}

	}

	@Test
	@Order(2)
	/**
	 * prueba unitaria de la funcion leerSerializado()
	 */
	public void leerSerializadoTest() {
		String datos_test_temp = (String) FileHandler.leerSerializado(nombre_test_serializado);
		cont++;
		try {
			assertEquals(datos_test_temp, datos_test);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
		terminacionArchivoPruebaSerializado();
	}

	/**
	 * Inicia el archivo de prueba CSV
	 */
	public void inicializacionArchivoPruebaCSV() {
		File file = new File("src/co/edu/unbosque/model/persistance/" + nombre_test_csv);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * Termina el archivo de prueba CSV
	 */
	public void terminacionArchivoPruebaCSV() {
		File file = new File("src/co/edu/unbosque/model/persistance/" + nombre_test_csv);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	@Order(3)
	/**
	 * prueba unitaria de la funcion escribirArchivo()
	 */
	public void escribirArchivoTest() {
		inicializacionArchivoPruebaCSV();
		String datos = datos_test;
		FileHandler.escribirArchivo(nombre_test_csv, datos);
		File file = new File("src/co/edu/unbosque/model/persistance/" + nombre_test_csv);
		cont++;
		try {
			assertTrue(file.exists());
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}

	}

	@Test
	@Order(4)
	/**
	 * prueba unitaria de la funcion abrirArchivo()
	 */
	public void abrirArchivoTest() {
		String contenido = FileHandler.abrirArchivo(nombre_test_csv);
		cont++;
		try {
			assertEquals(contenido, datos_test + "\n");
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(5)
	/**
	 * prueba unitaria de la funcion cargarDesdeArchivo()
	 */
	public void cargarDesdeArchivoTest() {
		ArrayList<String> desde_archivo = new ArrayList<>();
		String contenido = FileHandler.abrirArchivo(nombre_test_csv);
		String[] lineas = contenido.split("\n");
		for (String linea : lineas) {
			desde_archivo.add(linea);
		}
		cont++;
		try {
			assertEquals(desde_archivo, FileHandler.cargarDesdeArchivo(nombre_test_csv));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
		terminacionArchivoPruebaCSV();
	}

	// GRAFICAS

	@Test
	@Order(6)
	/**
	 * prueba unitaria de la funcion crearGraficaEdad()
	 */
	public void crearGraficaEdadTest() {
		int media = MTC.mediaEdad(lista);
		int mediana = MTC.medianaEdad(lista);
		HashMap<Integer, Integer> mapa_moda = MTC.modaEdad(lista);
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

		cont++;
		try {
			assertEquals(chart, FileHandler.crearGraficaEdad(media, mediana, mapa_moda));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}

	}

	@Test
	@Order(7)
	/**
	 * prueba unitaria de la funcion crearGraficaGenero()
	 */
	public void crearGraficaGeneroTest() {
		HashMap<Character, Integer> mapa_moda = MTC.modaGenero(lista);
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
		cont++;
		try {
			assertEquals(chart, FileHandler.crearGraficaGenero(mapa_moda));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(8)
	/**
	 * prueba unitaria de la funcion crearGraficaPrograma()
	 */
	public void crearGraficaProgramaTest() {
		HashMap<String, Integer> mapa_moda = MTC.modaPrograma(lista);
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
		cont++;
		try {
			assertEquals(chart, FileHandler.crearGraficaPrograma(mapa_moda));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(9)
	/**
	 * prueba unitaria de la funcion crearGraficaActivoInactivo()
	 */
	public void crearGraficaActivoInactivoTest() {
		HashMap<String, Integer> mapa_moda = MTC.modaActivoInactivo(lista);

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
		cont++;
		try {
			assertEquals(chart, FileHandler.crearGraficaActivoInactivo(mapa_moda));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(10)
	/**
	 * prueba unitaria de la funcion crearGraficaJornada()
	 */
	public void crearGraficaJornadaTest() {
		HashMap<String, Integer> mapa_moda = MTC.modaJornada(lista);
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
		cont++;
		try {
			assertEquals(chart, FileHandler.crearGraficaJornada(mapa_moda));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(11)
	/**
	 * prueba unitaria de la funcion crearGraficaNacionalidad()
	 */
	public void crearGraficaNacionalidadTest() {
		HashMap<String, Integer> mapa_moda = MTC.modaNacionalidad(lista);
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
		cont++;
		try {
			assertEquals(chart, FileHandler.crearGraficaNacionalidad(mapa_moda));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(12)
	/**
	 * prueba unitaria de la funcion crearGraficas()
	 */
	public void crearGraficasTest() {
		JFreeChart[] graficas = new JFreeChart[6];
		graficas[0] = FileHandler.crearGraficaEdad(MTC.mediaEdad(lista), MTC.medianaEdad(lista), MTC.modaEdad(lista));
		graficas[1] = FileHandler.crearGraficaGenero(MTC.modaGenero(lista));
		graficas[2] = FileHandler.crearGraficaPrograma(MTC.modaPrograma(lista));
		graficas[3] = FileHandler.crearGraficaJornada(MTC.modaJornada(lista));
		graficas[4] = FileHandler.crearGraficaActivoInactivo(MTC.modaActivoInactivo(lista));
		graficas[5] = FileHandler.crearGraficaNacionalidad(MTC.modaNacionalidad(lista));

		cont++;
		try {
			assertArrayEquals(graficas, FileHandler.crearGraficas(lista));
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

	@Test
	@Order(13)
	/**
	 * prueba unitaria de la funcion crearPdf()
	 */
	public void crearPdfTest() {
		FileHandler.crearPdf("pruebaPDF.pdf", FileHandler.crearGraficas(lista));
		File file = new File("src/co/edu/unbosque/model/persistance/pruebaPDF.pdf");
		cont++;
		try {
			assertTrue(file.exists());
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
		file.delete();
	}

}
