package co.edu.unbosque.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import co.edu.unbosque.util.exceptions.ApellidosIncompletosException;
import co.edu.unbosque.util.exceptions.CaracteresInvalidosException;
import co.edu.unbosque.util.exceptions.DocumentoMuyLargoException;
import co.edu.unbosque.util.exceptions.FechaErroneaException;
import co.edu.unbosque.util.exceptions.StringVacioException;

/**
 * Clase encargada de todos los avisos y recomendaciones de las excepciones
 * 
 * @author Juan Esteban Quintero, Javier Felipe Meza, Joann Zamudio, Federico
 *         Vargas Rozo
 */
public class ExceptionControl {
	/**
	 * Metodo encargado de la verificacion de la entrada correcta de los datos
	 * ingresados por el usuario, en caso de encontrar una erronea soltara alguna
	 * excepcion propia
	 * 
	 * @param documento String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param nombre    String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param apellido  String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param genero    String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param correo    String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param programa  String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param lugar     String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @param fecha     String que almacena algun dato temporal que ingreso el
	 *                  usuario
	 * @return Booleano que refleja la correcta verificacion o en su defecto el mal
	 *         ingreso de los datos
	 */
	public boolean verificarInfo(String documento, String nombre, String apellido, String genero, String correo,
			String programa, String lugar, String fecha) {

		int contador = 0;
		try {
			String temp = nombre;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.replaceAll("[A-Z,a-z,\s, �,�,�,�,�,�,�,�,�,�,�,�]", "").isEmpty()) {
				throw new CaracteresInvalidosException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Nombre completo");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage()
					+ "\"Nombre completo\", por favor ingrese unicamente letras mayusculas y minusculas de la 'A' a la 'Z' y espacios.");
		}
		try {
			String temp = apellido;

			String[] comp = apellido.split(" ");

			if (comp.length <= 1) {
				throw new ApellidosIncompletosException();
			}

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.replaceAll("[A-Z,a-z,\s, �,�,�,�,�,�,�,�,�,�,�,�]", "").isEmpty()) {
				throw new CaracteresInvalidosException();
			}
			contador++;

		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Apellido completo");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage()
					+ "\"Apellido completo\", por favor ingrese unicamente letras mayusculas y minusculas de la 'A' a la 'Z' y espacios.");
		} catch (ApellidosIncompletosException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}

		try {
			String temp = "" + documento;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.replaceAll("^[0-9]+$", "").isEmpty()) {
				throw new CaracteresInvalidosException();
			} else if ((temp.length() == 10) == false) {
				throw new DocumentoMuyLargoException();
			}
			contador++;

		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Documento de identidad");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null, e2.getMessage()
					+ "\"Documento de identidad\", por favor ingrese unicamente letras mayusculas y minusculas de la 'A' a la 'Z' y espacios.");
		} catch (DocumentoMuyLargoException e3) {
			JOptionPane.showMessageDialog(null, e3.getMessage());
		}

		try {
			String temp = correo;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.matches("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$")) {
				throw new CaracteresInvalidosException();
			}
			contador++;

		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Correo electronico.");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null,
					e2.getMessage() + "\"Correo electronico\", por favor ingrese un correo electronico valido.");
		}

		try {
			String temp = lugar;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Lugar de Nacimiento.");
		}

		try {
			String temp = fecha;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Fecha de Nacimiento");
		}

		try {
			String temp = programa;

			if (temp.equals("Seleccionar")) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Programa Academico");
		}

		try {
			String temp = genero;

			if (temp.equals("Seleccionar")) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Genero");
		}

		if (contador < 8) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Metodo encargado de mostrar un JOptionPane acerca de alguna lista que no
	 * tiene seleccionado un item
	 * 
	 * @param message String que almacena un texto de advertencia
	 */
	public void listaVacia(String message) {

		JOptionPane.showMessageDialog(null,
				message + ", elija alguno de la lista o, en caso de que este vacia, ingrese un elemento nuevo.");
	}

	/**
	 * Metodo que verifica el correcto ingreso de datos en ciertos casos
	 * 
	 * @param usuario String que almacena un atributo temporal ingresado por el
	 *                usuario
	 * @param codigo  String que almacena un atributo temporal ingresado por el
	 *                usuario
	 * @return Booleano que refleja si se ingreso o no bien los datos
	 */
	public boolean verificarTextoActivar(String usuario, String codigo) {

		int contador = 0;

		try {
			String temp = usuario;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Usuario");
		}

		try {
			String temp = codigo;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage() + "Codigo");
		}

		if (contador < 2) {
			return false;
		} else {
			return true;
		}

	}

	/**
	 * Metodo para verificar el correcto ingreso de la fecha y coherencia de la
	 * misma
	 * 
	 * @param fecha String que almacena una fecha ingresada y guardada temporalmente
	 *              que fue ingresada por el usuario
	 * @return Booleano que refleja el correcto ingreso de la fecha o en su defecto
	 *         una excepcion que avise al usuario del error
	 */
	public boolean verificarFecha(String fecha) {
		boolean verificacion = false;
		try {
			String[] dma = fecha.split("/");
			int[] cont = new int[3];
			for (int i = 0; i < dma.length; i++) {
				cont[i] = Integer.parseInt(dma[i]);
			}

			LocalDate fnacimiento = LocalDate.of(cont[2], cont[1], cont[0]);

			int edad = (int) ChronoUnit.YEARS.between(fnacimiento, LocalDate.now());

			if (edad < 15 || edad > 150) {
				verificacion = false;
				throw new FechaErroneaException();
			} else {
				verificacion = true;
			}
		} catch (FechaErroneaException e) {
			JOptionPane.showMessageDialog(null, e.getMessage() + " ,por favor ingrese una fecha de nacimiento valida");
		}
		return verificacion;
	}
}
