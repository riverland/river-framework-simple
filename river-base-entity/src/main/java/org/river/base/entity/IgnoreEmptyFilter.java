package org.river.base.entity;

import java.util.Collection;
import java.util.List;

/**
 * entity filter which ignores empty fields when project the entities resources
 * TODO this class has not implemented any method yet, so those must be implemented when needed
 * @author river
 * 2010/09/30
 */
public class IgnoreEmptyFilter implements IFilter {

	@Override
	public IFilter and(IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter between(String fieldName, Object lVlaue, Object rValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter empty(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter eq(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter eqIgnoreCase(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter ge(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FilterCase> getFilterCases() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter gt(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter in(String fieldName, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter in(String fieldName, Collection<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter isNull(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter le(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter like(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter lt(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter ne(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter neIgnoreCase(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter notBetween(String fieldName, Object lVlaue, Object rValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter notEmpty(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter notIn(String fieldName, Collection<?> values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter notIn(String fieldName, Object[] values) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter notLike(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter notNull(String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IFilter or(IFilter filter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isFilterEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

}
