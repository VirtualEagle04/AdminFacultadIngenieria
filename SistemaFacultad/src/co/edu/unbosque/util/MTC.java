package co.edu.unbosque.util;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import co.edu.unbosque.model.EstudianteDTO;

/**
 * Clase encargada de calcular las medidas de tendencia central con base a la
 * lista de estudiantes suministrada
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class MTC {

	// ARRAYS PARA COMPARAR
	/**
	 * Metodo encargado de crear el array de edades
	 * 
	 * @param lista_estudiantes ArrayList que almacena la lista de estudiantes
	 * @return un array de int que almacena las edades
	 */
	public static int[] crearArrayEdades(ArrayList<EstudianteDTO> lista_estudiantes) {

		int[] arr_edades = new int[lista_estudiantes.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista_estudiantes) {

			LocalDate fecha_nacimiento = estudiante.getFecha_nacimiento().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			LocalDate fecha_actual = LocalDate.now();

			Period periodo = Period.between(fecha_nacimiento, fecha_actual);

			arr_edades[conteo] = periodo.getYears();
			conteo++;
		}

		return arr_edades;
	}

	/**
	 * Metodo encargado de crear el array de generos
	 * 
	 * @param lista_estudiantes ArrayList que almacena la lista de estudiantes
	 * @return un array de char que almacena los generos
	 */
	public static char[] crearArrayGenero(ArrayList<EstudianteDTO> lista_estudiantes) {

		char[] arr_genero = new char[lista_estudiantes.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista_estudiantes) {
			arr_genero[conteo] = estudiante.getGenero();
			conteo++;
		}

		return arr_genero;
	}

	/**
	 * Metodo encargado de crear el array de programas academicos
	 * 
	 * @param lista_estudiantes ArrayList que almacena la lista de estudiantes
	 * @return un array de String que almacena los programas academicos
	 */
	public static String[] crearArrayPrograma(ArrayList<EstudianteDTO> lista_estudiantes) {

		String[] arr_programa = new String[lista_estudiantes.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista_estudiantes) {
			arr_programa[conteo] = estudiante.getPrograma();
			conteo++;
		}

		return arr_programa;
	}

	/**
	 * Metodo encargado de crear el array de activos
	 * 
	 * @param lista_estudiantes ArrayList que almacena la lista de estudiantes
	 * @return un array de String que almacena los activos
	 */
	public static String[] crearArrayActivoInactivo(ArrayList<EstudianteDTO> lista_estudiantes) {

		String[] arr_programa = new String[lista_estudiantes.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista_estudiantes) {
			arr_programa[conteo] = estudiante.isEsta_activo() ? "Activo" : "Inactivo";
			conteo++;
		}

		return arr_programa;
	}

	/**
	 * Metodo encargado de crear el array de jornadas
	 * 
	 * @param lista_estudiantes ArrayList que almacena la lista de estudiantes
	 * @return un array de String que almacena los jornadas
	 */
	public static String[] crearArrayJornada(ArrayList<EstudianteDTO> lista_estudiantes) {

		String[] arr_jornada = new String[lista_estudiantes.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista_estudiantes) {
			arr_jornada[conteo] = estudiante.getJornada();
			conteo++;
		}

		return arr_jornada;
	}
	/**
	 * Metodo encargado de crear el array de nacionalidades
	 * 
	 * @param lista_estudiantes ArrayList que almacena la lista de estudiantes
	 * @return un array de String que almacena los nacionalidades
	 */
	public static String[] crearArrayNacionalidad(ArrayList<EstudianteDTO> lista_estudiantes) {

		String[] arr_nacionalidades = new String[lista_estudiantes.size()];
		int conteo = 0;

		for (EstudianteDTO estudiante : lista_estudiantes) {
			arr_nacionalidades[conteo] = estudiante.getNacional_extranjero();
			conteo++;
		}

		return arr_nacionalidades;
	}

	// NACIONALIDAD
