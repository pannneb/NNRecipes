package rs.apps.nn.recipes.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.domain.EnumUnitOfMeasure;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.UnitOfMeasure;
import rs.apps.nn.recipes.service.CategoryService;
import rs.apps.nn.recipes.service.RecipeService;
import rs.apps.nn.recipes.service.UnitOfMeasureService;

@Slf4j
@Controller
@RequestMapping("comment")
public class CommentController {

	private static final String VIEWS_RECIPE_CREATE_OR_UPDATE_FORM = "recipes/recipeCreateOrUpdateForm";

	private final RecipeService recipeService;
	private final CategoryService categoryService;
	private final UnitOfMeasureService uomService;

	public CommentController(RecipeService recipeService, CategoryService categoryService, UnitOfMeasureService uomService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
		this.uomService = uomService;
		log.debug("CommentController constructor called");
	}
	
}
