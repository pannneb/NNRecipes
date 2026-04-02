package rs.apps.nn.recipes.domain;

public enum EnumUserRole implements IdLabelInt {

	// @formatter:off
	ROLE_ADMIN("ROLE_ADMIN"),
	ROLE_USER("ROLE_USER");
	// @formatter:on

	private final String id;

	private EnumUserRole(String id) {
		this.id = id;
	}

	public static EnumUserRole getById(String id) throws Exception {
		for (EnumUserRole u : values()) {
			if (u.getId().equals(id))
				return u;
		}
		throw new Exception("Unknown EnumUserRole:" + id);
	}

	public static boolean isStatusValid(String id) {
		for (EnumUserRole u : values()) {
			if (u.getId().equals(id))
				return true;
		}
		return false;
	}

	@Override
	public String getLabel() {
		return id;
	}

	@Override
	public String getId() {
		return id;
	}
}
