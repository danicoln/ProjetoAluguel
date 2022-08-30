package br.com.alfamidia.exception;

public class ClienteException extends Exception {
	private static final long serialVersionUID = 1L;

	public ClienteException(String mensagem) {
		super(mensagem);
	}
	
}
