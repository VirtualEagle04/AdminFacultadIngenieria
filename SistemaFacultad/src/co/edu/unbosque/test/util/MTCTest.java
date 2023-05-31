package co.edu.unbosque.test.util;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.model.persistance.FileHandler;
import co.edu.unbosque.util.MTC;

/**
 * Clase encargada de las pruebas unitarias del MTC
 * 
 * @param cont   Int que almacena el contador de pruebas
 * @param passed Int que almacena el contador de pruebas pasadas
 * @param failed Int que almacena el contador de pruebas erroneas
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class MTCTest {

	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;

	private static ArrayList<EstudianteDTO> lista = (ArrayList<EstudianteDTO>) FileHandler
			.leerSerializado("estudiantes.txt");
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	@BeforeAll
	/**
	 * Metodo que inicia e introduce
	 */
	public static void inicializacion() {
		System.out.println("Iniciando pruebas unitarias: MTC");

	}

	@AfterAll
	/**
	 * Metodo que finaliza y despide
	 */
	public static void finalizacion() {
		System.out.print("\u001B[0m");
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: " + passed + "/15\n-Fallido: " + failed
				+ "/15\n<----------------------------------->");
	}

	@Test
	/**
	 * prueba unitaria de la funcion crearArrayEdades()
	 */
	public void crearArrayEdadesTest() {
		int[] arr_edades = new int[lista.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista) {

			LocalDate fecha_nacimiento = estudiante.getFecha_nacimiento().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			LocalDate fecha_actual = LocalDate.now();

			Period periodo = Period.between(fecha_nacimiento, fecha_actual);

			arr_edades[conteo] = periodo.getYears();
			conteo++;
		}
		String arr_edades_string = Arrays.toString(arr_edades);
		String mtc_string = Arrays.toString(MTC.crearArrayEdades(lista));

		cont++;
		try {
			assertEquals(arr_edades_string, mtc_string);
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
	 * prueba unitaria de la funcion crearArrayGenero()
	 */
	public void crearArrayGeneroTest() {
		char[] arr_genero = new char[lista.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista) {
			arr_genero[conteo] = estudiante.getGenero();
			conteo++;
		}
		String arr_genero_string = Arrays.toString(arr_genero);
		String mtc_string = Arrays.toString(MTC.crearArrayGenero(lista));
		cont++;
		try {
			assertEquals(arr_genero_string, mtc_string);
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
	 * prueba unitaria de la funcion crearArrayPrograma()
	 */
	public void crearArrayProgramaTest() {
		String[] arr_programa = new String[lista.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista) {
			arr_programa[conteo] = estudiante.getPrograma();
			conteo++;
		}
		String arr_programa_string = Arrays.toString(arr_programa);
		String mtc_string = Arrays.toString(MTC.crearArrayPrograma(lista));
		cont++;
		try {
			assertEquals(arr_programa_string, mtc_string);
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
	 * prueba unitaria de la funcion crearArrayActivoInactivo()
	 */
	public void crearArrayActivoInactivoTest() {
		String[] arr_estado = new String[lista.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista) {
			arr_estado[conteo] = estudiante.isEsta_activo() ? "Activo" : "Inactivo";
			conteo++;
		}
		String arr_estado_string = Arrays.toString(arr_estado);
		String mtc_string = Arrays.toString(MTC.crearArrayActivoInactivo(lista));
		cont++;
		try {
			assertEquals(arr_estado_string, mtc_string);
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
	 * prueba unitaria de la funcion crearArrayJornadas()
	 */
	public void crearArrayJornadaTest() {
		String[] arr_jornada = new String[lista.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista) {
			arr_jornada[conteo] = estudiante.getJornada();
			conteo++;
		}
		String arr_jornada_string = Arrays.toString(arr_jornada);
		String mtc_string = Arrays.toString(MTC.crearArrayJornada(lista));
		cont++;
		try {
			assertEquals(arr_jornada_string, mtc_string);
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
	 * prueba unitaria de la funcion crearArrayNacionalidad()
	 */
	public void crearArrayNacionalidadTest() {
		String[] arr_nacionalidades = new String[lista.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista) {
			arr_nacionalidades[conteo] = estudiante.getNacional_extranjero();
			conteo++;
		}
		String arr_nacionalidades_string = Arrays.toString(arr_nacionalidades);
		String mtc_string = Arrays.toString(MTC.crearArrayNacionalidad(lista));
		cont++;
		try {
			assertEquals(arr_nacionalidades_string, mtc_string);
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
	 * prueba unitaria de la funcion modaNacionalidad()
	 */
	public void modaNacionalidadTest() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] nacionalidades = MTC.crearArrayNacionalidad(lista);

		for (int i = 0; i < nacionalidades.length; i++) {
			String nacional_extranjero = nacionalidades[i];
			if (map.containsKey(nacional_extranjero)) {
				map.put(nacional_extranjero, map.get(nacional_extranjero) + 1);
			} else {
				map.put(nacional_extranjero, 1);
			}
		}

		cont++;
		try {
			assertEquals(map, MTC.modaNacionalidad(lista));
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
	 * prueba unitaria de la funcion modaJornada()
	 */
	public void modaJornadaTest() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] jornadas = MTC.crearArrayJornada(lista);

		for (int i = 0; i < jornadas.length; i++) {
			String jornada = jornadas[i];
			if (map.containsKey(jornada)) {
				map.put(jornada, map.get(jornada) + 1);
			} else {
				map.put(jornada, 1);
			}
		}
		cont++;
		try {
			assertEquals(map, MTC.modaJornada(lista));
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
	 * prueba unitaria de la funcion modaActivoInactivo()
	 */
	public void modaActivoInactivoTest() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] activos_inactivos = MTC.crearArrayActivoInactivo(lista);

		for (int i = 0; i < activos_inactivos.length; i++) {
			String activo_inactivo = activos_inactivos[i];
			if (map.containsKey(activo_inactivo)) {
				map.put(activo_inactivo, map.get(activo_inactivo) + 1);
			} else {
				map.put(activo_inactivo, 1);
			}
		}
		cont++;
		try {
			assertEquals(map, MTC.modaActivoInactivo(lista));
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
	 * prueba unitaria de la funcion modaPrograma()
	 */
	public void modaProgramaTest() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] programas = MTC.crearArrayPrograma(lista);

		for (int i = 0; i < programas.length; i++) {
			String programa = programas[i];
			if (map.containsKey(programa)) {
				map.put(programa, map.get(programa) + 1);
			} else {
				map.put(programa, 1);
			}
		}
		cont++;
		try {
			assertEquals(map, MTC.modaPrograma(lista));
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
	 * prueba unitaria de la funcion modaGenero()
	 */
	public void modaGeneroTest() {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] generos = MTC.crearArrayGenero(lista);

		for (int i = 0; i < generos.length; i++) {
			char genero = generos[i];
			if (map.containsKey(genero)) {
				map.put(genero, map.get(genero) + 1);
			} else {
				map.put(genero, 1);
			}
		}
		cont++;
		try {
			assertEquals(map, MTC.modaGenero(lista));
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
	 * prueba unitaria de la funcion modaEdad()
	 */
	public void modaEdadTest() {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] edades = MTC.crearArrayEdades(lista);

		for (int i = 0; i < edades.length; i++) {
			int edad = edades[i];
			if (map.containsKey(edad)) {
				map.put(edad, map.get(edad) + 1);
			} else {
				map.put(edad, 1);
			}
		}
		cont++;
		try {
			assertEquals(map, MTC.modaEdad(lista));
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
	 * prueba unitaria de la funcion mediaEdad()
	 */
	public void mediaEdadTest() {
		int[] edades = MTC.crearArrayEdades(lista);

		int suma = 0;

		for (int edad : edades) {
			suma += edad;
		}
		int res = suma / edades.length;
		cont++;
		try {
			assertEquals(res, MTC.mediaEdad(lista));
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
	 * prueba unitaria de la funcion medianaEdad()
	 */
	public void medianaEdadTest() {
		int[] edades = MTC.crearArrayEdades(lista);

		Arrays.sort(edades);

		int mediana = 0;
		int mitad = edades.length / 2;

		if (mitad % 2 == 0) {
			mediana = (edades[mitad - 1] + edades[mitad]) / 2;
		} else {
			mediana = edades[mitad];
		}
		cont++;
		try {
			assertEquals(mediana, MTC.medianaEdad(lista));
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
	 * prueba unitaria de la funcion topNacionales()
	 */
	public void topNacionalesTest() {
		String[] programas = { "Bioingenieria", "Ingenieria Ambiental", "Ingenieria Electronica",
				"Ingenieria Industrial", "Ingenieria de Sistemas" };
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String programa : programas) {
			map.put(programa, 0);
		}

		String programa_temp = "";
		for (EstudianteDTO estudiante : lista) {
			if (estudiante.getNacional_extranjero().equalsIgnoreCase("Nacional")) {

				programa_temp = estudiante.getPrograma();
				if (map.containsKey(programa_temp)) {
					map.put(programa_temp, map.get(programa_temp) + 1);
				}
			}
		}

		cont++;
		try {
			assertEquals(map, MTC.topNacionales(lista));
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
