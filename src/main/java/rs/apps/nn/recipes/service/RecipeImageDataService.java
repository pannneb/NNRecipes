package rs.apps.nn.recipes.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.RecipeImageData;

public interface RecipeImageDataService extends CrudServiceRecipes<RecipeImageData, Long> {

	public RecipeImageData store(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException;

	public List<RecipeImageData> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType id);

	 // public RecipeImageMap storeAWSS3(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException;

}
