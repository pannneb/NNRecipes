package rs.apps.nn.guessme.controller;

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
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.guessme.api.EnumResponseStatus;
import rs.apps.nn.guessme.api.ResponseData;
import rs.apps.nn.guessme.exception.ValidateException;
import rs.apps.nn.guessme.model.Category;
import rs.apps.nn.guessme.service.CategoryService;

@Slf4j
@Controller
@RequestMapping(path = "/categories")
public class CategoryController {

	private static final String VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM = "category/createOrUpdateCategoryForm";
	private static final String VIEWS_CATEGORY_DETAILS_FORM = "category/categoryDetails";

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}


	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String indexGuessMePage(Model model) {
		List<Category> categories = categoryService.findAll();
//		Set<Recipe> listOfRecipes = recipeService.getRecipes();
		
		model.addAttribute("categories", categories);
//
//		listOfRecipes.forEach(a->{
//			a.getCategories().forEach(c->{
//				System.out.println(c.toString());
//			});
//		});
//		
		return "categories/categoriesList";
	}
	
	@Deprecated
	@GetMapping({ "/all/json" })
	@ResponseBody
	public List<Category> getCategories() {
		return categoryService.findAll();
	}

	@PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
	@ResponseBody
	public ResponseData createCategory(@RequestBody Category cat) {
		ResponseData rd = new ResponseData();
		try {
			categoryService.createOrUpdateCategory(cat);
			rd.setStatus(EnumResponseStatus.RESP_OK.getId());
		} catch (ValidateException e) {
			rd.setStatus(e.getValExcCode());
			rd.setDescription(e.getValExcDesc());
		} catch (Exception e) {
			rd.setStatus(EnumResponseStatus.RESP_GENERAL_ERR.getId());
			rd.setDescription(e.getMessage());
		}
		return rd;
	}
	
	
	
	
	
	

//	@GetMapping()
//	public String processFindForm(Category category, BindingResult result, Model model){
//
//		// allow parameterless GET request for /owners to return all records
//		if (category.getLastName() == null) {
//			category.setLastName(""); // empty string signifies broadest possible search
//		}
//
//		// find owners by last name
//		List<Category> results = categoryService.findAllByName("%" + category.getName() + "%");
//
//		if (results.isEmpty()) {
//			// no owners found
//			result.rejectValue("lastName", "notFound", "not found");
//			return "owner/findOwners";
//		}
//		else if (results.size() == 1) {
//			// 1 owner found
//			category = results.get(0);
//			return "redirect:/categories/" + category.getId();
//		}
//		else {
//			// multiple owners found
//			model.addAttribute("selections", results);
//			return "categories/categoriesList";
//		}
//	}

	@GetMapping({ "/{categoryId}"})
	public ModelAndView showOwner(@PathVariable("categoryId") Long categoryId) {
		log.debug("showOwner", "categoryId:{}", categoryId);
		ModelAndView mav = new ModelAndView(VIEWS_CATEGORY_DETAILS_FORM);
		mav.addObject(categoryService.findById(categoryId));
		return mav;
	}
	

	@GetMapping("/new")
	public String initCreationForm(Model model) {
		model.addAttribute("category", Category.builder().build());
		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping("/new")
	public String processCreationForm(@Valid Category category, BindingResult result) {
		if (result.hasErrors()) {
			return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
		}
		else {
			Category c = categoryService.save(category);
			return "redirect:/categories/" + c.getId();
		}
	}


	@GetMapping("/{ownerId}/edit")
	public String initUpdateOwnerForm(@PathVariable/*("ownerId")*/ Long categoryId, Model model) {
		Category c = categoryService.findById(categoryId);
		model.addAttribute(c);
		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	}

	// Posto imamo @InitBinder koji onemogucava bindovanje ID-a koristi se rucno postavljanje ID u objektu
	@PostMapping("/{ownerId}/edit")
	public String processUpdateOwnerForm(@Valid Category category, BindingResult result,
			@PathVariable("ownerId") Long ownerId) {
		if (result.hasErrors()) {
			return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
		}
		else {
			category.setId(ownerId);
			Category o = categoryService.save(category);
			return "redirect:/categories/"+o.getId();
		}
	}

}
