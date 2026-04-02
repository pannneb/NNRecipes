package rs.apps.nn.recipes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.awss3.S3Config;
import rs.apps.nn.recipes.awss3.api.v2.S3Service;
import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.RecipeImageMap;
import rs.apps.nn.recipes.repository.RecipeImageMapRepository;

@Service
public class RecipeImageMapServiceImpl implements RecipeImageMapService {

	private RecipeImageMapRepository recipeImageMapRepository;
	//	private FileDataService fileDataService;
	private S3Service s3Service;
	
	public RecipeImageMapServiceImpl(S3Service s3Service, RecipeImageMapRepository recipeImageMapRepository) {
		this.s3Service = s3Service;
		this.recipeImageMapRepository = recipeImageMapRepository;
	}

	@Override
	public List<RecipeImageMap> findAll() {
		List<RecipeImageMap> files = new ArrayList<>();
		recipeImageMapRepository.findAll().forEach(files::add);
		return files.size() > 0 ? files : null;
	}

	@Override
	public RecipeImageMap findById(Long id) {
		return recipeImageMapRepository.findById(id).orElse(null);
	}

	@Override
	public RecipeImageMap save(RecipeImageMap object) {
		return recipeImageMapRepository.save(object);
	}

	@Override
	public void delete(RecipeImageMap object) {
		recipeImageMapRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		recipeImageMapRepository.deleteById(id);
	}

//	public RecipeImageMap store(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException {
//		FileData fd = fileDataService.store(file, imageId);
//
//		RecipeImageMap RecipeImageMap = new RecipeImageMap();
//		RecipeImageMap.setFileType(enumFileType);
//		RecipeImageMap.setRecipe(recipe);
//		RecipeImageMap.setFileData(fd);
//		RecipeImageMap.setId(fd.getId());
//		
//		return recipeImageMapRepository.save(RecipeImageMap);
//	}

	@Override
	public RecipeImageMap storeAWSS3(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException {

		String imgName = s3Service.uploadAWSS3(file);

		// TODO Prepate response RecipeImageMap:
		String imgUrl = S3Config.AWS_ACCESS_S3_URL_BASE.concat(imgName);
		
		RecipeImageMap recipeImageMap = new RecipeImageMap(recipe);
		recipeImageMap.setUrl(imgUrl);
		recipeImageMap.setFileType(enumFileType);
		//recipeImageMap.setRecipe(recipe);
		RecipeImageMap a = recipeImageMapRepository.save(recipeImageMap);
		return a;
//		FileData fd = fileDataService.store(file);
//		RecipeImageMap RecipeImageMap = new RecipeImageMap();
//		RecipeImageMap.setFileType(enumFileType);
//		RecipeImageMap.setRecipe(recipe);
//		RecipeImageMap.setFileData(fd);
//		RecipeImageMap.setId(fd.getId());
//		
//		return recipeImageMapRepository.save(RecipeImageMap);
	}

	@Override
	public List<RecipeImageMap> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType fileType) {
		return recipeImageMapRepository.findAllByRecipeIdAndFileTypeOrderByIdDesc(recipeId, fileType);
	}


}
