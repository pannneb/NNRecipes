package rs.apps.nn.recipes.domain;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Table(name = "recipe", schema = "recipes")
@Getter
@Setter
@Entity
@Builder
@Slf4j
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
// @NoArgsConstructor
public class Recipe extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	@Size(min = 5, max = 250)
	private String title;
	private String description;
	private Integer prepTime;
	private Integer cookTime;
//	private Integer servTime;
	private Integer portions;
	@NotNull
	private String source;
	private String url;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private List<Ingredient> ingredients;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	private Set<Comment> comments;

	@Transient
	private RecipeIngredientsData recipeIngredientsData;

	@Lob
	private String directions;

	//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
	//	private Set<Ingredient> ingredients = new HashSet<Ingredient>();
	//
	//	// Za cuvanje slike
	//	@Lob
	//	private Byte[] image;
	//
	/**
	 * // ORDINAL - ide u bazu kao 1, 2, 3 (problem ako se doda novi tip, poremete
	 * se identi) // STRING - ide u bazu kao text
	 */
	@Enumerated(value = EnumType.STRING)
	@Column(name = "difficulty")
	private EnumDifficulty enumDifficulty;

	// @OneToOne(cascade = CascadeType.ALL)
	// @ManyToMany
	// @JoinTable(schema="recipes", name = "recipe_note", joinColumns =
	// @JoinColumn(name = "recipe_id"), inverseJoinColumns = @JoinColumn(name =
	// "note_id"))
	//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "recipes")
	//	private Set<Note> notes = new HashSet<Note>();
	
	//	@Column
	// 	private Long categoryFk;

	// @JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY /* , optional = false */ )
	@JoinColumn(name = "categoryFk", referencedColumnName = "ID", nullable = true)
	private Category category;

}
