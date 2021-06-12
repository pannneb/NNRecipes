package rs.apps.nn.recipes.api;

/**
 * 
 * @author Nebojsa
 *
 */
public enum EnumResponseStatus {
	RESP_OK("00"), RESP_GENERAL_ERR("01")
	, RESP_WORD_ALREADY_EXISTS("02")
	, RESP_ENTITY_ALREADY_EXISTS("10");

	private final String id;

	EnumResponseStatus(String id) {
		this.id = id;
	}

	public String getId() {
		return this.id;
	}

}
