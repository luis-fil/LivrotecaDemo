package com.runapp.negocio.fachada.exception;

public class TopicoVazioException extends Exception {
 static final long serialVersionUID = -8801096325461676724L;
 public TopicoVazioException() {
	 super("Nao ha topicos cadastrados");
 }
}
