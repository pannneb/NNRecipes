package rs.apps.nn.recipes.service;

import java.util.Optional;

import rs.apps.nn.recipes.domain.Tag;

public interface TagService extends CrudServiceRecipes<Tag, Long> {

	Optional<Tag> findByValue(String value);

	// List<Category> findAllByDescriptionLike(String desc);

	// Category createOrUpdateCategory(Category entity) throws ValidateException;

	// List<Category> findAllByOrderByIdAsc();

}
