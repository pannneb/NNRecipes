package rs.apps.nn.recipes.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.api.EnumResponseStatus;
import rs.apps.nn.recipes.api.ResponseData;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.model.Category;
import rs.apps.nn.recipes.service.CategoryService;

@Slf4j
@Controller
public class RecipeController {

	private static final String VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM = "recipes/recipeCreateOrUpdateForm";
	//private static final String VIEWS_CATEGORY_DETAILS_FORM = "categories/categoryDetails";

	// private final CategoryService categoryService;

	//	public RecipeController(CategoryService categoryService) {
	//		super();
	//		this.categoryService = categoryService;
	//	}

	public RecipeController() {
		super();
		log.debug("RecipeController constructor called");
	}
	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String categoriesList(Model model) {
		
		List<Recipe> recipes = new ArrayList<>(); // categoryService.findAll();
		Recipe r1 = new Recipe();
		r1.setUrl("www.url1.aaa");
		r1.setSource("internet");
		r1.setId(123L);
		r1.setDescription("Description 1");
//		r1.setSource( 1");
//		
		Recipe r2 = new Recipe();
		r2.setUrl("www.aaaaaaaaa2.aaa");

		recipes.add(r1);
		recipes.add(r2);
		model.addAttribute("recipes", recipes);

		// listOfRecipes.forEach(a->{
		//     a.getCategories().forEach(c->{
		//			System.out.println(c.toString());
		//		});
		// });
		//		
		return "recipes/recipeList";
	}

	//	@RequestMapping(value = { "/","" }, method = RequestMethod.GET)
	//	public String categoriesList(Model model) {
	//		List<Category> categories = categoryService.findAll();
	//		// Set<Recipe> listOfRecipes = recipeService.getRecipes();
	//		model.addAttribute("categories", categories);
	//		// listOfRecipes.forEach(a->{
	//		//     a.getCategories().forEach(c->{
	//		//			System.out.println(c.toString());
	//		//		});
	//		// });
	//		//		
	//		return "categories/categoriesList";
	//	}
	//
	//	@RequestMapping(value = { "/old/","/old" }, method = RequestMethod.GET)
	//	public String oldCategoriesList(Model model) {
	//		List<Category> categories = categoryService.findAll();
	//		model.addAttribute("categories", categories);
	//		return "categories/oldcategoriesList";
	//	}
	//
	//	@RequestMapping(value = { "/adminDefaultPage", "/adminDefaultPage/" }, method = RequestMethod.GET)
	//	public String indexNewDesignAdmin(Model model) {
	//		List<Category> categories = categoryService.findAll();
	//		model.addAttribute("categories", categories);
	//		return "categories/newDesign/indexAdminDefault";
	//	}
	//
	//	@Deprecated
	//	@GetMapping({ "/all/json" })
	//	@ResponseBody
	//	public List<Category> getCategories() {
	//		return categoryService.findAll();
	//	}
	//	
	//	@PostMapping(value = "/newJSON", consumes = "application/json", produces = "application/json")
	//	@ResponseBody
	//	public ResponseData createCategory(@RequestBody Category cat) {
	//		ResponseData rd = new ResponseData();
	//		try {
	//			categoryService.createOrUpdateCategory(cat);
	//			rd.setStatus(EnumResponseStatus.RESP_OK.getId());
	//		} catch (ValidateException e) {
	//			rd.setStatus(e.getValExcCode());
	//			rd.setDescription(e.getValExcDesc());
	//		} catch (Exception e) {
	//			rd.setStatus(EnumResponseStatus.RESP_GENERAL_ERR.getId());
	//			rd.setDescription(e.getMessage());
	//		}
	//		return rd;
	//	}
	//	
	//	//	@GetMapping()
	//	//	public String processFindForm(Category category, BindingResult result, Model model){
	//	//
	//	//		// allow parameterless GET request for /owners to return all records
	//	//		if (category.getLastName() == null) {
	//	//			category.setLastName(""); // empty string signifies broadest possible search
	//	//		}
	//	//
	//	//		// find owners by last name
	//	//		List<Category> results = categoryService.findAllByName("%" + category.getName() + "%");
	//	//
	//	//		if (results.isEmpty()) {
	//	//			// no owners found
	//	//			result.rejectValue("lastName", "notFound", "not found");
	//	//			return "owner/findOwners";
	//	//		}
	//	//		else if (results.size() == 1) {
	//	//			// 1 owner found
	//	//			category = results.get(0);
	//	//			return "redirect:/categories/" + category.getId();
	//	//		}
	//	//		else {
	//	//			// multiple owners found
	//	//			model.addAttribute("selections", results);
	//	//			return "categories/categoriesList";
	//	//		}
	//	//	}
	//	
	//	//	@GetMapping({ "/{categoryId}"})
	//	//	public ModelAndView showOwner(@PathVariable("categoryId") Long categoryId) {
	//	//		log.debug("showOwner", "categoryId:{}", categoryId);
	//	//		ModelAndView mav = new ModelAndView(VIEWS_CATEGORY_DETAILS_FORM);
	//	//		mav.addObject(categoryService.findById(categoryId));
	//	//		return mav;
	//	//	}
	//	
	//
	//	@GetMapping({ "/new","/new/" })
	//	public String initCreationForm(Model model) {
	//		model.addAttribute("category", Category.builder().build());
	//		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	//	}
	//
	//	//	@PostMapping("/new")
	//	//	public String processCreationForm(@Valid Category category, BindingResult result) {
	//	//		if (result.hasErrors()) {
	//	//			return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	//	//		}
	//	//		else {
	//	//			Category c = categoryService.save(category);
	//	//			return "redirect:/categories/" + c.getId();
	//	//		}
	//	//	}
	//
	//	@GetMapping("/{ownerId}/edit")
	//	public String initUpdateOwnerForm(@PathVariable/*("ownerId")*/ Long categoryId, Model model) {
	//		Category c = categoryService.findById(categoryId);
	//		model.addAttribute(c);
	//		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	//	}
	//
	//	// Posto imamo @InitBinder koji onemogucava bindovanje ID-a koristi se rucno postavljanje ID u objektu
	//	@PostMapping("/{ownerId}/edit")
	//	public String processUpdateOwnerForm(@Valid Category category, BindingResult result,
	//			@PathVariable("ownerId") Long ownerId) {
	//		if (result.hasErrors()) {
	//			return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	//		}
	//		else {
	//			category.setId(ownerId);
	//			Category o = categoryService.save(category);
	//			return "redirect:/categories/"+o.getId();
	//		}
	//	}

}
