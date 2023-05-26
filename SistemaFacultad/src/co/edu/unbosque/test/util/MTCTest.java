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

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import co.edu.unbosque.model.EstudianteDTO;
import co.edu.unbosque.model.persistance.FileHandler;
import co.edu.unbosque.util.MTC;

public class MTCTest {
	
	private static int cont = 0;
	private static int passed = 0;
	private static int failed = 0;
	
	private static ArrayList<EstudianteDTO> lista;
	
	public MTCTest() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		lista = (ArrayList<EstudianteDTO>) FileHandler.leerSerializado("estudiantes.txt");
	}
	
	
	@BeforeAll
	public static void inicializacion() throws ParseException {
		System.out.println("Iniciando pruebas unitarias: MTC");
		
	}
	@AfterAll
	public static void finalizacion() {
		System.out.println("Fin de las pruebas unitarias:\n-Pasado: "+passed+"/15\n-Fallido: "+failed+"/15");
	}
	
	@Test
	public void crearArrayEdadesTest() {
		int[] arr_edades = new int[lista.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista) {
			
			LocalDate fecha_nacimiento = estudiante.getFecha_nacimiento().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
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
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void crearArrayGeneroTest() {
		char[] arr_genero = new char[lista.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista) {
			arr_genero[conteo] = estudiante.getGenero();
			conteo++;
		}
		String arr_genero_string = Arrays.toString(arr_genero);
		String mtc_string = Arrays.toString(MTC.crearArrayGenero(lista));
		cont++;
		try {
			assertEquals(arr_genero_string, mtc_string);
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void crearArrayProgramaTest() {
		String[] arr_programa = new String[lista.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista) {
			arr_programa[conteo] = estudiante.getPrograma();
			conteo++;
		}
		String arr_programa_string = Arrays.toString(arr_programa);
		String mtc_string = Arrays.toString(MTC.crearArrayPrograma(lista));
		cont++;
		try {
			assertEquals(arr_programa_string, mtc_string);
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void crearArrayActivoInactivoTest() {
		String[] arr_estado = new String[lista.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista) {
			arr_estado[conteo] = estudiante.isEsta_activo()? "Activo":"Inactivo";
			conteo++;
		}
		String arr_estado_string = Arrays.toString(arr_estado);
		String mtc_string = Arrays.toString(MTC.crearArrayActivoInactivo(lista));
		cont++;
		try {
			assertEquals(arr_estado_string, mtc_string);
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void crearArrayJornadaTest() {
		String[] arr_jornada = new String[lista.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista) {
			arr_jornada[conteo] = estudiante.getJornada();
			conteo++;
		}
		String arr_jornada_string = Arrays.toString(arr_jornada);
		String mtc_string = Arrays.toString(MTC.crearArrayJornada(lista));
		cont++;
		try {
			assertEquals(arr_jornada_string, mtc_string);
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	
	@Test
	public void crearArrayNacionalidadTest() {
		String[] arr_nacionalidades = new String[lista.size()];
		int conteo = 0;
		
		for(EstudianteDTO estudiante : lista) {
			arr_nacionalidades[conteo] = estudiante.getNacional_extranjero();
			conteo++;
		}
		String arr_nacionalidades_string = Arrays.toString(arr_nacionalidades);
		String mtc_string = Arrays.toString(MTC.crearArrayNacionalidad(lista));
		cont++;
		try {
			assertEquals(arr_nacionalidades_string, mtc_string);
			passed++;
			System.out.println("Test "+cont+" pasado.");
		} catch (AssertionError e) {
			failed++;
			System.out.println("Test "+cont+" fallido.");
		}
	}
	

	
}
