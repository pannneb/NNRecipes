package rs.apps.nn.guessme.api;

/**
 * 
 * @author Nebojsa
 *
 */
public enum EnumResponseStatus {
	RESP_OK("00"), RESP_GENERAL_ERR("01");

	private final String id;

	EnumResponseStatus(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

}
