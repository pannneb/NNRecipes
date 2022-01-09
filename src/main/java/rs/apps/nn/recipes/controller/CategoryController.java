package rs.apps.nn.recipes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.api.EnumResponseStatus;
import rs.apps.nn.recipes.api.ResponseData;
import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.service.CategoryService;

@Slf4j
@Controller
@RequestMapping(path = "category")
public class CategoryController {

	private static final String VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM = "categories/categoryCreateOrUpdateForm";
	private static final String VIEWS_CATEGORY_LIST_FORM = "categories/categoryList";

	private final CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@RequestMapping(value = { "/list/", "/list" }, method = RequestMethod.GET)
	public String categoriesList(Model model) {
		List<Category> categories = categoryService.findAllByOrderByIdAsc();
		// Set<Recipe> listOfRecipes = recipeService.getRecipes();
		model.addAttribute("categories", categories);
		// listOfRecipes.forEach(a->{
		// a.getCategories().forEach(c->{
		// System.out.println(c.toString());
		// });
		// });
		//
		log.debug("CategoryController list called");
		return VIEWS_CATEGORY_LIST_FORM;
	}

	@InitBinder
	public void setAllowedFields(WebDataBinder dataBinder) {
		dataBinder.setDisallowedFields("id");
	}
	
	@GetMapping({ "/new", "/new/" })
	public String initCreationForm(Model model) {
		log.debug("initCreationForm - GET");
		model.addAttribute("category", Category.builder().build());
		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping({ "/new" })
	public String submitCreationForm(@Valid Category category, BindingResult result, Model model, 
			final RedirectAttributes redirectAttributes) {
		log.debug("category add - POST");
		if (result.hasErrors()) {
			model.addAttribute("err", "msg.err.pleaseFix");
			return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
		} else {
			Category c = categoryService.save(category);

			// Add message to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			redirectAttributes.addFlashAttribute("msg", "msg.info.recordAdded");

			return "redirect:/category/" + c.getId();
		}
	}

	@GetMapping({ "/{categoryId}"})
	public String showById(@PathVariable("categoryId") Long categoryId, Model model) {
		log.debug("showById - GET");
		model.addAttribute("category", categoryService.findById(categoryId));
		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	}

	// Posto imamo @InitBinder koji onemogucava bindovanje ID-a koristi se rucno
	// postavljanje ID u objektu
	@PostMapping("/{categoryIdd}")
	public String processUpdateRecipeForm(@Valid Category category, BindingResult result, Model model,
			@PathVariable("categoryIdd") Long categoryIdd, 
			final RedirectAttributes redirectAttributes) {
		log.debug("processUpdateRecipeForm - POST");
		if (result.hasErrors()) {
			// redirectAttributes.addFlashAttribute("err", "Please fix errors!");
			model.addAttribute("err", "msg.err.pleaseFix");
			return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
		} else {
			// boolean isNew = category.isNew();

			category.setId(categoryIdd);
			//boolean isNew = category.isNew();

			Category c = categoryService.save(category);

			// Add message to flash scope
			redirectAttributes.addFlashAttribute("css", "success");
			if (category.isNew()) {
				redirectAttributes.addFlashAttribute("msg", "msg.info.recordAdded");
			} else {
				redirectAttributes.addFlashAttribute("msg", "msg.info.recordUpdated");
			}

			return "redirect:/category/" + c.getId();
		}
	}

	@Deprecated
	@GetMapping({ "/all/json" })
	@ResponseBody
	public List<Category> getCategories() {
		return categoryService.findAll();
	}

	@PostMapping(value = "/newJSON", consumes = "application/json", produces = "application/json")
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

}
