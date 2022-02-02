package rs.apps.nn.recipes.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.domain.Comment;
import rs.apps.nn.recipes.domain.EnumUnitOfMeasure;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.UnitOfMeasure;
import rs.apps.nn.recipes.service.CategoryService;
import rs.apps.nn.recipes.service.CommentService;
import rs.apps.nn.recipes.service.RecipeService;
import rs.apps.nn.recipes.service.UnitOfMeasureService;

@Slf4j
@Controller
@RequestMapping("recipe")
public class RecipeController {

	private static final String VIEWS_RECIPE_CREATE_OR_UPDATE_FORM = "recipes/recipeCreateOrUpdateForm";
	private static final String VIEWS_RECIPE_COMMENTS_LIST = "comments/recipeCommentsList";

	private final RecipeService recipeService;
	private final CategoryService categoryService;
	private final UnitOfMeasureService uomService;
	private final CommentService commentService;

	public RecipeController(RecipeService recipeService, CategoryService categoryService, UnitOfMeasureService uomService, CommentService commentService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
		this.uomService = uomService;
		this.commentService = commentService;
		log.debug("RecipeController constructor called");
	}
	
	@RequestMapping(value = { "/list/", "/list" }, method = RequestMethod.GET)
	public String recipesList(Model model) {
		List<Recipe> recipes = recipeService.findAllByOrderByIdAsc();
		// Set<Recipe> listOfRecipes = recipeService.getRecipes();
		model.addAttribute("recipes", recipes);
		// listOfRecipes.forEach(a->{
		//     a.getCategories().forEach(c->{
		//			System.out.println(c.toString());
		//		});
		// });
		//		
		log.debug("RecipeController list called");
		return "recipes/recipeList";

	}

	@GetMapping({ "/new" })
	public String initCreationForm(Model model) {
		model.addAttribute("recipe", Recipe.builder().build());
		prepareModelForAddUpdateView(model);
		return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
	}

	private void prepareModelForAddUpdateView(Model model) {
		List<Category> l = categoryService.findAll();
		model.addAttribute("categoryList", l);
		log.debug("prepareModelForAddUpdateView, categories size: " + l.size());

		List<EnumUnitOfMeasure> unitOfMeasuresList = Arrays.asList(EnumUnitOfMeasure.values());
		model.addAttribute("unitOfMeasuresList", unitOfMeasuresList);
		log.debug("prepareModelForAddUpdateView, unitOfMeasuresList size: " + unitOfMeasuresList.size());

		List<UnitOfMeasure> allUnitOfMeasuresList = uomService.findAllByOrderByIdAsc();
		model.addAttribute("allUnitOfMeasuresList", allUnitOfMeasuresList);
		log.debug("prepareModelForAddUpdateView, allUnitOfMeasuresList size: " + allUnitOfMeasuresList.size());

	}

	@PostMapping({ "/new" })
	public String submitCreationForm(@Valid Recipe recipe, BindingResult result) {
		log.debug("recipe new POST");
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error->{
				log.debug(error.toString());
			});

