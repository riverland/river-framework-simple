package org.river.base.db.orm;
/**
 * <p>
 * 避免注入工具类
 * @author river
 * @date 20121106
 */
public class InjectFilter {
	/**
	 * <p>
	 * 避免sql注入
	 * @param param
	 * @return
	 */
	public static String filterSqlInject(String param){
		InvalidSqlChar [] invalids=InvalidSqlChar.values();
		for(InvalidSqlChar c : invalids){
			param=param.replaceAll(c.getValue().invalid, c.getValue().valid);
		}
		return param;
	}
	
	
	/**
	 * <p>
	 * 非法sql字符
	 * @author river
	 * @date 20121106
	 */
	public static enum InvalidSqlChar{
		SINGLE_QUOTE(new Pair("'","''")),COMMENT(new Pair("--",""));
		
		private Pair value;

		public Pair getValue() {
			return value;
		}
	
		private InvalidSqlChar(Pair value) {
			this.value = value;
		}
	}
	
	//非法sql字符对
	private static class Pair{
		private String invalid;
		private String valid;
		public Pair(String invalid, String valid) {
			this.invalid = invalid;
			this.valid = valid;
		}		
	}
	
	//测试主函数
	public static void main(String args[]){
		System.out.println(InjectFilter.filterSqlInject("ha'llo--h-a"));
	}
	
}
