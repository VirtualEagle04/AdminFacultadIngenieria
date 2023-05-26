package co.edu.unbosque.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.EstudianteDAO;
import co.edu.unbosque.model.PersistenciaEstudiantesDAO;
import co.edu.unbosque.model.PersistenciaEstudiantesDTO;
import co.edu.unbosque.model.persistance.FileHandler;

public class PersistenciaEstudiantesDAOTest {

	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;
	
	private static PersistenciaEstudiantesDAO pedao = new PersistenciaEstudiantesDAO();
	private static EstudianteDAO edao = new EstudianteDAO();
	
	@BeforeAll
	public static void inicializacion() {
		System.out.println("Iniciando pruebas unitarias: PersistenciaEstudiantesDAO");
	}
	
	@AfterAll
	public static void finalizacion() {
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: "+passed+"/3\n-Fallido: "+failed+"/3");
	}
	
	@Test
	public void crearTest() {
		PersistenciaEstudiantesDTO lista = new PersistenciaEstudiantesDTO(edao.getLista());
		pedao.crear(lista);
		PersistenciaEstudiantesDTO temp = pedao.getLista_pdfs().get(pedao.getLista_pdfs().size()-1);
		cont++;
		try {
			assertEquals(lista, temp);
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
		pedao.getLista_pdfs().remove(pedao.getLista_pdfs().size()-1);
	}
	
	@Test
	public void mostrarPDFsTest() {
		String todo = pedao.mostrarPDFs();
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy - HH:mm:ss");
		sb.append("Lista de PDFs generados: \n");
		for(int i = 0; i < pedao.getLista_pdfs().size(); i++) {
			sb.append(i+") "+sdf.format(pedao.getLista_pdfs().get(i).getFecha_generacion())+"\n");
		}
		cont++;
		try {
			assertEquals(todo, sb.toString());
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void escribirArchivoTest() {
		pedao.escribirArchivo();
		ArrayList<PersistenciaEstudiantesDTO> lista_temp = (ArrayList<PersistenciaEstudiantesDTO>) FileHandler.leerSerializado("serializadoListas.txt");
		String todo = pedao.mostrarPDFs();
		StringBuilder sb = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, MMM d, yyyy - HH:mm:ss");
		sb.append("Lista de PDFs generados: \n");
		for(int i = 0; i < lista_temp.size(); i++) {
			sb.append(i+") "+sdf.format(lista_temp.get(i).getFecha_generacion())+"\n");
		}
		cont++;
		try {
			assertEquals(todo, sb.toString());
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	
	
}
