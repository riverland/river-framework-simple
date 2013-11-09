package org.river.base.entity.util;

import java.util.Collection;
import java.util.Map;



import org.river.base.entity.IEntity;
import org.river.base.entity.IFilter;
import org.river.base.entity.IFilter.FilterCase;
import org.river.base.entity.enumaration.Operator;


class FilterExecutor {
	public static boolean excute(FilterCase filterCase,IEntity entity,Operator optor){
		return false;
	}
	@SuppressWarnings("unchecked")
	public static  boolean between(FilterCase filterCase,IEntity entity) throws Exception{
		boolean between=false;
		Object value=entity.get(filterCase.getFieldName());
    	Map<String,Object> vMap=(Map<String, Object>) filterCase.getValue();
    	Object lValue=vMap.get(IFilter.LEFT_VALUE);
    	Object rValue=vMap.get(IFilter.RIGHT_VALUE);
    	between=ExpressionExecutor.between(value, lValue, rValue);
    	return between;
	}
	
	public static boolean notBetween(FilterCase filterCase,IEntity entity) throws Exception{
		return !between(filterCase,entity);
	}
	
	public static boolean eq(FilterCase filterCase,IEntity entity) throws Exception{
		Object lValue=entity.get(filterCase.getFieldName());
		Object rValue=filterCase.getValue();
		return ExpressionExecutor.eq(lValue, rValue);
	}
	
	public static boolean ne(FilterCase filterCase,IEntity entity) throws Exception{
		return eq(filterCase,entity);
	}
	
	public static boolean eqIgnoreCase(FilterCase filterCase,IEntity entity) throws Exception{
		Object lValue=entity.get(filterCase.getFieldName());
		Object rValue=filterCase.getValue();
		return ExpressionExecutor.eqIgnoreCase(lValue, rValue);
	}
	
	public static boolean neIgnoreCase(FilterCase filterCase,IEntity entity)throws Exception{
		return !eqIgnoreCase(filterCase,entity);
	}
	
	public static boolean gt(FilterCase filterCase,IEntity entity) throws Exception{
		Object lValue=entity.get(filterCase.getFieldName());
		Object rValue=filterCase.getValue();
		return ExpressionExecutor.gt(lValue, rValue);
	}
	
	public static boolean lt(FilterCase filterCase,IEntity entity) throws Exception{
		Object lValue=entity.get(filterCase.getFieldName());
		Object rValue=filterCase.getValue();
		return ExpressionExecutor.lt(lValue, rValue);
	}
	
	public static boolean ge(FilterCase filterCase,IEntity entity) throws Exception{
		return !lt(filterCase,entity);
	}
	
	public static boolean le(FilterCase filterCase,IEntity entity) throws Exception{
		return !gt(filterCase,entity);
	}
	
	public static boolean empty(FilterCase filterCase,IEntity entity) throws Exception{
		Object value=entity.get(filterCase.getFieldName());
		return ExpressionExecutor.empty(value);
	}
	
	public static boolean isNull(FilterCase filterCase,IEntity entity) throws Exception{
		Object value=entity.get(filterCase.getFieldName());
		return ExpressionExecutor.isNull(value);
	}
	
	@SuppressWarnings("unchecked")
	public static boolean in(FilterCase filterCase,IEntity entity) throws Exception{
		boolean in=false;
		Object lValue=entity.get(filterCase.getFieldName());
		Object rValue=filterCase.getValue();
		if(rValue instanceof Object[]){
			in=ExpressionExecutor.in(lValue, (Object[]) rValue);
		}else if(rValue instanceof Collection){
			in=ExpressionExecutor.in(lValue, (Object[]) rValue);
		}else{
			throw new Exception("illegal params error");
		}
		return in;
	}
	
	public static boolean notIn(FilterCase filterCase,IEntity entity) throws Exception{
		return !in(filterCase,entity);
	}
	
	public static boolean like(FilterCase filterCase,IEntity entity) throws Exception{
		Object lValue=entity.get(filterCase.getFieldName());
		Object rValue=filterCase.getValue();
		return ExpressionExecutor.like(lValue, rValue);
	}
	
	public static boolean notLike(FilterCase filterCase,IEntity entity) throws Exception{
		return !like(filterCase,entity);
	}
	
}
