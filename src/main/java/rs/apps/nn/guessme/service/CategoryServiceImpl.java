package rs.apps.nn.guessme.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

//	@Override
//	public List<Category> getCategories() {
//		return categoryRepository.findAll();
//	}

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

	@Override
	public List<Category> findAllByNameLike(String name) {
		return categoryRepository.findAllByNameLike(name);
	}

	@Override
	public List<Category> findAll() {
		List<Category> categories = new ArrayList<>();
		categoryRepository.findAll().forEach(categories::add);
		return categories.size()>0?categories:null;
	}

	@Override
	public Category findById(Long id) {
		return categoryRepository.findById(id).orElse(null);
	}

	@Override
	public Category save(Category object) {
		return categoryRepository.save(object);
	}

	@Override
	public void delete(Category object) {
		categoryRepository.delete(object);		
	}

	@Override
	public void deleteById(Long id) {
		categoryRepository.deleteById(id);
	}

}