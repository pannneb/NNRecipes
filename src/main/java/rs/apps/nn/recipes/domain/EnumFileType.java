package rs.apps.nn.recipes.domain;

public enum EnumFileType implements IdLabelInt {

	// @formatter:off
	IMG_RECIPE_BY_OWNER("REC_OWNER"),
	IMG_RECIPE_FROM_COMMENTS("REC_COMMENT");
	// @formatter:on

	private final String id;

	private EnumFileType(String id) {
		this.id = id;
	}

	public static EnumFileType getById(String id) throws Exception {
		for (EnumFileType u : values()) {
			if (u.getId().equals(id))
				return u;
		}
		throw new Exception("Unknown EnumFileType:" + id);
	}

	public static boolean isStatusValid(String id) {
		for (EnumFileType u : values()) {
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
