package br.com.anteros.oauth2.server.config;

public class OAuth2ServerException extends RuntimeException {

	public OAuth2ServerException() {
		super();
	}

	public OAuth2ServerException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public OAuth2ServerException(String message, Throwable cause) {
		super(message, cause);
	}

	public OAuth2ServerException(String message) {
		super(message);
	}

	public OAuth2ServerException(Throwable cause) {
		super(cause);
	}
	
	

}
