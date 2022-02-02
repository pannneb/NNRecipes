package rs.apps.nn.recipes.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
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
import lombok.ToString;

@Table(name = "category", schema = "recipes")
@Getter
@Setter
@EqualsAndHashCode(exclude = { "recipes" })
@Entity
@Data
@ToString(exclude = { "recipes" })
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 5, max = 250)
	private String description;

	// @ManyToMany(mappedBy = "categories")
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private Set<Recipe> recipes;

	public String getLabel() {
		return description;
	}

}