/**
 * Metodo encargado de calcular la moda de los arraylist
 * @param lista_estudiantes ArrayList de los estudiantes
 * @return HashMap que almacena todos los datos
 */
	public static HashMap<String, Integer> modaNacionalidad(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] nacionalidades = crearArrayNacionalidad(lista_estudiantes);

		for (int i = 0; i < nacionalidades.length; i++) {
			String nacional_extranjero = nacionalidades[i];
			if (map.containsKey(nacional_extranjero)) {
				map.put(nacional_extranjero, map.get(nacional_extranjero) + 1);
			} else {
				map.put(nacional_extranjero, 1);
			}
		}
		return map;
	}

	// JORNADA
	/**
	 * Metodo encargado de calcular la moda de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return HashMap que almacena todos los datos
	 */
	public static HashMap<String, Integer> modaJornada(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] jornadas = crearArrayJornada(lista_estudiantes);

		for (int i = 0; i < jornadas.length; i++) {
			String jornada = jornadas[i];
			if (map.containsKey(jornada)) {
				map.put(jornada, map.get(jornada) + 1);
			} else {
				map.put(jornada, 1);
			}
		}
		return map;
	}

	// ESTADO
	/**
	 * Metodo encargado de calcular la moda de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return HashMap que almacena todos los datos
	 */
	public static HashMap<String, Integer> modaActivoInactivo(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] activos_inactivos = crearArrayActivoInactivo(lista_estudiantes);

		for (int i = 0; i < activos_inactivos.length; i++) {
			String activo_inactivo = activos_inactivos[i];
			if (map.containsKey(activo_inactivo)) {
				map.put(activo_inactivo, map.get(activo_inactivo) + 1);
			} else {
				map.put(activo_inactivo, 1);
			}
		}
		return map;
	}

	// PROGRAMA
	/**
	 * Metodo encargado de calcular la moda de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return HashMap que almacena todos los datos
	 */
	public static HashMap<String, Integer> modaPrograma(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] programas = crearArrayPrograma(lista_estudiantes);

		for (int i = 0; i < programas.length; i++) {
			String programa = programas[i];
			if (map.containsKey(programa)) {
				map.put(programa, map.get(programa) + 1);
			} else {
				map.put(programa, 1);
			}
		}
		return map;
	}

	// GENERO
	/**
	 * Metodo encargado de calcular la moda de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return HashMap que almacena todos los datos
	 */
	public static HashMap<Character, Integer> modaGenero(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] generos = crearArrayGenero(lista_estudiantes);

		for (int i = 0; i < generos.length; i++) {
			char genero = generos[i];
			if (map.containsKey(genero)) {
				map.put(genero, map.get(genero) + 1);
			} else {
				map.put(genero, 1);
			}
		}
		return map;
	}

	// EDAD
	/**
	 * Metodo encargado de calcular la moda de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return HashMap que almacena todos los datos
	 */
	public static HashMap<Integer, Integer> modaEdad(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] edades = crearArrayEdades(lista_estudiantes);

		for (int i = 0; i < edades.length; i++) {
			int edad = edades[i];
			if (map.containsKey(edad)) {
				map.put(edad, map.get(edad) + 1);
			} else {
				map.put(edad, 1);
			}
		}
		return map;
	}

	/**
	 * Metodo encargado de calcular la media de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return Int que almacena la media calculada
	 */
	public static int mediaEdad(ArrayList<EstudianteDTO> lista_estudiantes) {

		int[] edades = crearArrayEdades(lista_estudiantes);

		int suma = 0;

		for (int edad : edades) {
			suma += edad;
		}

		return suma / edades.length;

	}
	/**
	 * Metodo encargado de calcular la media de los arraylist
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return Int que almacena la media calculada
	 */
	public static int medianaEdad(ArrayList<EstudianteDTO> lista_estudiantes) {

		int[] edades = crearArrayEdades(lista_estudiantes);

		Arrays.sort(edades);

		int mediana = 0;
		int mitad = edades.length / 2;

		if (mitad % 2 == 0) {
			mediana = (edades[mitad - 1] + edades[mitad]) / 2;
		} else {
			mediana = edades[mitad];
		}

		return mediana;
	}

	// PROGRAMAS POR ESTUDIANTES NACIONALES
	/**
	 * Metodo encargado de calcular el top de estudiantes nacionales
	 * @param lista_estudiantes ArrayList de los estudiantes
	 * @return HashMap que almacena los top nacionales
	 */
	public static HashMap<String, Integer> topNacionales(ArrayList<EstudianteDTO> lista_estudiantes) {
		String[] programas = { "Bioingenieria", "Ingenieria Ambiental", "Ingenieria Electronica",
				"Ingenieria Industrial", "Ingenieria de Sistemas" };
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for (String programa : programas) {
			map.put(programa, 0);
		}

		String programa_temp = "";
		for (EstudianteDTO estudiante : lista_estudiantes) {
			if (estudiante.getNacional_extranjero().equalsIgnoreCase("Nacional")) {

				programa_temp = estudiante.getPrograma();
				if (map.containsKey(programa_temp)) {
					map.put(programa_temp, map.get(programa_temp) + 1);
				}
			}
		}

		return map;
	}

}
