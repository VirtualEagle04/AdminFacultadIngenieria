package co.edu.unbosque.util;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import co.edu.unbosque.model.EstudianteDTO;

public class MTC {
	
	
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
//		int moda = 0;
//		int mayor = 0;
//		for(HashMap.Entry<Integer, Integer> parejas : map.entrySet()) {
//			if(parejas.getValue() > mayor) {
//				mayor = parejas.getValue();
//				moda = parejas.getKey();
//			}
//		}
		
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
	
	
	
	
}
