package rs.apps.nn.recipes.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "category", schema = "recipes")
@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"})
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
	@NotNull
	@Size(min = 5, max=250)
	private String description;

	// @ManyToMany(mappedBy = "categories")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private Set<Recipe> recipes;

//	//public Long getId() {
//	//	return id;
//	// }
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//	public Set<Recipe> getRecipes() {
//		return recipes;
//	}
//
//	public void setRecipes(Set<Recipe> recipes) {
//		this.recipes = recipes;
//	}

	public String getLabel() {
		return description;
	}
}
