package co.edu.unbosque.model.persistance;

import java.awt.Graphics;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

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
		// Rutas relativas: Dependen desde el archivo actual.
		// Rutas absolutas: Empiezan desde el origen del disco.
	}

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

	public static String abrirArchivoText(String nombre_archivo) {

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
}
