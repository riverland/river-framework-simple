package org.river.base.db.orm;
//package com.turbom.base.db.orm;
//
//import java.util.List;
//
//import com.turbom.base.entity.IFilter;
//import com.turbom.base.entity.IFilter.FilterCase;
//import com.turbom.base.entity.IFilter.FilterCase.CaseType;
//import com.turbom.base.entity.util.IFilterParser;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//public abstract class FilterParser implements IFilterParser {
//	private static Logger log = LoggerFactory.getLogger(FilterParser.class);
//	private static final String space = " ";
//	
//	private  String parseUnitCase(FilterCase filterCase){
//		
//	}
//	
//	private  String parseGroupCase(FilterCase filterCase){
//		
//	}
//
//	public String parse(IFilter filter) {
//		StringBuffer filterBuffer = new StringBuffer();
//		List<FilterCase> filterCases = filter.getFilterCases();
//		for (FilterCase tmp : filterCases) {
//			// filterBuffer.append(b)
//		}
//		return filterBuffer.toString();
//	}
//
//	private String parseCase(FilterCase filterCase) {
//		StringBuffer unitBuffer = new StringBuffer();
//		switch (filterCase.getCaseType()) {
//		case UNIT:
//			unitBuffer.append(this.parseUnitCase(filterCase));
//			break;
//		case GROUP:
//			unitBuffer.append(b);
////			unitBuffer.append(FilterParser.space);
////			unitBuffer.append(filterCase.getGroupType().getValue());
////			unitBuffer.append(FilterParser.space);
////			unitBuffer.append(filterCase.getFieldName());
////			unitBuffer.append(filterCase.getFilterType().)
//			
//		}
//		return unitBuffer.toString();
//	}
//	
//	
//
//}
