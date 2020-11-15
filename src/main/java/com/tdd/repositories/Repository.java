package com.tdd.repositories;

import java.util.Collection;
import java.util.Optional;

public interface Repository<T, ID> {
	<S extends T> S put(S entity);

	Optional<T> get(ID id);

	Collection<T> get();

	Optional<T> delete(String id);
}
