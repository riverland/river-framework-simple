package org.river.base.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.river.base.entity.IFilter.FilterCase.CaseType;
import org.river.base.entity.enumaration.Operator;
import org.river.base.entity.enumaration.Relation;


/**
 * <p>
 * the default implement of filter
 * </p>
 * 
 * @author river 2010/05/31
 */
public class EntityFilter implements IFilter {

	protected List<FilterCase> filterCases = new ArrayList<FilterCase>();
	
	private EntityFilter(){
		super();
	}

	public static IFilter newInstance() {
		return new EntityFilter();
	}

	public IFilter and(IFilter filter) {
		FilterCase filterCase = new FilterCase();
		filterCase.setCaseType(CaseType.GROUP);
		filterCase.setGroupType(Relation.AND);
		filterCase.setValue(filter.getFilterCases());
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter between(String fieldName, Object lVlaue, Object rValue) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFieldName(fieldName);
		filterCase.setFilterType(Operator.BETWEEN);
		
		Map<String,Object> values=new HashMap<String,Object>();
		values.put(IFilter.LEFT_VALUE, lVlaue);
		values.put(IFilter.RIGHT_VALUE, rValue);
		filterCase.setValue(values);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter empty(String fieldName) {
		FilterCase filterCase = this.buildUnitCase();
		filterCase.setFieldName(fieldName);
		filterCase.setFilterType(Operator.EMPTY);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter eq(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFieldName(fieldName);
		filterCase.setFilterType(Operator.EQ);	
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter eqIgnoreCase(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.EQIGNORECASE);
		filterCase.setFieldName(fieldName);		
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter ge(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.GE);
		filterCase.setFieldName(fieldName);		
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter gt(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.GT);
		filterCase.setFieldName(fieldName);		
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter in(String fieldName, Object[] values) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.IN);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(Arrays.asList(values));
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter in(String fieldName, Collection<?> values) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.IN);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(values);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter isNull(String fieldName) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NULL);
		filterCase.setFieldName(fieldName);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter le(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.LE);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter like(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.LIKE);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter lt(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.LT);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter ne(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NE);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter neIgnoreCase(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NEIGNORECASE);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter notBetween(String fieldName, Object lVlaue, Object rValue) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFieldName(fieldName);
		filterCase.setFilterType(Operator.NOTBETWEEN);
		
		Map<String,Object> values=new HashMap<String,Object>();
		values.put(IFilter.LEFT_VALUE, lVlaue);
		values.put(IFilter.RIGHT_VALUE, rValue);
		filterCase.setValue(values);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter notIn(String fieldName, Collection<?> values) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NOTIN);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(values);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter notIn(String fieldName, Object[] values) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NOTIN);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(Arrays.asList(values));
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter notLike(String fieldName, Object value) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NOTLIKE);
		filterCase.setFieldName(fieldName);			
		filterCase.setValue(value);
		this.filterCases.add(filterCase);
		return this;
	}
	
	public IFilter notEmpty(String fieldName) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NOTEMPTY);
		filterCase.setFieldName(fieldName);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter notNull(String fieldName) {
		FilterCase filterCase =this.buildUnitCase();
		filterCase.setFilterType(Operator.NOTEMPTY);
		filterCase.setFieldName(fieldName);
		this.filterCases.add(filterCase);
		return this;
	}

	public IFilter or(IFilter filter) {
		FilterCase filterCase = new FilterCase();
		filterCase.setCaseType(CaseType.GROUP);
		filterCase.setGroupType(Relation.OR);
		filterCase.setValue(filter.getFilterCases());
		this.filterCases.add(filterCase);
		return this;
	}

	public List<FilterCase> getFilterCases() {
		return this.filterCases;
	}
	
	//build the default case
	private FilterCase buildUnitCase(){
		FilterCase filterCase = new FilterCase();
		filterCase.setCaseType(CaseType.UNIT);
		filterCase.setGroupType(Relation.AND);
		return filterCase;
	}

	@Override
	public boolean isFilterEmpty() {
		return this.filterCases==null||this.filterCases.isEmpty();
	}


}
