package org.river.base.db.test;

import org.hibernate.SessionFactory;
import org.springframework.test.AbstractTransactionalSpringContextTests;

public class HibernateTxTestCase extends AbstractTransactionalSpringContextTests {
	
	public static final String TEST_CONTEXT_PATH="/applicationContext-test.xml";
	
	public static final String SESSIONFACTORY_BEAN_NAME="sessionFactory";
	
	protected String getConfigPath() {
		return TEST_CONTEXT_PATH;
	}

	protected void prepareTestInstance() throws Exception {
		setAutowireMode(AbstractTransactionalSpringContextTests.AUTOWIRE_BY_NAME);
		super.prepareTestInstance();		
	}

	protected void flush() {
		flush(SESSIONFACTORY_BEAN_NAME);
	}

	protected void flush(String sessionFactoryName) {
		((SessionFactory) applicationContext.getBean(sessionFactoryName))
				.getCurrentSession().flush();
	}

	protected void evict(Object entity) {
		evict(entity, SESSIONFACTORY_BEAN_NAME);
	}

	protected void evict(Object entity, String sessionFactoryName) {
		((SessionFactory) applicationContext.getBean(sessionFactoryName))
				.getCurrentSession().evict(entity);
	}
}
