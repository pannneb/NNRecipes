package rs.apps.nn.recipes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.FileData;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.RecipeImageData;
import rs.apps.nn.recipes.repository.RecipeImageDataRepository;

@Service
public class RecipeImageDataServiceImpl implements RecipeImageDataService {

	private RecipeImageDataRepository recipeImageDataRepository;
	private FileDataService fileDataService;

	public RecipeImageDataServiceImpl(RecipeImageDataRepository recipeImageDataRepository, FileDataService fileDataservice) {
		this.recipeImageDataRepository = recipeImageDataRepository;
		this.fileDataService = fileDataservice;
	}

	@Override
	public List<RecipeImageData> findAll() {
		List<RecipeImageData> files = new ArrayList<>();
		recipeImageDataRepository.findAll().forEach(files::add);
		return files.size() > 0 ? files : null;
	}

	@Override
	public RecipeImageData findById(Long id) {
		return recipeImageDataRepository.findById(id).orElse(null);
	}

	@Override
	public RecipeImageData save(RecipeImageData object) {
		return recipeImageDataRepository.save(object);
	}

	@Override
	public void delete(RecipeImageData object) {
		recipeImageDataRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		recipeImageDataRepository.deleteById(id);
	}

	public RecipeImageData store(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException {
		FileData fd = fileDataService.store(file, imageId);
		RecipeImageData recipeImageData = new RecipeImageData();
		recipeImageData.setFileType(enumFileType);
		recipeImageData.setRecipe(recipe);
		recipeImageData.setFileData(fd);
		recipeImageData.setId(fd.getId());
		
		// RecipeImageData recipeImageData = new RecipeImageData();
		/// recipeImageData.setId(imageId);
		return recipeImageDataRepository.save(recipeImageData);
	}

	@Override
	public List<RecipeImageData> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType fileType) {
		return recipeImageDataRepository.findAllByRecipeIdAndFileTypeOrderByIdDesc(recipeId, fileType);
	}

//	@Override
//	public List<RecipeImageData> findAllByRecipeFkOrderByIdDesc(Long id) {
//	    // return recipeImageDataRepository.findAllByRecipeFkOrderByIdDesc(id);
//		return null;
//	}
//
//	@Override
//	public List<RecipeImageData> findAllByRecipeFkAndFileModeOrderByIdDesc(Long id, String fileMode) {
//	    //return recipeImageDataRepository.findAllByRecipeFkAndFileModeOrderByIdDesc(id, fileMode);
//		return null;
//	}
	
//	@Override
//	public List<recipeImageDataRepository> findAllByOrderByIdAsc() {
//		return recipeImageDataRepository.findAllByOrderByIdAsc();
//	}

}
