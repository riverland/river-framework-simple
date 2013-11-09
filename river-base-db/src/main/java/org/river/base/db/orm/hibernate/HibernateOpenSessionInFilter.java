package org.river.base.db.orm.hibernate;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.orm.hibernate3.support.OpenSessionInViewFilter;
/**
 * 
 * @author river
 * 2010/08/30
 */
public class HibernateOpenSessionInFilter extends OpenSessionInViewFilter {

	public static final String EXCLUDE_SUFFIXS_NAME = "excludeSuffixs";
	public static final String INCLUDE_SUFFIXS_NAME = "includeSuffixs";
	private static final String[] DEFAULT_EXCLUDE_SUFFIXS = { ".js", ".css",
			".jpg", ".gif" };
	private static final String[] DEFAULT_INCLUDE_SUFFIXS = new String[0];
	private String[] excludeSuffixs;
	private String[] includeSuffixs;

	public HibernateOpenSessionInFilter() {
		this.excludeSuffixs = DEFAULT_EXCLUDE_SUFFIXS;
		
		this.includeSuffixs = DEFAULT_INCLUDE_SUFFIXS;
	}

	protected boolean shouldNotFilter(HttpServletRequest request)
			throws ServletException {
		String suffix;
		String fullPath = request.getServletPath();
		String path = StringUtils.substringBefore(fullPath, "?");
		path = path.toLowerCase();

		if (this.includeSuffixs.length > 0) {
			int length = includeSuffixs.length;
			for (int i = 0; i < length; i++) {
				suffix = includeSuffixs[i];
				if (path.endsWith(suffix)) {
					return false;
				}
			}
			return true;
		}

		int len = excludeSuffixs.length;
		for (int i = 0; i < len; i++) {
			suffix = excludeSuffixs[i];
			if (path.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}

	protected void initFilterBean() throws ServletException {
		String includeSuffixStr = getFilterConfig().getInitParameter(
				"includeSuffixs");
		if (StringUtils.isNotBlank(includeSuffixStr)) {
			String[] configIncludeSuffixs = includeSuffixStr.split(",");
			List<String> suffixs = new ArrayList<String>();
			for (int i = 0; i < configIncludeSuffixs.length; ++i) {
				String suffix = configIncludeSuffixs[i].trim().toLowerCase();
				if (suffix.length() > 0) {
					suffixs.add(suffix);
				}
			}

			this.includeSuffixs = ((String[]) suffixs.toArray(new String[0]));
		}

		String excludeSuffixStr = getFilterConfig().getInitParameter(
				"excludeSuffixs");
		if (StringUtils.isNotBlank(excludeSuffixStr)) {
			String[] configExcludeSuffixs = excludeSuffixStr.split(",");
			List<String> suffixs = new ArrayList<String>();
			for (int i = 0; i < configExcludeSuffixs.length; ++i) {
				String suffix = configExcludeSuffixs[i].trim().toLowerCase();
				if (suffix.length() > 0)
					suffixs.add(suffix);
			}

			this.excludeSuffixs = ((String[]) suffixs.toArray(new String[0]));
		}
	}

}
