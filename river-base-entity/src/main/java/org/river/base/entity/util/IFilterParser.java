package org.river.base.entity.util;

import org.river.base.entity.IFilter;

/**
 * <p>
 * Helper class to parse {@link Sort} object
 * @author river
 * 2010/06/17
 */
public interface IFilterParser {
	/**
	 * <p>
	 * parse IFilter
	 * @param filter
	 * @return
	 * @throws Exception 
	 */
    public String parse(IFilter filter) throws Exception;
}
