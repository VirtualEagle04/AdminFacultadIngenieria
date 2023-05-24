package co.edu.unbosque.util.exceptions;

public class FechaErroneaException extends Exception {

	public FechaErroneaException() {
		super("La fecha ingresada no es valida");
	}

}
