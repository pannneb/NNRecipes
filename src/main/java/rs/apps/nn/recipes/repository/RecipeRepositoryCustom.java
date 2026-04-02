package rs.apps.nn.recipes.repository;

import java.util.List;
import java.util.Optional;

import rs.apps.nn.recipes.domain.Recipe;
import rs.apps.nn.recipes.domain.Word;

public interface RecipeRepositoryCustom {

	Optional<Word> searchDataByCustomVal(String word);

	Optional<Word> searchWordByWordDataHQL(String word);

	List<Recipe> findAllPublicOrOwnedByUserSQL(String loggedUser);

	List<Recipe> findAllPublicOrOwnedByUserHQL(String loggedUser);

}
