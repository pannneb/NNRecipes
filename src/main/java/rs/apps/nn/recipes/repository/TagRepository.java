package rs.apps.nn.recipes.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {

	Optional<Tag> findById(Long id);

	Optional<Tag> findByValue(String value);

	// List<Tag> findAllByOrderByIdAsc();

	// Optional<Category> findByDescription(String desc);

	// Optional<Category> findByName(String name);

	// List<Category> findAllByDescriptionLike(String desc);

}
