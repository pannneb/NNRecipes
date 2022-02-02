package rs.apps.nn.recipes.domain;

public enum EnumUnitOfMeasure implements IdLabelInt {

	// @formatter:off
	EACH("EACH"),
	TEASPOON("TEASPOON"), 
	TABLESPOON("TABLESPOON"), 
	CUP("CUP"), 
	PINC("PINC"), 
	OUNCE("OUNCE"), 
	DASH("DASH"), 
	PINT("PINT");
	// @formatter:on

	private final String id;

	private EnumUnitOfMeasure(String id) {
		this.id = id;
	}

	public static EnumUnitOfMeasure getById(String id) throws Exception {
		for (EnumUnitOfMeasure u : values()) {
			if (u.getId().equals(id))
				return u;
		}
		throw new Exception("Unknown EnumUnitOfMeasure:" + id);
	}

	public static boolean isStatusValid(String id) {
		for (EnumUnitOfMeasure u : values()) {
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
