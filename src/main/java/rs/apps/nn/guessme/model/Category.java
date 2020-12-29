package rs.apps.nn.guessme.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author Nebojsa
 *
 */
@Entity
@Table(name = "category", schema = "asocijacije")
@Getter
@Setter
@EqualsAndHashCode(exclude = { }) // (exclude = { "recipes" })
@Data
@NoArgsConstructor
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@Column
	private String description;

	@Builder
	public Category(Long id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	// @OneToMany(mappedBy = "appEmailSend", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//@OneToMany(mappedBy = "category", cascade=CascadeType.ALL)
	//private Set<Word> words;
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getDescription() {
//		return description;
//	}
//
//	public void setDescription(String description) {
//		this.description = description;
//	}

//	public Set<Word> getWords() {
//		return words;
//	}
//
//	public void setWords(Set<Word> words) {
//		this.words = words;
//	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Category [id=").append(id).append(", name=").append(name).append(", description=")
				.append(description)/*.append(", words=").append(words)*/.append("]");
		return builder.toString();
	}

}
