package com.tdd.services;

public interface BooksService {
	void add(String bookTitle);

	class DuplicationException extends RuntimeException {
		public DuplicationException(String message) {
			super(message);
		}
	}
}
