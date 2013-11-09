package org.river.base.entity.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.river.base.entity.IEntity;
import org.river.base.entity.IFilter;
import org.river.base.entity.IProjection;
import org.river.base.entity.IFilter.FilterCase;
import org.river.base.entity.IFilter.FilterCase.CaseType;


public class Projection implements IProjection {

	public  List<IEntity> filter(Collection<IEntity> entities, IFilter filter) throws Exception {
		List<IEntity> rt=new ArrayList<IEntity>();
		for(IEntity tmp:entities){
			if(this.fit(tmp, filter)){
				rt.add(tmp);
			}
		}
		return rt;
	}

	public List<IEntity> filter(IFilter filter) {
		// TODO may be override  when entity source is clarified
		return null;
	}


	public boolean fit(IEntity entity, IFilter filter) throws Exception {
		boolean condition=true;
		List<FilterCase> filterCases = filter.getFilterCases();
		for(FilterCase tmp:filterCases){
			condition=condition&&this.computeUnitCondition(tmp, entity);
		}
		return condition;
	}

	private boolean computeUnitCondition(FilterCase filterCase, IEntity entity)
			throws Exception {
		boolean condition = true;
		if (CaseType.UNIT.getValue().equals(filterCase.getCaseType().getValue())) {
			switch (filterCase.getFilterType()) {
			case BETWEEN:
				condition = condition
						&& FilterExecutor.between(filterCase, entity);
				break;
			case EMPTY:
				condition = condition && FilterExecutor.empty(filterCase, entity);
				break;
			case NOTEMPTY:
				condition = condition && (!FilterExecutor.empty(filterCase, entity));
				break;
			case EQ:
				condition = condition && FilterExecutor.eq(filterCase, entity);
				break;
			case EQIGNORECASE:
				condition = condition
						&& FilterExecutor.eqIgnoreCase(filterCase, entity);
				break;
			case NE:
				condition = condition && FilterExecutor.ne(filterCase, entity);
				break;
			case NEIGNORECASE:
				condition = condition
						&& FilterExecutor.neIgnoreCase(filterCase, entity);
				break;
			case GT:
				condition = condition && FilterExecutor.gt(filterCase, entity);
				break;
			case GE:
				condition = condition && FilterExecutor.ge(filterCase, entity);
				break;
			case LT:
				condition = condition && FilterExecutor.lt(filterCase, entity);
				break;
			case LE:
				condition = condition && FilterExecutor.le(filterCase, entity);
				break;
			case NULL:
				condition = condition && FilterExecutor.isNull(filterCase, entity);
				break;
			case NOTNULL:
				condition = condition && (!FilterExecutor.isNull(filterCase, entity));
				break;
			case NOTIN:
				condition = condition && FilterExecutor.notIn(filterCase, entity);
				break;
			case LIKE:
				condition = condition && FilterExecutor.like(filterCase, entity);
				break;
			case NOTLIKE:
				condition = condition
						&& FilterExecutor.notLike(filterCase, entity);
				break;
			}
		} else {
			switch(filterCase.getGroupType()){
			    case AND:
			    	FilterCase andCase=(FilterCase) filterCase.getValue();
			    	condition=condition&&computeGroupCondition(andCase,entity);
			    	break;
			    case OR:
			    	FilterCase orCase=(FilterCase) filterCase.getValue();
			    	condition=condition||computeGroupCondition(orCase,entity);
			    	break;
			}
		}
		return condition;
	}
	
	@SuppressWarnings("unchecked")
	private  boolean computeGroupCondition(FilterCase filterCase, IEntity entity) throws Exception{
		boolean condition=true;
		List<FilterCase> filterCases=(List<FilterCase>) filterCase.getValue();		
		for(FilterCase tmp:filterCases){
			if(CaseType.UNIT.getValue().equals(filterCase.getCaseType().getValue())){
				condition=condition&&this.computeUnitCondition(tmp, entity);
			}else{
				condition=condition&&this.computeGroupCondition(tmp, entity);
			}			
		}
		return condition;
	}
}
