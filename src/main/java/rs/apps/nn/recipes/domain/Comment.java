package rs.apps.nn.recipes.domain;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Table(name = "comment", schema = "recipes")
@Getter
@Setter
@EqualsAndHashCode(exclude = { "recipe" })
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY /* , optional = false*/ )
    @JoinColumn(name="recipeFk", referencedColumnName = "ID", nullable=true)
	private Recipe recipe;

	@Lob
	private String text;

	private Timestamp insertedDt;

	private String username;

	//	public Recipe getRecipe() {
	//		return recipe;
	//	}
	//
	//	public void setRecipe(Recipe recipe) {
	//		this.recipe = recipe;
	//	}

	// public String getRecipeNotes() {
	// return recipeNotes;
	// }
	//
	// public void setRecipeNotes(String recipeNotes) {
	// this.recipeNotes = recipeNotes;
	// }
	//
	// public Long getId() {
	// return id;
	// }
	//
	// public void setId(Long id) {
	// this.id = id;
	// }
	//
	// @Override
	// public String toString() {
	// StringBuilder builder = new StringBuilder();
	// builder.append("Notes [recipe=").append(recipe).append(",
	// recipeNotes=").append(recipeNotes).append("]");
	// return builder.toString();
	// }

}
