package rs.apps.nn.recipes.service;

import java.util.List;

import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.exception.ValidateException;

public interface RecipeService extends CrudServiceRecipes<Recipe, Long> {

	List<Recipe> findAllByDescriptionLikeIgnoreCase(String desc);

	List<Recipe> findAllByCookTimeGreaterThanEqual(int time1);

	List<Recipe> findAllByCookTimeLessThanEqual(int time1);

	Recipe createOrUpdateRecipe(Recipe entity) throws ValidateException;

	List<Recipe> findAllByCookTimeGreaterThanEqualAndLessThanEqual(int timeFrom, int timeTo);

	List<Recipe> findAllByOrderByIdAsc();

	List<Recipe> findAllPublicOrOwnedByUser(String loggedUser);

	/**
	 * If loggedUser has role: ADMIN -> show all recipes.
	 * Otherwise -> show only public recipes and recipes of logged user.
	 * 
	 * @param loggedUser
	 * @return
	 */
	List<Recipe> findAllRecipesForList(String loggedUser);

}
