package org.river.base.entity.util;

/**
 * <p>
 * Helper class to parse {@link Sort} object
 * @author river
 * 2010/06/17
 */
public interface ISortParser {
	
	public static final String ASC="asc";
	public static final String DESC="desc";
	
	/**
	 * <p>
	 * parse sort
	 * @param sort
	 * @return
	 */
    public String parse(Sort sort);
}
