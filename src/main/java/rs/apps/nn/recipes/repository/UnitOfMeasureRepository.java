package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.UnitOfMeasure;

@Repository
public interface UnitOfMeasureRepository extends JpaRepository<UnitOfMeasure, Long> {

	Optional<UnitOfMeasure> findById(Long id);

	List<UnitOfMeasure> findAllByOrderByIdAsc();
	
	Optional<UnitOfMeasure> findByDescription(String desc);

	List<UnitOfMeasure> findAllByDescriptionLike(String desc);

} 
