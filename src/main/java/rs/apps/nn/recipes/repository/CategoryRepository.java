package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	Optional<Category> findById(Long id);

	List<Category> findAllByOrderByIdAsc();
	
	Optional<Category> findByDescription(String desc);

	// Optional<Category> findByName(String name);

	List<Category> findAllByDescriptionLike(String desc);

} 
