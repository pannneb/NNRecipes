package rs.apps.nn.guessme.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.guessme.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findById(Long wordId);

	Optional<Category> findByName(String name);

}
