package org.river.base.db.test;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SpringTestCase extends
		AbstractDependencyInjectionSpringContextTests {
	public static final String TEST_CONTEXT_PATH = "/applicationContext-test.xml";

	public static final String SESSIONFACTORY_BEAN_NAME = "sessionFactory";


	protected String getConfigPath() {
		return TEST_CONTEXT_PATH;
	}

	protected void prepareTestInstance() throws Exception {
		setAutowireMode(1);
		super.prepareTestInstance();
	}
	
}
