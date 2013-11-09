package org.river.base.entity.enumaration;

/**
 * condition enum
 *
 */
public enum Operator {
	EQ("eq"), EQIGNORECASE("eqIgnoreCase"), NE("ne"), 
	NEIGNORECASE("neIgnoreCase"), GT("gt"), GE("gr"),
	LT("lt"), LE("le"), BETWEEN("between"), NOTBETWEEN("notBeteen"),
	IN("in"), NOTIN("notIn"), EMPTY("empty"),NOTEMPTY("notEmpty"), NULL("isNull"),
	NOTNULL("notNull"),	LIKE("like"), NOTLIKE("notLike");
	private String value;

	public String getValue() {
		return value;
	}

	private Operator(String value) {
		this.value = value;
	}
}