			// result.geterr
			return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
		} else {
			Recipe c = recipeService.save(recipe);
			return "redirect:/recipe/" + c.getId();
		}

	// 	model.addAttribute("recipe", Recipe.builder().build());	
	//	return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
	}

	@GetMapping({ "/{recipeId}"})
	public String showById(@PathVariable("recipeId") Long recipeId, Model model) {
		Recipe r = recipeService.findById(recipeId);
		model.addAttribute("recipe", r);
		prepareModelForAddUpdateView(model);
		return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
	}
	
	// Posto imamo @InitBinder koji onemogucava bindovanje ID-a koristi se rucno
	// postavljanje ID u objektu
	@PostMapping("/{recipeId}")
	public String processUpdateRecipeForm(@Valid Recipe recipe, BindingResult result,
			@PathVariable("recipeId") Long recipeId) {
		log.debug("recipe update POST");
		log.debug("recipe ingredients count:"+recipe.getIngredients().size());
		log.debug("recipe ingredients:"+recipe.getIngredients());

		// V1 - remove empty elements using Iterator - avoid ConcurrentModificationException
		// Empty Objects remains (Object with all fields equal to null) in the list after some row is deleted on the form.
		//		ListIterator<Ingredient> iter = recipe.getIngredients().listIterator();
		//		while(iter.hasNext()){
		//			if (Stream.of(iter.next().getId(), iter.next().getUom(), iter.next().getIngredientName(), iter.next().getAmount()).allMatch(Objects::isNull)) {
		//				iter.remove();
		//			}
		//		}

		// V2 - remove empty elements using Streams - avoid ConcurrentModificationException
		recipe.getIngredients().removeIf(i -> Stream.of(i.getId(), i.getUom(), i.getIngredientName(), i.getAmount()).allMatch(Objects::isNull));
		
		if (result.hasErrors()) {
			result.getAllErrors().forEach(error->{
				log.debug(error.toString());
			});
			return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
		} else {
			recipe.setId(recipeId);
			Recipe c = recipeService.save(recipe);
			return "redirect:/recipe/" + c.getId();
		}
	}

	// Posto imamo @InitBinder koji onemogucava bindovanje ID-a koristi se rucno
	// postavljanje ID u objektu
	@PostMapping("/{recipeId}/delete/")
	public String processDeleteRecipe(Recipe recipe, @PathVariable("recipeId") Long recipeId, Model model) {
		// if (result.hasErrors()) {
		// return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
		// } else {
		
		recipeService.deleteById(recipeId);
	 	return "redirect:/recipe/list";
		
//		log.debug("processDeleteRecipe");
//		List<Recipe> recipes = recipeService.findAll();
//		model.addAttribute("recipes", recipes);
//		return "recipes/recipeList";
		
	}
	              //recipe/-133/      comment/list
	@GetMapping({ "/{recipeId}/comment/list"})
	public String showAllRecipeComments(@PathVariable("recipeId") Long recipeId, Model model) {
		log.debug("recipe showAllRecipeComments GET, recipeId:{}", recipeId);
		Recipe r = recipeService.findById(recipeId);
		model.addAttribute("recipeComments", r.getComments());
		model.addAttribute("commentsRecipeId", recipeId);
		// prepareModelForAddUpdateView(model);
		return VIEWS_RECIPE_COMMENTS_LIST;
	}
	
	@RequestMapping(value = { "/{recipeId}/comment/new" }, method = RequestMethod.POST)
	public String onlineSbWebtestPreviewJSONAjax(@RequestBody Comment comment,  @PathVariable("recipeId") Long recipeId) {
		log.debug("recipe processNewCommentForm POST, comment: {} ", comment);
//        if (result.hasErrors()) {
//            return "pets/createOrUpdateCommentForm";
//        } else {
        	Recipe r = recipeService.findById(recipeId);
        	// TODO Move to DB - if not populated -> set Default=current timestamp in DB.
        	// (Joda dateTime option)
        	comment.setInsertedDt(Timestamp.valueOf(LocalDateTime.now()));
        	comment.setRecipe(r);
            commentService.save(comment);
            return "redirect:/recipe/"+recipeId+"/comment/list";
//        }
		
	}

//	 // Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
//    @PostMapping(path="/{recipeId}/comment/new", consumes = { "application/json" })
//    public String processNewCommentForm(@Valid Valid Comment comment, BindingResult result,  @PathVariable("recipeId") Long recipeId) {
//		log.debug("recipe processNewCommentForm POST, comment: {} ", comment);
//        if (result.hasErrors()) {
//            return "pets/createOrUpdateCommentForm";
//        } else {
//        	Recipe r = recipeService.findById(recipeId);
//        	comment.setRecipe(r);
//            commentService.save(comment);
//            return "redirect:/recipe/{recipeId}";
//        }
//    }
	

	//	public ModelAndView showOwner(@PathVariable("recipeId") Long ownerId) {
	//		System.out.println("RecipeController showOwner");
	//		ModelAndView mav = new ModelAndView("owner/ownerDetails");
	//		mav.addObject(recipeService.findById(ownerId));
	//		return mav;
	//	}

	//	@GetMapping("/{ownerId}/edit")
	//	public String initUpdateOwnerForm(@PathVariable/*("ownerId")*/ Long categoryId, Model model) {
	//		Category c = categoryService.findById(categoryId);
	//		model.addAttribute(c);
	//		return VIEWS_CATEGORY_CREATE_OR_UPDATE_FORM;
	//	}

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
