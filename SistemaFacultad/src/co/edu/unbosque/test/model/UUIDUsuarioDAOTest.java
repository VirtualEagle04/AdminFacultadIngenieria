package co.edu.unbosque.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.UUIDUsuarioDAO;
import co.edu.unbosque.model.UUIDUsuarioDTO;
import co.edu.unbosque.model.persistance.FileHandler;

/**
 * Clase encargada de las pruebas unitarias del UUIDUsuarioDAO
 * 
 * @param cont   Int que almacena el contador de pruebas
 * @param passed Int que almacena el contador de pruebas pasadas
 * @param failed Int que almacena el contador de pruebas erroneas
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class UUIDUsuarioDAOTest {

	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;

	private static UUIDUsuarioDAO udao = new UUIDUsuarioDAO();

	@BeforeAll
	/**
	 * Metodo que inicia e introduce
	 */
	public static void inicializacion() {
		System.out.println("Iniciando pruebas unitarias: UUIDUsuarioDAO");
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
		UUIDUsuarioDTO usuarioCodigo = new UUIDUsuarioDTO("fvargasr", "f3937bc0d6");
		udao.crear(usuarioCodigo);
		UUIDUsuarioDTO temp = udao.getLista().get(udao.getLista().size() - 1);
		cont++;
		try {
			assertEquals(usuarioCodigo, temp);
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
		int size_antes = udao.getLista().size();
		udao.eliminar(udao.getLista().size() - 1);
		int size_despues = udao.getLista().size();
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
		UUIDUsuarioDTO usuarioC = new UUIDUsuarioDTO("test", "abcdef6h1i");
		udao.crear(usuarioC);
		UUIDUsuarioDTO aux = udao.getLista().get(0);
		UUIDUsuarioDTO usuarioCodigo = new UUIDUsuarioDTO("fvargasr", "f3937bc0d6");
		udao.actualizar(0, usuarioCodigo);
		UUIDUsuarioDTO temp = udao.getLista().get(0);
		cont++;
		try {
			assertEquals(usuarioCodigo, temp);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test " + cont + " pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test " + cont + " fallido.");
		}
		udao.eliminar(udao.getLista().size() - 1);

	}

	@Test
	/**
	 * prueba unitaria de la funcion mostrar()
	 */
	public void mostrarTodoTest() {
		String todo = udao.mostrarTodo();
		StringBuilder sb = new StringBuilder();
		sb.append("UUIDs y Usuarios: \n");
		for (UUIDUsuarioDTO uuid_usuario : udao.getLista()) {
			sb.append(uuid_usuario.toString());
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
		udao.escribirArchivo();
		ArrayList<UUIDUsuarioDTO> lista_temp = (ArrayList<UUIDUsuarioDTO>) FileHandler
				.leerSerializado("UUIDusuario.txt");
		String todo = udao.mostrarTodo();
		StringBuilder sb = new StringBuilder();
		sb.append("UUIDs y Usuarios: \n");
		for (UUIDUsuarioDTO uuid_usuario : udao.getLista()) {
			sb.append(uuid_usuario.toString());
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
