package rs.apps.nn.recipes.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "file_data", schema = "recipes")
public class FileData {

	// @GeneratedValue(generator = "uuid")
	// @GenericGenerator(name = "uuid", strategy = "uuid2")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String type;

    @OneToOne(mappedBy = "fileData")
    private RecipeImageData recipeImageData;
 
	@Lob
	private byte[] data;

	public FileData() {
	}
	
	public FileData(String name, String type, byte[] data) {
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
//
//	public Long getRecipeFk() {
//		return recipeFk;
//	}
//
//	public void setRecipeFk(Long recipeFk) {
//		this.recipeFk = recipeFk;
//	}

	public void setId(Long id) {
		this.id = id;
	}
}