package com.tdd.services;

import com.tdd.domain.Book;
import com.tdd.repositories.BooksRepository;
import com.tdd.services.impl.BooksServiceImpl;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.HashMap;
import java.util.Optional;

import static org.junit.Assert.*;

public class BooksServiceImplTest {

	@Test
	public void add() {
		// example of using a Testing Fake
		BooksRepository fakeBooksRepository = new BooksRepository() {
			private HashMap<String, Book> store = new HashMap<>();
			public <S extends Book> S put(S entity) {
				store.put(entity.getTitle(), entity);
				return entity;
			}

			public Optional<Book> get(String id) {
				return Optional.ofNullable(store.get(id));
			}

			public Collection<Book> get() {
				return store.values();
			}

			public Optional<Book> delete(String id) {
				return Optional.ofNullable(store.remove(id));
			}
		};
		BooksService booksService = new BooksServiceImpl(fakeBooksRepository);
		booksService.add("White Fang");

		assertEquals(1, fakeBooksRepository.get().size());
		assertEquals("White Fang", fakeBooksRepository.get().iterator().next().getTitle());
	}

	@Test
	public void add_failOnDuplicate() {
		// example of using a Testing Stub using Mockito
		BooksRepository stubBooksRepositoryWithPreexistingBook = Mockito.mock(BooksRepository.class);
		Book preexistingBook = new Book("White Fang");
		Mockito.when(stubBooksRepositoryWithPreexistingBook.get(preexistingBook.getTitle()))
				.thenReturn(Optional.of(preexistingBook));

		BooksServiceImpl booksServiceImpl = new BooksServiceImpl(stubBooksRepositoryWithPreexistingBook);
		try {
			booksServiceImpl.add("White Fang");
			fail("should fail when adding same book twice");
		} catch (Exception e) {
			//expected to fail
		}
	}

}