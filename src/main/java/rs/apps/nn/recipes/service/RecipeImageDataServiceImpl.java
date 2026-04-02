package rs.apps.nn.recipes.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import rs.apps.nn.recipes.awss3.api.v2.S3Service;
import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.FileData;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.RecipeImageData;
import rs.apps.nn.recipes.repository.RecipeImageDataRepository;
import rs.apps.nn.recipes.repository.RecipeImageMapRepository;

@Service
public class RecipeImageDataServiceImpl implements RecipeImageDataService {

	private RecipeImageDataRepository recipeImageDataRepository;
	// private RecipeImageMapRepository recipeImageMapRepository;

	private FileDataService fileDataService;

	// private S3Service s3Service;

	public RecipeImageDataServiceImpl(RecipeImageDataRepository recipeImageDataRepository,
			FileDataService fileDataservice/*, S3Service s3Service, RecipeImageMapRepository recipeImageMapRepository*/) {
		this.recipeImageDataRepository = recipeImageDataRepository;
		this.fileDataService = fileDataservice;
		// this.s3Service = s3Service;
		// this.recipeImageMapRepository = recipeImageMapRepository;
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

	public RecipeImageData store(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId)
			throws IOException {
		FileData fd = fileDataService.store(file, imageId);

		RecipeImageData recipeImageData = new RecipeImageData();
		recipeImageData.setFileType(enumFileType);
		recipeImageData.setRecipe(recipe);
		recipeImageData.setFileData(fd);
		recipeImageData.setId(fd.getId());

		return recipeImageDataRepository.save(recipeImageData);
	}

//	@Override
//	public RecipeImageMap storeAWSS3(MultipartFile file, EnumFileType enumFileType, Recipe recipe, Long imageId) throws IOException {
//
//		String imgName = s3Service.uploadAWSS3(file);
//
//		// TODO Prepate response recipeImageData:
//		String imgUrl = S3Config.AWS_ACCESS_S3_URL_BASE.concat(imgName);
//		
//		RecipeImageMap recipeImageMap = new RecipeImageMap(recipe);
//		recipeImageMap.setUrl(imgUrl);
//		recipeImageMap.setFileType(enumFileType);
//		//recipeImageMap.setRecipe(recipe);
//		RecipeImageMap a = recipeImageMapRepository.save(recipeImageMap);
//		return a;
////		FileData fd = fileDataService.store(file);
////		RecipeImageData recipeImageData = new RecipeImageData();
////		recipeImageData.setFileType(enumFileType);
////		recipeImageData.setRecipe(recipe);
////		recipeImageData.setFileData(fd);
////		recipeImageData.setId(fd.getId());
////		
////		return recipeImageDataRepository.save(recipeImageData);
//	}

	@Override
	public List<RecipeImageData> findAllByRecipeIdAndFileTypeOrderByIdDesc(Long recipeId, EnumFileType fileType) {
		return recipeImageDataRepository.findAllByRecipeIdAndFileTypeOrderByIdDesc(recipeId, fileType);
	}

}
