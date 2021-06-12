package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	// Optional<Category> findById(Long wordId);

	Optional<Category> findByName(String name);

	List<Category> findAllByNameLike(String name);

} 
