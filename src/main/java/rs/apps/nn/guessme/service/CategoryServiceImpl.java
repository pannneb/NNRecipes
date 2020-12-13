package rs.apps.nn.guessme.service;

import java.util.List;

import org.springframework.stereotype.Service;

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

}
