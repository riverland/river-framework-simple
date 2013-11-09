package org.river.base.entity.enumaration;

/**
 * 
 * @author river 2010/05/31
 */
public enum SortType {

	ASC(1), DESC(-1);

	private int value;

	public int getValue() {
		return value;
	}

	private SortType(int value) {
		this.value = value;
	}
}
