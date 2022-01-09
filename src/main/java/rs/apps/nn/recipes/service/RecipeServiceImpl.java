package rs.apps.nn.recipes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.repository.RecipeRepository;

@Service
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe createOrUpdateRecipe(Recipe entity) throws ValidateException {
//		// Optional<Word> word = wordRepository.findById(entity.getId());
//		Optional<Category> category = categoryRepository.findByName(entity.getName());
//
//		if (category.isPresent()) {
//			throw new ValidateException(EnumResponseStatus.RESP_WORD_ALREADY_EXISTS.getId(),
//					"msg.err.entityAlreadyExists", new String[] { "Category" });
//
//			// Word newEntity = word.get();
//			// // newEntity.setId(entity.getId());
//			// newEntity.setWord(entity.getWord());
//			// newEntity.setCategoryFk(entity.getCategoryFk());
//			//
//			// newEntity = wordRepository.save(newEntity);
//			//
//			// return newEntity;
//		} else {
//			entity = categoryRepository.save(entity);
//			return entity;
//		}
		return null;
	}

	@Override
	public List<Recipe> findAll() {
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAll().forEach(recipes::add);
		return recipes.size() > 0 ? recipes : null;
	}

	@Override
	public List<Recipe> findAllByOrderByIdAsc() {
		List<Recipe> recipes = new ArrayList<>();
		recipeRepository.findAllByOrderByIdAsc().forEach(recipes::add);
		return recipes.size() > 0 ? recipes : null;
	}

	@Override
	public Recipe findById(Long id) {
		return recipeRepository.findById(id).orElse(null);
	}

	@Override
	public Recipe save(Recipe object) {
		return recipeRepository.save(object);
	}

	@Override
	public void delete(Recipe object) {
		recipeRepository.delete(object);
	}

	@Override
	public void deleteById(Long id) {
		recipeRepository.deleteById(id);
	}

	@Override
	public List<Recipe> findAllByDescriptionLikeIgnoreCase(String desc) {
		return recipeRepository.findAllByDescriptionLikeIgnoreCase(desc);
	}

	@Override
	public List<Recipe> findAllByCookTimeGreaterThanEqual(int time1) {
		return recipeRepository.findAllByCookTimeGreaterThanEqual(time1);
	}

	@Override
	public List<Recipe> findAllByCookTimeLessThanEqual(int time1) {
		return recipeRepository.findAllByCookTimeLessThanEqual(time1);
	}

	@Override
	public List<Recipe> findAllByCookTimeGreaterThanEqualAndLessThanEqual(int timeFrom, int timeTo) {
		return recipeRepository.findAllByCookTimeGreaterThanEqualAndCookTimeLessThanEqual(timeFrom, timeTo);
	}

}