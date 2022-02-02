package rs.apps.nn.recipes.domain;

import java.math.BigDecimal;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "ingredient", schema = "recipes")
@Getter
@Setter
@EqualsAndHashCode (exclude = { "recipe" })
@Entity
@ToString(exclude = { "recipe" })
public class Ingredient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// private Long recipeFk;

	private String ingredientName;
	
	private BigDecimal amount;

//	@OneToOne(fetch = FetchType.EAGER)
	@Enumerated(value = EnumType.STRING)
	@Column(name="unitOfMeasure")
	private EnumUnitOfMeasure enumUnitOfMeasure;
 
	private String uom;

	@ManyToOne(fetch = FetchType.LAZY /* , optional = false*/ )
    @JoinColumn(name="RECIPE_FK", referencedColumnName = "ID", nullable=true, unique = false, insertable = false, updatable = false)
   	private Recipe recipe;

	@Column(name = "RECIPE_FK", nullable = true, unique = false)
	private Long recipeFk;

//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "APP_PANEL_FK", referencedColumnName = "ID", nullable = true, unique = false, insertable = false, updatable = false)
//	private AppPanel appPanel;

	
	public Ingredient() {
		super();
	}

	public Ingredient(String ingredientName, BigDecimal amount, EnumUnitOfMeasure enumUnitOfMeasure, String uom) {
		super();
		this.ingredientName = ingredientName;
		this.amount = amount;
		this.enumUnitOfMeasure = enumUnitOfMeasure;
		this.uom = uom;
	}

//	public Ingredient(String description, BigDecimal amount, EnumUnitOfMeasure uom, Recipe recipe) {
//		super();
//		this.description = description;
//		this.amount = amount;
//		this.uom = uom;
//		this.recipe = recipe;
//	}

}
