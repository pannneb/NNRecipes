package rs.apps.nn.recipes.domain;

import java.util.ArrayList;

import javax.persistence.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@Entity
@Builder
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class RecipeIngredientsData extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Builder.Default
	private ArrayList<String> ingQuantityList= new ArrayList<>();
	@Builder.Default
	private ArrayList<String> ingMeasureList= new ArrayList<>();
	@Builder.Default
	private ArrayList<String> ingIngredientList= new ArrayList<>();

}
