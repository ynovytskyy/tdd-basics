package com.tdd.services.impl;

import com.tdd.domain.Book;
import com.tdd.repositories.BooksRepository;
import com.tdd.services.BooksService;

public class BooksServiceImpl implements BooksService {
	private BooksRepository booksRepository;

	public BooksServiceImpl(BooksRepository booksRepository) {
		this.booksRepository = booksRepository;
	}

	public void add(String bookTitle) {
		// according to the concept of TDD : "red->green->refactor" the following lines of implementation are
		// added only after respectful tests that represent requirements fail

		// 2. BooksServiceImplTest.add_failOnDuplicate() fails -> implement this:
		booksRepository.get(bookTitle).ifPresent((Book b) -> {
			throw new DuplicationException("Book with title [" + bookTitle + "] already exists.");
		});

		// 1. BooksServiceImplTest.add() fails -> implement this:
		booksRepository.put(new Book(bookTitle));
	}
}
