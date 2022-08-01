package rs.apps.nn.recipes.controller;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;
import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.domain.Comment;
import rs.apps.nn.recipes.domain.EnumFileType;
import rs.apps.nn.recipes.domain.EnumUnitOfMeasure;
import rs.apps.nn.recipes.domain.FileData;
import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.RecipeImageData;
import rs.apps.nn.recipes.domain.Tag;
import rs.apps.nn.recipes.domain.UnitOfMeasure;
import rs.apps.nn.recipes.service.CategoryService;
import rs.apps.nn.recipes.service.CommentService;
import rs.apps.nn.recipes.service.FileDataService;
import rs.apps.nn.recipes.service.RecipeImageDataService;
import rs.apps.nn.recipes.service.RecipeService;
import rs.apps.nn.recipes.service.TagService;
import rs.apps.nn.recipes.service.UnitOfMeasureService;

@Slf4j
@Controller
@RequestMapping("recipe")
public class RecipeController {

	private static final String VIEWS_RECIPE_CREATE_OR_UPDATE_FORM = "recipes/recipeCreateOrUpdateForm";
	private static final String VIEWS_RECIPE_COMMENTS_LIST = "comments/recipeCommentsList";
	private static final String VIEWS_RECIPE_COMMENTS_IMAGES = "segments/imagesCarousel";

	private final RecipeService recipeService;
	private final TagService tagService;
	private final CategoryService categoryService;
	private final UnitOfMeasureService uomService;
	private final CommentService commentService;
	// private final FileDataService fileDataService;
	private final RecipeImageDataService recipeImageDataService;

	public RecipeController(RecipeService recipeService, CategoryService categoryService, 
			UnitOfMeasureService uomService, CommentService commentService, 
			/*FileDataService fileDataService,*/ RecipeImageDataService recipeImageDataService,
			TagService tagService) {
		super();
		this.recipeService = recipeService;
		this.categoryService = categoryService;
		this.uomService = uomService;
		this.commentService = commentService;
		// this.fileDataService = fileDataService;
		this.recipeImageDataService = recipeImageDataService;
		this.tagService = tagService;
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
		List<RecipeImageData> a = recipeImageDataService.findAllByRecipeIdAndFileTypeOrderByIdDesc(recipeId, EnumFileType.IMG_RECIPE_BY_OWNER);
		//List<FileData> a = new ArrayList<FileData>();
		if (a!=null && !a.isEmpty()) {
			log.debug("showRecipeById, image found: {} ",a.get(0).getFileData().getName());
			log.debug("showRecipeById, image found: {} ",a.get(0).getFileData().getType());
			log.debug("showRecipeById, image found: {} ",a.get(0).getId());
			r.setRecipeImage(a.get(0));
		}
		model.addAttribute("recipe", r);
		model.addAttribute("recipeImage", (a!=null && !a.isEmpty()) ? a.get(0) : null);
		
		log.debug("showRecipeById, tags.size: {} , tags: {}", r.getTags().size(), r.getTags());
		 
		prepareModelForAddUpdateView(model);
		return VIEWS_RECIPE_CREATE_OR_UPDATE_FORM;
	}
	
