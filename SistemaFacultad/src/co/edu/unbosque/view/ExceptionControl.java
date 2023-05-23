package co.edu.unbosque.view;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

import co.edu.unbosque.util.exceptions.CaracteresInvalidosException;
import co.edu.unbosque.util.exceptions.DocumentoMuyLargoException;
import co.edu.unbosque.util.exceptions.FechaErroneaException;
import co.edu.unbosque.util.exceptions.StringVacioException;

public class ExceptionControl {

	public boolean verificarInfo(String documento, String nombre, String apellido, String genero, String correo,
			String programa, String lugar, String fecha) {

		int contador = 0;
		try {
			String temp = nombre;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.replaceAll("[A-Z,a-z,\s, ñ,Ñ,á,Á,é,É,í,Í,ó,Ó,ú,Ú]", "").isEmpty()) {
				throw new CaracteresInvalidosException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Nombre\" no contiene informacion, por favor rellenela para continuar.");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Nombre\" contiene caracteres invalidos, por favor ingrese unicamente letras mayusculas y minusculas de la 'A' a la 'Z' y espacios.");
		}
		try {
			String temp = apellido;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.replaceAll("[A-Z,a-z,\s, ñ,Ñ,á,Á,é,É,í,Í,ó,Ó,ú,Ú]", "").isEmpty()) {
				throw new CaracteresInvalidosException();
			}
			contador++;

		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Apellido\" no contiene informacion, por favor rellenela para continuar.");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Apellido\" contiene caracteres invalidos, por favor ingrese unicamente letras mayusculas y minusculas de la 'A' a la 'Z' y espacios.");
		}

		try {
			String temp = "" + documento;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.replaceAll("^[0-9]+$", "").isEmpty()) {
				throw new CaracteresInvalidosException();
			} else if (temp.length() > 10) {
				throw new DocumentoMuyLargoException();
			}
			contador++;

		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Documento\" no contiene informacion, por favor rellenela para continuar.");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Documento\" contiene caracteres invalidos, por favor ingrese unicamente numeros.");
		} catch (DocumentoMuyLargoException e3) {
			JOptionPane.showMessageDialog(null, "El Documento ingresado no puede tener mas de 10 Digitos");
		}

		try {
			String temp = correo;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			} else if (!temp.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
				throw new CaracteresInvalidosException();
			}
			contador++;

		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Correo electronico\" no contiene informacion, por favor rellenela para continuar.");
		} catch (CaracteresInvalidosException e2) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Correo electronico\" contiene caracteres invalidos, por favor ingrese un correo electronico valido.");
		}

		try {
			String temp = lugar;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Lugar de Nacimiento\" no contiene informacion, por favor seleccione para continuar.");
		}

		try {
			String temp = fecha;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Fecha de nacimiento\" no contiene informacion, por favor seleccione para continuar.");
		}

		try {
			String temp = programa;

			if (temp.equals("Seleccionar")) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Programa\" no contiene informacion, por favor seleccione para continuar.");
		}
		try {
			String temp = genero;

			if (temp.equals("Seleccionar")) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Genero\" no contiene informacion, por favor seleccione para continuar.");
		}

		if (contador < 8) {
			return false;
		} else {
			return true;
		}
	}

	public void listaVacia() {

		JOptionPane.showMessageDialog(null,
				"No se ha seleccionado un elemento valido, elija alguno de la lista o, en caso de que este vacia, ingrese un elemento nuevo.");
	}

	public boolean verificarTextoActivar(String usuario, String codigo) {

		int contador = 0;

		try {
			String temp = usuario;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Usuario\" no contiene informacion, por favor rellenela para continuar.");
		}

		try {
			String temp = codigo;

			if (temp.isEmpty()) {
				throw new StringVacioException();
			}
			contador++;
		} catch (StringVacioException e1) {
			JOptionPane.showMessageDialog(null,
					"La casilla \"Codigo\" no contiene informacion, por favor rellenela para continuar.");
		}

		if (contador < 2) {
			return false;
		} else {
			return true;
		}

	}

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
				System.out.println(edad);
				verificacion = false;
				throw new FechaErroneaException();
			} else {
				verificacion = true;
			}
		} catch (FechaErroneaException e) {
			JOptionPane.showMessageDialog(null, "Por favor ingrese una fecha de nacimiento valida");
		}
		return verificacion;
	}
}
