package com.douzone.jblog.exception;

public class CustomFileUploadException  extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public CustomFileUploadException() {
		super("FileUploadException Occurs");
	}

	public CustomFileUploadException(String message) {
		super(message);
	}
}
