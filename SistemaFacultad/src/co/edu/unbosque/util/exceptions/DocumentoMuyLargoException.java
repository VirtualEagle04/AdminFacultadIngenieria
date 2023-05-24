package co.edu.unbosque.util.exceptions;

public class DocumentoMuyLargoException extends Exception {

	public DocumentoMuyLargoException() {

		super("El documento ingresado no puede contener mas de 10 digitos.");
	
	}
}
