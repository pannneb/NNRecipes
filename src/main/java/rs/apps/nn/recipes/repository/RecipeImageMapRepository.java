package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.RecipeImageMap;

@Repository
public interface RecipeImageMapRepository extends JpaRepository<RecipeImageMap, Long> {

	Optional<RecipeImageMap> findById(Long id);

	List<RecipeImageMap> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType fileType);

}
