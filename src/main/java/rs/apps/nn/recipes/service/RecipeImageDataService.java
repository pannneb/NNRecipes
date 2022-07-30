package rs.apps.nn.recipes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.RecipeImageData;

public interface RecipeImageDataService extends CrudServiceRecipes<RecipeImageData, Long> {

	// List<UnitOfMeasure> findAllByOrderByIdAsc();
	// public RecipeImageData store(MultipartFile file, Long recipeId, Long imageId) throws IOException;

	public RecipeImageData store(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException;
	// public List<RecipeImageData> findAllByRecipeFkOrderByIdDesc(Long recipeId);
	public List<RecipeImageData> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType id);
	
//	public List<FileData> findAllByRecipeFkOrderByIdDesc(Long id);
//	public List<FileData> findAllByRecipeFkAndFileModeOrderByIdDesc(Long id, String fileMode);

}
