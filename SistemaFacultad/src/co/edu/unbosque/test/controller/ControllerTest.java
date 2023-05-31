package co.edu.unbosque.test.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import javax.swing.DefaultListModel;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.controller.Controller;

/**
 * Clase encargada de las pruebas unitarias del controller
 * 
 * @param cont   Int que almacena el contador de pruebas
 * @param passed Int que almacena el contador de pruebas pasadas
 * @param failed Int que almacena el contador de pruebas erroneas
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class ControllerTest {

	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;

	private static Controller c = new Controller();

	@BeforeAll
	/**
	 * Metodo que inicia e introduce
	 */
	public static void inicializacion() {

		System.out.println("Iniciando pruebas unitarias: Controller");

	}

	@AfterAll
	/**
	 * Metodo que finaliza y despide
	 */
	public static void finalizacion() {
		System.out.print("\u001B[0m");
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: " + passed + "/5\n-Fallido: " + failed
				+ "/5\n<----------------------------------->");
	}

	@Test
	/**
	 * prueba unitaria de la funcion agregarLectores()
	 */
	public void agregarLectoresTest() {
		int cuenta_listeners = c.agregarLectores();
		cont++;
		try {
			assertEquals(cuenta_listeners, 28);
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
	/**
	 * prueba unitaria de la funcion filtrarEstudiantes()
	 */
	public void filtrarEstudiantesTest() {
		c.getVp().getAdmincontrol().getFilter().setText("Juan");
		c.filtrarEstudiantes();
		ArrayList<String> estudiantes = new ArrayList<>();
		for (int i = 0; i < c.getModel_temp().size(); i++) {
			estudiantes.add(c.getModel_temp().getElementAt(i));
		}
		boolean contienen_filtro = true;
		for (String estudiante : estudiantes) {
			if (estudiante.contains("Juan") == false) {
				contienen_filtro = false;
			}
		}
		cont++;
		try {
			assertTrue(contienen_filtro);
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
	/**
	 * prueba unitaria de la funcion filtarModelo()
	 */
	public void filtarModeloTest() {
		String texto_filtro = "Carlos";
		c.filtrarModelo(c.getModel_temp(), texto_filtro);
		ArrayList<String> estudiantes = new ArrayList<>();
		for (int i = 0; i < c.getModel_temp().size(); i++) {
			estudiantes.add(c.getModel_temp().getElementAt(i));
		}
		boolean contienen_filtro = true;
		for (String estudiante : estudiantes) {
			if (estudiante.contains("Carlos") == false) {
				contienen_filtro = false;
			}
		}
		cont++;
		try {
			assertTrue(contienen_filtro);
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
	/**
	 * prueba unitaria de la funcion verificarCambios()
	 */
	public void verificarCambiosTest() {
		int listado_pdfs_antes = c.getPdao().getLista_pdfs().size();
		c.setContador_cambios(3);
		c.verificarCambios();
		int listado_pdfs_despues = c.getPdao().getLista_pdfs().size();
		cont++;
		try {
			assertTrue(listado_pdfs_despues > listado_pdfs_antes);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
		c.getPdao().getLista_pdfs().remove(c.getPdao().getLista_pdfs().size() - 1);
		c.getPdao().escribirArchivo();
	}

	@Test
	/**
	 * prueba unitaria de la funcion remodelar()
	 */
	public void remodelarTest() {
		DefaultListModel<String> antes = c.getModel_temp();
		c.remodelar();
		DefaultListModel<String> despues = c.getModel_temp();
		cont++;
		try {
			assertEquals(antes, despues);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
	}

}
