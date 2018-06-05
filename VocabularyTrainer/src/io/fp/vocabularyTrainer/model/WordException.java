package io.fp.vocabularyTrainer.model;

import java.io.Serializable;

public class WordException extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6966622074606203247L;

	public WordException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public WordException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public WordException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public WordException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
