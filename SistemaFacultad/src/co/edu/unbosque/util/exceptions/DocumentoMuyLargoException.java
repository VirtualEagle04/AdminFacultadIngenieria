package co.edu.unbosque.util.exceptions;

public class DocumentoMuyLargoException extends Exception {

	public DocumentoMuyLargoException() {

		super("El documento ingresado debe contener 10 digitos.");
	
	}
}
