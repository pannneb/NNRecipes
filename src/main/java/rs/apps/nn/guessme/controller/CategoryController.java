package rs.apps.nn.guessme.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.apps.nn.guessme.model.Category;
import rs.apps.nn.guessme.service.CategoryService;

@Controller
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

    @RequestMapping({"/categories", "/categories/"})
    @ResponseBody
	public List<Category> getCategories() {
		return categoryService.getCategories();
	}

}
