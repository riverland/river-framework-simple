package org.river.base.entity.enumaration;
/**
 * relation between conditions
 */
public enum Relation {
	AND("and"), OR("or");
	private String value;

	public String getValue() {
		return value;
	}

	private Relation(String value) {
		this.value = value;
	}
};
