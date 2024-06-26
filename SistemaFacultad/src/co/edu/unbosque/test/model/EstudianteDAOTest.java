package co.edu.unbosque.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.EstudianteDAO;
import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.model.persistance.FileHandler;

/**
 * Clase encargada de las pruebas unitarias del EstudianteDAO
 * 
 * @param cont   Int que almacena el contador de pruebas
 * @param passed Int que almacena el contador de pruebas pasadas
 * @param failed Int que almacena el contador de pruebas erroneas
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class EstudianteDAOTest {

	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;

	private static EstudianteDAO edao = new EstudianteDAO();

	@BeforeAll
	/**
	 * Metodo que inicia e introduce
	 */
	public static void inicializacion() {
		System.out.println("Iniciando pruebas unitarias: EstudianteDAO");
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
	 * prueba unitaria de la funcion crear()
	 */
	public void crearTest() {
		EstudianteDTO estudiante = new EstudianteDTO(1234567890, "NombreUno NombreDos", "ApellidoUno ApellidoDos", 'M',
				"nnapellidouno", "test@unbosque.edu.co", new Date(), false, "Ingenieria de Sistemas", "Diurno",
				"(Colombia/BOGOTA D.C)", new Date(), "Nacional");
		edao.crear(estudiante);
		EstudianteDTO temp = edao.getLista().get(edao.getLista().size() - 1);
		cont++;
		try {
			assertEquals(estudiante, temp);
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
	 * prueba unitaria de la funcion eliminar()
	 */
	public void eliminarTest() {
		int size_antes = edao.getLista().size();
		edao.eliminar(edao.getLista().size() - 1);
		int size_despues = edao.getLista().size();
		cont++;
		boolean ver = false;
		if (size_antes <= size_despues) {
			ver = true;
		}
		try {
			assertFalse(ver);
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
	 * prueba unitaria de la funcion actualizar()
	 */
	public void actualizarTest() {
		EstudianteDTO aux = edao.getLista().get(0); // admin, admin
		EstudianteDTO estudiante = new EstudianteDTO(1234567890, "NombreUno NombreDos", "ApellidoUno ApellidoDos", 'M',
				"nnapellidouno", "test@unbosque.edu.co", new Date(), false, "Ingenieria de Sistemas", "Diurno",
				"(Colombia/BOGOTA D.C)", new Date(), "Nacional");
		edao.actualizar(0, estudiante);
		EstudianteDTO temp = edao.getLista().get(0);
		cont++;
		try {
			assertEquals(estudiante, temp);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
		edao.actualizar(0, aux);
	}

	@Test
	/**
	 * prueba unitaria de la funcion mostrar()
	 */
	public void mostrarTodoTest() {
		String todo = edao.mostrarTodo();
		StringBuilder sb = new StringBuilder();
		sb.append("Estudiantes: \n");
		for (EstudianteDTO estudiante : edao.getLista()) {
			sb.append(estudiante.toString());
			sb.append("<---------------------------->\n");
		}
		cont++;
		try {
			assertEquals(todo, sb.toString());
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
	 * prueba unitaria de la funcion escribirArchivo()
	 */
	public void escribirArchivoTest() {
		edao.escribirArchivo();
		ArrayList<EstudianteDTO> lista_temp = (ArrayList<EstudianteDTO>) FileHandler.leerSerializado("estudiantes.txt");
		String todo = edao.mostrarTodo();
		StringBuilder sb = new StringBuilder();
		sb.append("Estudiantes: \n");
		for (EstudianteDTO estudiante : lista_temp) {
			sb.append(estudiante.toString());
			sb.append("<---------------------------->\n");
		}

		cont++;
		try {
			assertEquals(todo, sb.toString());
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
