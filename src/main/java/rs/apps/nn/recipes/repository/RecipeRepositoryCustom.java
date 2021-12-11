package rs.apps.nn.recipes.repository;

import java.util.Optional;

import rs.apps.nn.recipes.domain.Word;

public interface RecipeRepositoryCustom {

	Optional<Word> searchDataByCustomVal(String word);

	Optional<Word> searchWordByWordDataHQL(String word);

}
