package rs.apps.nn.recipes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Table(name = "recipe_image_map", schema = "recipes")
@Getter
@Setter
@Entity
@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "recipe" })
@ToString
public class RecipeImageMap {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(value = EnumType.STRING)
	@Column(name = "fileType")
	private EnumFileType fileType;

	private String url;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY /* , optional = false */ )
	@JoinColumn(name = "recipeFk", referencedColumnName = "ID")
	private Recipe recipe;

	public RecipeImageMap(Recipe recipe) {
		super();
		this.recipe = recipe;
	}
	
}
