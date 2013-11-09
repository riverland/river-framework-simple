package org.river.base.entity.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public class ExpressionExecutor {	
	@SuppressWarnings("unchecked")
	public static boolean eq(Object lValue, Object rValue) throws Exception {
		boolean eq = false;
		if(lValue==null&&rValue==null){
			eq=true;
		}else if(lValue==null||rValue==null){
			eq=false;
		}else if (lValue instanceof Number && rValue instanceof Number) {
			BigDecimal lDecimal = new BigDecimal(lValue.toString());
			BigDecimal rDecimal = new BigDecimal(rValue.toString());
			if (lDecimal.compareTo(rDecimal) == 0) {
				eq = true;
			}
		} else if (lValue instanceof String && rValue instanceof String) {
			eq = lValue.equals(rValue);
		} else if (lValue instanceof Date && rValue instanceof Date) {
			eq=lValue.equals(rValue);
		} else if (lValue instanceof Comparable<?>
				&& rValue instanceof Comparable<?>) {
			Comparable lc = (Comparable) lValue;
			Comparable rc = (Comparable) rValue;
			if (lc.compareTo(rc) == 0) {
				eq = true;
			}
		} else {
			throw new Exception(
					"different type between left and right value or the value type is not comparable");
		}
		return eq;
	}

	public static boolean eqIgnoreCase(Object lValue, Object rValue) {
		boolean eq = false;
		String lv = (String) lValue;
		String rv = (String) rValue;
		if (lv == null) {
			if (rv == null)
				eq = true;
		} else {
			eq = lv.equalsIgnoreCase(rv);
		}
		return eq;
	}

	public static boolean ne(Object lValue, Object rValue) throws Exception {
		return !eq(lValue, rValue);
	}

	public static boolean neIgnoreCase(Object lValue, Object rValue) {
		return !eqIgnoreCase(lValue, rValue);
	}

	@SuppressWarnings("unchecked")
	public static boolean gt(Object lValue, Object rValue) throws Exception {
		boolean gt = false;
		if(lValue!=null&&rValue==null){
			gt=true;
		}else if (lValue instanceof Number && rValue instanceof Number) {
			BigDecimal lDecimal = new BigDecimal(lValue.toString());
			BigDecimal rDecimal = new BigDecimal(rValue.toString());
			if (lDecimal.compareTo(rDecimal) == 1) {
				gt = true;
			}
		} else if (lValue instanceof String && rValue instanceof String) {
			String lv = (String) lValue;
			String rv = (String) rValue;
			int i=lv.compareTo(rv);
			if (i >0) {
				gt = true;
			}
		} else if (lValue instanceof Date && rValue instanceof Date) {
			Date lv = (Date) lValue;
			Date rv = (Date) rValue;
			if (lv.after(rv)) {
				gt = true;
			}
		} else if (lValue instanceof Comparable<?>
				&& rValue instanceof Comparable<?>) {
			Comparable lc = (Comparable) lValue;
			Comparable rc = (Comparable) rValue;
			if (lc.compareTo(rc) == 1) {
				gt = true;
			}
		} else {
			throw new Exception(
					"different type between left and right value or the value type is not comparable");
		}
		return gt;
	}

	@SuppressWarnings("unchecked")
	public static boolean ge(Object lValue, Object rValue) throws Exception {
		boolean ge = false;
		if (lValue instanceof Number && rValue instanceof Number) {
			BigDecimal lDecimal = new BigDecimal(lValue.toString());
			BigDecimal rDecimal = new BigDecimal(rValue.toString());
			if (lDecimal.compareTo(rDecimal) > -1) {
				ge = true;
			}
		} else if (lValue instanceof String && rValue instanceof String) {
			String lv = (String) lValue;
			String rv = (String) rValue;
			if (lv.compareTo(rv) > -1) {
				ge = true;
			}
		} else if (lValue instanceof Date && rValue instanceof Date) {
			Date lv = (Date) lValue;
			Date rv = (Date) rValue;
			if (!lv.before(rv)) {
				ge = true;
			}
		} else if (lValue instanceof Comparable<?>
				&& rValue instanceof Comparable<?>) {
			Comparable lc = (Comparable) lValue;
			Comparable rc = (Comparable) rValue;
			if (lc.compareTo(rc) > -1) {
				ge = true;
			}
		} else {
			throw new Exception(
					"different type between left and right value or the value type is not comparable");
		}
		return ge;
	}
	
	public static boolean lt(Object lValue,Object rValue) throws Exception{
		return !ge(lValue,rValue);
	}
	
	public static boolean le(Object lValue,Object rValue) throws Exception{
	   return !gt(lValue,rValue);
	}
	
	public static boolean between(Object value, Object lVlaue, Object rValue) throws Exception{
		boolean between=false;
		boolean ge=ge(value,lVlaue);
		boolean le=le(value,rValue);
		between=ge&&le;
		return between;
	}	
	
	public static boolean notBetween(Object value, Object lVlaue, Object rValue) throws Exception{
		boolean between=false;
		between=lt(value,lVlaue)&&gt(value,rValue);
		return between;
	}
	
	public static boolean in(Object value,Object[] scope) throws Exception{
		boolean in=false;
		for(Object tmp:scope){
			if(eq(value,tmp)){
				in=true;
				break;
			}
		}
		return in;
	}
	
	public static boolean in(Object value,Collection<?> scope) throws Exception{
		boolean in=false;
		for(Object tmp:scope){
			if(eq(value,tmp)){
				in=true;
				break;
			}
		}
		return in;
	}
	
	public static boolean notIn(Object value,Object[] scope) throws Exception{
		return !in(value,scope);
	}
	
	public static boolean notIn(Object value,Collection<?> scope) throws Exception{
		return !in(value,scope);
	}
	
	public static boolean empty(Object value){
		boolean empty=false;
		if(value==null){
			empty=true;
		}
		if(value instanceof String&&"".equals(value)){
			empty=true;
		}		
		return empty;
	}
	
	public static boolean like(Object value,Object partten) throws Exception{
		boolean like=false;
		String v=(String)value;
		if(value instanceof String){
			String op=(String)partten;
			String cp=op.replaceAll("%", "");
			if(op.startsWith("%")&&op.endsWith("%")){
				if(v.contains(cp)){
					like=true;
				}
			}else if(op.startsWith("%")){
				if(v.endsWith(cp)){
					like=true;
				}
			}else if(op.endsWith("%")){
				if(v.startsWith(cp)){
					like=true;
				}
			}else if(value.equals(op)){
				like=true;
			}
			
		}else{
			throw new Exception("like operator dosen't accept param type as not String!");
		}
		return like;
	}
	
	public static boolean notLike(Object value,Object partten) throws Exception{
		return !like(value,partten);
	}
	
	public static boolean isNull(Object value){
		return value==null;
	}
	
	
}
