package rs.apps.nn.recipes.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.controller.RecipeController;
import rs.apps.nn.recipes.domain.Ingredient;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.repository.IngredientRepository;
import rs.apps.nn.recipes.repository.RecipeRepository;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

	private RecipeRepository recipeRepository;
	private IngredientRepository ingredientRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository, IngredientRepository ingredientRepository) {
		this.recipeRepository = recipeRepository;
		this.ingredientRepository = ingredientRepository;
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
		List<Ingredient> lst = ingredientRepository.findAllByRecipeId(object.getId());
		
		lst.stream().forEach(i -> 
		{
			if (!object.getIngredients().contains(i)) {					
				ingredientRepository.delete(i);
			}
		}	
		); 
		
		object.getIngredients().stream().forEach(i -> 
		{
			if (i.getId() == null) {
				i.setRecipe(object);
			}
		}	
		); 
		Recipe r = recipeRepository.save(object);
//		List<Ingredient> lst = ingredientRepository.findAllByRecipeId(object.getId());
//		r.getIngredients().stream().forEach(i -> 
//			{
//				if (i.getId() == null) {
//					i.setRecipe(object);
//					log.info("Recipe.save -> ing:{}, ing.rec.id:{}", i.getIngredientName(),
//						i.getRecipe() == null ? "recipe==null" : i.getRecipe().getId());
//					ingredientRepository.save(i);
//				} else if (lst.contains(i)) {					
//					ingredientRepository.save(i);
//				} else {
//					ingredientRepository.delete(i);
//				}
//			}	
//		); 

		return r;// recipeRepository.getOne(object.getId());

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