	// Posto imamo @InitBinder koji onemogucava bindovanje ID-a koristi se rucno
	// postavljanje ID u objektu
	@PostMapping("/{recipeId}")
	public String processUpdateRecipeForm(@Valid Recipe recipe, BindingResult result,
			@PathVariable("recipeId") Long recipeId) throws Exception {
		log.debug("recipe update POST");
		log.debug("recipe ingredients count:"+(recipe.getIngredients()==null?"null":recipe.getIngredients().size()));
		log.debug("recipe ingredients:"+recipe.getIngredients());

		// V1 - remove empty elements using Iterator - avoid ConcurrentModificationException
		// Empty Objects remains (Object with all fields equal to null) in the list after some row is deleted on the form.
		//		ListIterator<Ingredient> iter = recipe.getIngredients().listIterator();
		//		while(iter.hasNext()){
		//			if (Stream.of(iter.next().getId(), iter.next().getUom(), iter.next().getIngredientName(), iter.next().getAmount()).allMatch(Objects::isNull)) {
		//				iter.remove();
		//			}
		//		}

		// V2 - remove empty elements using Streams - avoid
		// ConcurrentModificationException
		if (recipe.getIngredients()!=null) { 
			recipe.getIngredients()
					.removeIf(
						i -> Stream.of(i.getId(),(StringUtils.isBlank(i.getIngredientName()) ? null : i.getIngredientName()), i.getAmount())
							.allMatch(Objects::isNull)
					);
	
			recipe.getIngredients().forEach(a -> {
				a.setRecipe(recipe);
				a.setRecipeFk(recipeId);
			});
		}
		
		

		recipe.setTags(new HashSet<Tag>());
		if (recipe.getRecipesTags()!=null) {
			recipe.getRecipesTags().forEach(a -> {
			    List<String> tokensForTag = Collections.list(new StringTokenizer(a, ":")).stream()
			      .map(token -> (String) token)
			      .collect(Collectors.toList());
			    
			    if (tokensForTag.size()>1) {
			    	// Tag _tag = tagService.findById(Long.valueOf(tokensForTag.get(0)));
			    	// if (_tag == null)         .orElseThrow(() -> new ResourceNotFoundException("Not found Tag with id = " + tagId));
			        // if (_tag == null) throw new java.lang.Exception("Not found Tag with id = " + tokensForTag.get(0));
			    	//recipe.addTag(_tag);
			    	recipe.getTags().add(Tag.builder().id(Long.valueOf(tokensForTag.get(0))).value(tokensForTag.get(1)).recipes(null).build());
			    } else {
			    	Optional<Tag> _tag = tagService.findByValue(tokensForTag.get(0));
			    	recipe.getTags().add(Tag.builder().id(_tag.isPresent()?_tag.get().getId():null).value(tokensForTag.get(0)).recipes(null).build()); 	
			    }
	//			recipe.getTags().add(e)
				// -111:breakfast			
			});
		}

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

	@GetMapping({ "/{recipeId}/comment/list"})
	public String showAllRecipeComments(@PathVariable("recipeId") Long recipeId, Model model) {
		log.debug("recipe showAllRecipeComments GET, recipeId:{}", recipeId);
		Recipe r = recipeService.findById(recipeId);
		model.addAttribute("recipeComments", r.getComments());
		model.addAttribute("commentsRecipeId", recipeId);
		// prepareModelForAddUpdateView(model);
		return VIEWS_RECIPE_COMMENTS_LIST;
	}

	@GetMapping({ "/{recipeId}/images/list"})
	public String showAllRecipeCommentImages(@PathVariable("recipeId") Long recipeId, Model model) {
		log.debug("recipe showAllRecipeCommentImages GET, recipeId:{}", recipeId);

		List<RecipeImageData> imgsFromComms = recipeImageDataService.findAllByRecipeIdAndFileTypeOrderByIdDesc(recipeId, EnumFileType.IMG_RECIPE_FROM_COMMENTS);
		log.debug("showRecipeById, images from comments found, count: {} ",imgsFromComms.size());
		model.addAttribute("recipeImagesFromComments", (imgsFromComms==null ||imgsFromComms.isEmpty()) ? null: imgsFromComms);
 		model.addAttribute("commentImagesRecipeId", recipeId);
		// prepareModelForAddUpdateView(model);
		return VIEWS_RECIPE_COMMENTS_IMAGES;
	}
	
	@RequestMapping(value = { "/{recipeId}/comment/new" }, method = RequestMethod.POST)
	public String saveCommentAjax(@RequestBody Comment comment,  @PathVariable("recipeId") Long recipeId) {
		log.debug("recipe processNewCommentForm POST, comment: {} ", comment);
//        if (result.hasErrors()) {
//            return "pets/createOrUpdateCommentForm";
//        } else {
        	Recipe r = recipeService.findById(recipeId);
        	// TODO Move to DB - if not populated -> set Default=current timestamp in DB.
        	// (Joda dateTime option)
        	comment.setInsertedDt(Timestamp.valueOf(LocalDateTime.now()));
        	comment.setRecipe(r);
        	
        	Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        	String username="";
        	if (principal instanceof UserDetails) {
        	  username = ((UserDetails)principal).getUsername();
        	} else {
        	  username = principal.toString();
        	}

		log.debug("Save Comment - username: {}", username);
        	comment.setUsername(username);

		//	log.debug("Save Comment, user:{}", username);

            commentService.save(comment);
            return "redirect:/recipe/"+recipeId+"/comment/list";
//        }
		
	}

	@PostMapping("/{recipeId}/uploadPhoto")
	public String uploadFile(@RequestParam("file") MultipartFile file, @RequestParam("imageId") Long imageId, @PathVariable("recipeId") Long recipeId, RedirectAttributes attributes) {
		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/";
		} else {
			try {
				
				recipeImageDataService.store(file, EnumFileType.IMG_RECIPE_BY_OWNER, recipeService.findById(recipeId),imageId);

				// Delete this 
				// recipeImageDataService.store(file, EnumFileType.IMG_RECIPE_FROM_COMMENTS, recipeService.findById(recipeId),null);
				// recipeImageDataService.store(file, EnumFileType.IMG_RECIPE_FROM_COMMENTS, recipeService.findById(recipeId),null);

				log.debug("You successfully uploaded:{}, imageId:{}, resource:{}", file.getOriginalFilename(), imageId, file.getResource());
				// message = "Uploaded the file successfully: " + file.getOriginalFilename();
				//return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} catch (Exception e) {
				log.error("Exception during image upload", e);
				//message = "Could not upload the file: " + file.getOriginalFilename() + "!";
				// return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
						
		}

		// normalize the file path
//	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//	        // save the file on the local file system
//	        try {
//	            //Path path = Paths.get(UPLOAD_DIR + fileName);
//	            // Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }

		// return success response
		// attributes.addFlashAttribute("message", "You successfully uploaded " +
		// fileName + '!');

		return "redirect:/recipe/" + recipeId;
	}
	
	@PostMapping("/{recipeId}/uploadCommentsPhoto")
	public String uploadCommentPhoto(@RequestParam("file") MultipartFile file, @PathVariable("recipeId") Long recipeId, RedirectAttributes attributes) {
		// check if file is empty
		if (file.isEmpty()) {
			attributes.addFlashAttribute("message", "Please select a file to upload.");
			return "redirect:/";
		} else {
			try {
				recipeImageDataService.store(file, EnumFileType.IMG_RECIPE_FROM_COMMENTS, recipeService.findById(recipeId),null);
				log.debug("You successfully uploaded:{}, resource:{}", file.getOriginalFilename(), file.getResource());
			} catch (Exception e) {
				log.error("Exception during image upload", e);
			}
						
		}

		return "redirect:/recipe/" + recipeId;
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
