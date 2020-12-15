package rs.apps.nn.guessme.api;

/**
 * Response Data for API
 * 
 * @author Nebojsa
 *
 */
public class ResponseData {

	private String description = "OK";
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ResponseData [description=").append(description).append(", status=").append(status).append("]");
		return builder.toString();
	}

}
