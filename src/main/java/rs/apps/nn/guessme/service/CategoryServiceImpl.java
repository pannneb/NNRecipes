package rs.apps.nn.guessme.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import rs.apps.nn.guessme.api.EnumResponseStatus;
import rs.apps.nn.guessme.exception.ValidateException;
import rs.apps.nn.guessme.model.Category;
import rs.apps.nn.guessme.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepository categoryRepository;

	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}
//
//    @GetMapping
//    @RequestMapping("/recipe/{recipeId}/ingredients")
//    public String listIngredients(@PathVariable String recipeId, Model model){
//        log.debug("Getting ingredient list for recipe id: " + recipeId);
//
//        // use command object to avoid lazy load errors in Thymeleaf.
//        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(recipeId)));
//
//        return "recipe/ingredient/list";
//    }

	@Override
	public List<Category> getCategories() {
		return categoryRepository.findAll();
	}

	@Override
	public Category createOrUpdateCategory(Category entity) throws ValidateException {
		// Optional<Word> word = wordRepository.findById(entity.getId());
		Optional<Category> category = categoryRepository.findByName(entity.getName());

		if (category.isPresent()) {
			throw new ValidateException(EnumResponseStatus.RESP_WORD_ALREADY_EXISTS.getId(),
					"msg.err.entityAlreadyExists", new String[] { "Category" });

			// Word newEntity = word.get();
			// // newEntity.setId(entity.getId());
			// newEntity.setWord(entity.getWord());
			// newEntity.setCategoryFk(entity.getCategoryFk());
			//
			// newEntity = wordRepository.save(newEntity);
			//
			// return newEntity;
		} else {
			entity = categoryRepository.save(entity);
			return entity;
		}
	}

}