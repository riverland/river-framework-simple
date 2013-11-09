package org.river.base.entity;

import java.util.Collection;
import java.util.List;

import org.river.base.entity.enumaration.Operator;
import org.river.base.entity.enumaration.Relation;


/**
 * <p>
 * compose the filter contition
 * </p>
 * 
 * @author river 2010/05/31
 */
public interface IFilter {
	
	/**left value key for map*/
	public static final String LEFT_VALUE="l_v";
	
	/**right value key for map*/
	public static final String RIGHT_VALUE="r_v";
	
	/**
	 * <p>
	 * check if the instance is an empty filter
	 * @return
	 */
	public boolean isFilterEmpty();

	/**
	 * operator and
	 * 
	 * @param filter
	 * @return
	 */
	public IFilter and(IFilter filter);

	/**
	 * operator or
	 * 
	 * @param filter
	 * @return
	 */
	public IFilter or(IFilter filter);

	/**
	 * filter condition: equal
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter eq(String fieldName, Object value);

	/**
	 * filter condition: equal ignore case if value type is String
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter eqIgnoreCase(String fieldName, Object value);

	/**
	 * filter condition: not equal
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter ne(String fieldName, Object value);

	/**
	 * filter condition: not equal ignore case if value type is String
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter neIgnoreCase(String fieldName, Object value);

	/**
	 * filter condition: greater than
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter gt(String fieldName, Object value);

	/**
	 * filter condition: greater and equal
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter ge(String fieldName, Object value);

	/**
	 * filter condition: lower than
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter lt(String fieldName, Object value);

	/**
	 * filter condition: lower and equal
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter le(String fieldName, Object value);

	/**
	 * filter condition: between
	 * 
	 * @param fieldName
	 * @param lVlaue
	 * @param rValue
	 * @param <code>comparator</code>
	 * @return
	 */
	public IFilter between(String fieldName, Object lVlaue, Object rValue);

	/**
	 * filter condition: not between
	 * 
	 * @param fieldName
	 * @param lVlaue
	 * @param rValue
	 * @param <code>comparator</code>
	 * @return
	 */
	public IFilter notBetween(String fieldName, Object lVlaue, Object rValue);

	/**
	 * filter condition: in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter in(String fieldName, Object[] values);

	/**
	 * filter condition: not in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter notIn(String fieldName, Collection<?> values);

	/**
	 * filter condition: in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter in(String fieldName, Collection<?> values);

	/**
	 * filter condition: not in
	 * 
	 * @param fieldName
	 * @param values
	 * @param comparator
	 * @return
	 */
	public IFilter notIn(String fieldName, Object[] values);

	/**
	 * filter condition: empty
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter empty(String fieldName);
	
	/**
	 * filter condition: not empty
	 * @param fieldName
	 * @return
	 */
	public IFilter notEmpty(String fieldName);
	

	/**
	 * filter condition: null
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter isNull(String fieldName);
	
	/**
	 * filter condition: not null
	 * 
	 * @param fieldName
	 * @return
	 */
	public IFilter notNull(String fieldName);

	/**
	 * filter condition: null
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter like(String fieldName, Object value);

	/**
	 * filter condition: null
	 * 
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public IFilter notLike(String fieldName, Object value);

	/**
	 * <p>
	 * return the filterCase list
	 * </p>
	 * 
	 * @return
	 */
	public List<FilterCase> getFilterCases();

	/**
	 * filter case for different filter method, such as "ne"
	 */
	public static class FilterCase {

		public enum CaseType {
			UNIT("unit"), GROUP("group");
			private String value;

			public String getValue() {
				return value;
			}

			private CaseType(String value) {
				this.value = value;
			}

		}

		/** the type of filter */
		private Operator filterType;

		/**and ,or*/
		private Relation groupType;

		/** case type,it can be GROUP or UNIT */
		private CaseType caseType;

		/** the field name to filter */
		private String fieldName;

		/** filter value */
		private Object value;
		
		public Operator getFilterType() {
			return filterType;
		}

		public void setFilterType(Operator filterType) {
			this.filterType = filterType;
		}

		public Relation getGroupType() {
			return groupType;
		}

		public void setGroupType(Relation groupType) {
			this.groupType = groupType;
		}

		public CaseType getCaseType() {
			return caseType;
		}

		public void setCaseType(CaseType caseType) {
			this.caseType = caseType;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

	};

}
