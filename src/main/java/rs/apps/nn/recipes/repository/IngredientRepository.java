package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.domain.Ingredient;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

	Optional<Ingredient> findById(Long id);

	List<Ingredient> findAllByRecipeId(Long id);

}
