package co.edu.unbosque.test.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.AdminDAO;
import co.edu.unbosque.model.AdminDTO;
import co.edu.unbosque.model.persistance.FileHandler;

public class AdminDAOTest {
	
	
	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;
	
	private static AdminDAO adao = new AdminDAO();
	
	@BeforeAll
	public static void inicializacion() {
		System.out.println("Iniciando pruebas unitarias: AdminDAO");
	}
	
	@AfterAll
	public static void finalizacion() {
		System.out.print("\u001B[0m");
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: "+passed+"/5\n-Fallido: "+failed+"/5\n<----------------------------------->");
	}
	
	@Test
	public void crearTest() {
		AdminDTO admin = new AdminDTO("admin", "admin");
		adao.crear(admin);
		AdminDTO temp = adao.getLista().get(adao.getLista().size()-1);
		cont++;
		try {
			assertEquals(admin, temp);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}
		
	}
	
	@Test
	public void eliminarTest() {
		int size_antes = adao.getLista().size();
		adao.eliminar(adao.getLista().size()-1);
		int size_despues = adao.getLista().size();
		cont++;
		boolean ver = false;
		if(size_antes <= size_despues) {
			ver = true;
		}
		try {
			assertFalse(ver);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void actualizarTest() {
		AdminDTO aux = adao.getLista().get(0); //admin, admin
		AdminDTO admin = new AdminDTO("adminTest", "adminTest");
		adao.actualizar(0, admin);
		AdminDTO temp = adao.getLista().get(0);
		cont++;
		try {
			assertEquals(admin, temp);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}
		adao.actualizar(0, aux);
	}
	
	@Test
	public void mostrarTodoTest() {
		String todo = adao.mostrarTodo();
		String test = "Administradores: \n"+adao.getLista().get(0).toString()+"<---------------------------->\n";
		cont++;
		try {
			assertEquals(todo, test);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}
		
	}
	
	@Test
	public void escribirArchivoTest() {
		adao.escribirArchivo();
		ArrayList<AdminDTO> lista_temp = (ArrayList<AdminDTO>) FileHandler.leerSerializado("admins.txt");
		String todo = adao.mostrarTodo();
		String test = "Administradores: \n"+lista_temp.get(0).toString()+"<---------------------------->\n";
		
		cont++;
		try {
			assertEquals(todo, test);
			passed++;
			System.out.print("\u001B[32m");
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.print("\u001B[31m");
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	

}
