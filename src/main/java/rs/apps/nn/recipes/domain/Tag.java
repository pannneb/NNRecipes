package rs.apps.nn.recipes.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tag", schema = "recipes")
@Getter
@Setter
@EqualsAndHashCode(exclude = { "recipes" })
@Entity
@ToString(exclude = { "recipes" })
@Builder
public class Tag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String value;

	@ManyToMany(fetch = FetchType.EAGER,
		      cascade = {
		             // CascadeType.PERSIST,
		             // CascadeType.MERGE
		              CascadeType.ALL
		      }, 
		      mappedBy = "tags")
	Set<Recipe> recipes; // = new HashSet<>();

	public Tag() {
		super();
	}

	public Tag(Long id, String value, Set<Recipe> recipes) {
		super();
		this.id = id;
		this.value = value;
		this.recipes = recipes;
	}

}
