package rs.apps.nn.recipes.service;

import java.util.List;

import rs.apps.nn.recipes.domain.UnitOfMeasure;

public interface UnitOfMeasureService extends CrudServiceRecipes<UnitOfMeasure, Long> {

	List<UnitOfMeasure> findAllByOrderByIdAsc();

}
