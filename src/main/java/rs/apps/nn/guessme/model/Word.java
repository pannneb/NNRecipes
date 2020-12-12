package rs.apps.nn.guessme.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

/**
 * 
 * @author Nebojsa
 *
 */
@Entity
@Table(name = "WORD", schema="asocijacije")
@Data
public class Word {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column
	private String word;
	@Column
	private Long categoryFk;

    @ManyToOne(fetch = FetchType.LAZY /* , optional = false*/ )
    @JoinColumn(name="categoryFk", referencedColumnName = "ID", nullable=false, insertable = false,  updatable=false)
    private Category category;

//	@ManyToOne(fetch = FetchType.LAZY, optional = false)
//	@JoinColumn(name = "PARTICIPANT_FK", referencedColumnName = "ID", nullable = true, unique = false, insertable = false, updatable = false)
//	private Participant participant;
//
//	@Column(name = "PARTICIPANT_FK", nullable = true, unique = false)
//	private Long participantFk;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public Long getCategoryFk() {
		return categoryFk;
	}

	public void setCategoryFk(Long categoryFk) {
		this.categoryFk = categoryFk;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Word [id=").append(id).append(", word=").append(word).append(", categoryFk=").append(categoryFk)
				.append("]");
		return builder.toString();
	}

}
