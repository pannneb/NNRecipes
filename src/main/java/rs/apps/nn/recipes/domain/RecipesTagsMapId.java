package rs.apps.nn.recipes.domain;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode()
@ToString
public class RecipesTagsMapId implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8266605299739585619L;

	private Long recipeFk;

	private Long tagFk;

	// default constructor

	public RecipesTagsMapId() {

	}

	public RecipesTagsMapId(Long recipeFk, Long tagFk) {
		this.recipeFk = recipeFk;
		this.tagFk = tagFk;
	}

	// equals() and hashCode()

}