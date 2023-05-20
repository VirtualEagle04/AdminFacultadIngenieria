package co.edu.unbosque.util;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import co.edu.unbosque.model.EstudianteDTO;

public class MTC {
	
	//ARRAYS PARA COMPARAR
	public static int[] crearArrayEdades(ArrayList<EstudianteDTO> lista_estudiantes){
		
		int[] arr_edades = new int[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			
			LocalDate fecha_nacimiento = estudiante.getFecha_nacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			LocalDate fecha_actual = LocalDate.now();
			
			
			Period periodo = Period.between(fecha_nacimiento, fecha_actual);
			
			arr_edades[conteo] = periodo.getYears();
			conteo++;
		}
		
		return arr_edades;
	}
	public static char[] crearArrayGenero(ArrayList<EstudianteDTO> lista_estudiantes){
		
		char[] arr_genero = new char[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			arr_genero[conteo] = estudiante.getGenero();
			conteo++;
		}
		
		return arr_genero;
	}
	public static String[] crearArrayPrograma(ArrayList<EstudianteDTO> lista_estudiantes){
		
		String[] arr_programa = new String[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			arr_programa[conteo] = estudiante.getPrograma();
			conteo++;
		}
		
		return arr_programa;
	}
	public static String[] crearArrayActivoInactivo(ArrayList<EstudianteDTO> lista_estudiantes){
		
		String[] arr_programa = new String[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			arr_programa[conteo] = estudiante.isEsta_activo()? "Activo":"Inactivo";
			conteo++;
		}
		
		return arr_programa;
	}
	public static String[] crearArrayJornada(ArrayList<EstudianteDTO> lista_estudiantes){
		
		String[] arr_jornada = new String[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			arr_jornada[conteo] = estudiante.getJornada();
			conteo++;
		}
		
		return arr_jornada;
	}
	public static String[] crearArrayOrigen(ArrayList<EstudianteDTO> lista_estudiantes){
		
		String[] arr_nacionalidades = new String[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			arr_nacionalidades[conteo] = estudiante.getLugar_nacimiento();
			conteo++;
		}
		
		return arr_nacionalidades;
	}	
	public static String[] crearArrayNacionalidad(ArrayList<EstudianteDTO> lista_estudiantes){
		
		String[] arr_nacionalidades = new String[lista_estudiantes.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista_estudiantes) {
			arr_nacionalidades[conteo] = estudiante.getNacional_extranjero();
			conteo++;
		}
		
		return arr_nacionalidades;
	}	
	//NACIONALIDAD
	public static HashMap<String, Integer> modaNacionalidad(ArrayList<EstudianteDTO> lista_estudiantes){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] nacionalidades = crearArrayNacionalidad(lista_estudiantes);
		
