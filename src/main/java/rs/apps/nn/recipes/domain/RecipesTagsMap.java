package rs.apps.nn.recipes.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "RECIPES_TAGS_MAP", schema = "recipes")
@Getter
@Setter
@EqualsAndHashCode()
@Entity
@ToString
@IdClass(RecipesTagsMapId.class)
public class RecipesTagsMap {

	@Id
	private Long recipeFk;
	@Id
	private Long tagFk;

	public RecipesTagsMap() {
		super();
	}

}
