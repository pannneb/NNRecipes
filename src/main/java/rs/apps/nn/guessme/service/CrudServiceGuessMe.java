package rs.apps.nn.guessme.service;

import java.util.List;
import java.util.Set;

public interface CrudServiceGuessMe<T, ID> {

	List<T> findAll();

	T findById(ID id);

	T save(T object);

	void delete(T object);

	void deleteById(ID id);

}
