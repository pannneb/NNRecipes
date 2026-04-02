package rs.apps.nn.recipes.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import rs.apps.nn.recipes.domain.Recipe;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, RecipeRepositoryCustom {

	Optional<Recipe> findById(Long wordId);

//	Optional<Recipe> findByWord(String word);

	List<Recipe> findAllByDescriptionLikeIgnoreCase(String desc);

	List<Recipe> findAllByCookTimeGreaterThanEqual(int time1);

	List<Recipe> findAllByCookTimeLessThanEqual(int time1);

	List<Recipe> findAllByCookTimeGreaterThanEqualAndCookTimeLessThanEqual(int timeFrom, int timeTo);

	List<Recipe> findAllByOrderByIdAsc();

	// https://www.bezkoder.com/spring-jpa-query/
	@Query("SELECT r FROM Recipe r")
	List<Recipe> findAllQuery();

	@Query(value = "SELECT * FROM recipes.recipe", nativeQuery = true)
	List<Recipe> findAllQueryNative();
	
	@Query("SELECT t FROM Recipe t WHERE t.dateTimeOfCreation >= ?1")
	List<Recipe> findByDateGreaterThanEqual(Date date);

}
