package rs.apps.nn.guessme.service;

import java.util.List;

import rs.apps.nn.guessme.exception.ValidateException;
import rs.apps.nn.guessme.model.Category;

public interface CategoryService {

	List<Category> getCategories();

	Category createOrUpdateCategory(Category entity) throws ValidateException;

}
