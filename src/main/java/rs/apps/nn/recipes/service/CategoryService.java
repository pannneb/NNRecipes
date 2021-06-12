package rs.apps.nn.recipes.service;

import java.util.List;

import rs.apps.nn.recipes.exception.ValidateException;
import rs.apps.nn.recipes.model.Category;

public interface CategoryService extends CrudServiceGuessMe<Category, Long>{

	List<Category> findAllByNameLike(String name);

	Category createOrUpdateCategory(Category entity) throws ValidateException;

}
