package rs.apps.nn.recipes.service;

import java.util.List;

import rs.apps.nn.recipes.domain.Category;
import rs.apps.nn.recipes.exception.ValidateException;

public interface CategoryService extends CrudServiceRecipes<Category, Long>{

	List<Category> findAllByDescriptionLike(String desc);

	Category createOrUpdateCategory(Category entity) throws ValidateException;

}
