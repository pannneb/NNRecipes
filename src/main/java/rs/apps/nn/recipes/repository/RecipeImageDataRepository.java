package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.FileData;
import rs.apps.nn.recipes.domain.RecipeImageData;

@Repository
public interface RecipeImageDataRepository extends JpaRepository<RecipeImageData, Long> {

	Optional<RecipeImageData> findById(Long id);

	List<RecipeImageData> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType fileType);

	// List<FileData> findAllByRecipeFkOrderByIdDesc(Long id);

	// List<FileData> findAllByRecipeFkAndFileModeOrderByIdDesc(Long recipeFk, String fileMode);

	// List<FileData> findAllByOrderByIdAsc();

	// Optional<FileData> findByDescription(String desc);

}