		for(int i = 0; i < nacionalidades.length; i++) {
			String nacional_extranjero = nacionalidades[i];
			if(map.containsKey(nacional_extranjero)) {
				map.put(nacional_extranjero, map.get(nacional_extranjero) + 1);
			}
			else {
				map.put(nacional_extranjero, 1);
			}
		}
		return map;
	}
	//PAIS DE ORIGEN (NO SE UTILIZA PORQUE SON MUCHOS PAISES. SI SE LIMITA SE PUEDE USAR)
	public static HashMap<String, Integer> modaOrigen(ArrayList<EstudianteDTO> lista_estudiantes){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] nacionalidades = crearArrayOrigen(lista_estudiantes);
		String[] nacionales_internacionales = new String [nacionalidades.length];
		
		for(int i = 0; i < nacionalidades.length; i++) {
			if(nacionalidades[i].toLowerCase().contains("colombia")) {
				nacionales_internacionales[i] = "Colombia";
			}else {
				nacionales_internacionales[i] = nacionalidades[i];
			}
		}
		
		for(int i = 0; i < nacionales_internacionales.length; i++) {
			String nacional_extranjero = nacionales_internacionales[i];
			if(map.containsKey(nacional_extranjero)) {
				map.put(nacional_extranjero, map.get(nacional_extranjero) + 1);
			}
			else {
				map.put(nacional_extranjero, 1);
			}
		}
		return map;
	}
	//JORNADA
	public static HashMap<String, Integer> modaJornada(ArrayList<EstudianteDTO> lista_estudiantes){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] jornadas = crearArrayJornada(lista_estudiantes);
		
		for(int i = 0; i < jornadas.length; i++) {
			String jornada = jornadas[i];
			if(map.containsKey(jornada)) {
				map.put(jornada, map.get(jornada) + 1);
			}
			else {
				map.put(jornada, 1);
			}
		}
		return map;
	}
	//ESTADO
	public static HashMap<String, Integer> modaActivoInactivo(ArrayList<EstudianteDTO> lista_estudiantes){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] activos_inactivos = crearArrayActivoInactivo(lista_estudiantes);
		
		for(int i = 0; i < activos_inactivos.length; i++) {
			String activo_inactivo = activos_inactivos[i];
			if(map.containsKey(activo_inactivo)) {
				map.put(activo_inactivo, map.get(activo_inactivo) + 1);
			}
			else {
				map.put(activo_inactivo, 1);
			}
		}
		return map;
	}
	//PROGRAMA
	public static HashMap<String, Integer> modaPrograma(ArrayList<EstudianteDTO> lista_estudiantes){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		String[] programas = crearArrayPrograma(lista_estudiantes);
		
		for(int i = 0; i < programas.length; i++) {
			String programa = programas[i];
			if(map.containsKey(programa)) {
				map.put(programa, map.get(programa) + 1);
			}
			else {
				map.put(programa, 1);
			}
		}
		return map;
	}
	//GENERO
	public static HashMap<Character, Integer> modaGenero(ArrayList<EstudianteDTO> lista_estudiantes){
		HashMap<Character, Integer> map = new HashMap<Character, Integer>();
		char[] generos = crearArrayGenero(lista_estudiantes);
		
		for(int i = 0; i < generos.length; i++) {
			char genero = generos[i];
			if(map.containsKey(genero)) {
				map.put(genero, map.get(genero) + 1);
			}
			else {
				map.put(genero, 1);
			}
		}
		return map;
	}
	//EDAD
	public static HashMap<Integer, Integer> modaEdad(ArrayList<EstudianteDTO> lista_estudiantes) {
		HashMap<Integer, Integer> map = new HashMap<>();
		int[] edades = crearArrayEdades(lista_estudiantes);
		
		for(int i = 0; i < edades.length; i++) {
			int edad = edades[i];
			if(map.containsKey(edad)) {
				map.put(edad, map.get(edad) + 1);
			}
			else {
				map.put(edad, 1);
			}
		}
		return map;
	}
	public static int mediaEdad(ArrayList<EstudianteDTO> lista_estudiantes) {
		
		int[] edades = crearArrayEdades(lista_estudiantes);
		
		int suma = 0;
		
		for(int edad : edades) {
			suma += edad;
		}
		
		return suma/edades.length;
		
	}
	public static int medianaEdad(ArrayList<EstudianteDTO> lista_estudiantes) {
		
		int[] edades = crearArrayEdades(lista_estudiantes);
		
		Arrays.sort(edades);
		
		int mediana = 0;
		int mitad = edades.length / 2;
		
		if(mitad % 2 == 0) {
			mediana = (edades[mitad -1] + edades[mitad]) / 2;
		}
		else {
			mediana = edades[mitad];
		}
		
		return mediana;
	}
	//PROGRAMAS POR ESTUDIANTES NACIONALES
	public static HashMap<String, Integer> topNacionales(ArrayList<EstudianteDTO> lista_estudiantes){
		String[] programas = {"Bioingenieria", "Ingenieria Ambiental", "Ingenieria Electronica", "Ingenieria Industrial", "Ingenieria de Sistemas" };
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		for(String programa : programas) {
			map.put(programa, 0);
		}
		
		String programa_temp = "";
		for(EstudianteDTO estudiante : lista_estudiantes) {
			if(estudiante.getNacional_extranjero().equalsIgnoreCase("Nacional")) {
				
				programa_temp = estudiante.getPrograma();
				if(map.containsKey(programa_temp)) {
					map.put(programa_temp, map.get(programa_temp) + 1);
				}
			}
		}
		
		return map;
	}
	
	
	
	
}
