package rs.apps.nn.recipes.controller.v1;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import rs.apps.nn.recipes.api.EnumResponseStatus;
import rs.apps.nn.recipes.api.ResponseData;
import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.service.CategoryService;

@Controller("categoryControllerApi")
@RequestMapping(path = "/v1/categories")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@GetMapping({ /* "/", */ "" })
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

}
