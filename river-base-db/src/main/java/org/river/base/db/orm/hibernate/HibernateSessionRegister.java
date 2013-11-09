package org.river.base.db.orm.hibernate;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.FlushMode;
import org.hibernate.Session;

import org.river.base.db.orm.IThreadSessionRegister;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate3.SessionFactoryUtils;
import org.springframework.orm.hibernate3.SessionHolder;
import org.springframework.transaction.support.TransactionSynchronizationManager;
/**
 * <p>
 * current thread session register interface
 * @author river
 * @date 20120927
 */
@Aspect
public class HibernateSessionRegister implements IThreadSessionRegister {
	private static final Logger log=LoggerFactory.getLogger(HibernateSessionRegister.class);
	private static final ThreadLocal<Integer> participate=new ThreadLocal<Integer>();
	private SessionFactory sessionFactory;

	
	@Override
	@Before(value = "@annotation(org.river.base.db.orm.Session)")
	public void bind() {
		if(participate.get()==null){
			participate.set(1);
		}
		
		if (TransactionSynchronizationManager.hasResource(sessionFactory)) {			
			participate.set(participate.get()+1);
		}else{
			log.debug("opening single Hibernate Session in thread["+Thread.currentThread().getName()+"]");
			Session session = getSession(sessionFactory);
			TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session));
		}

		log.debug("bind"+participate.get());
	}

	@Override
	@AfterReturning(value="@annotation(org.river.base.db.orm.Session)")
	public void unbind() {
		log.debug("unbind"+participate.get());
		participate.set(participate.get()-1);
		if (participate.get()<=0) {			
			SessionHolder sessionHolder =(SessionHolder) TransactionSynchronizationManager.unbindResource(sessionFactory);
			log.debug("Closing single Hibernate Session in thread["+Thread.currentThread().getName()+"]");
			closeSession(sessionHolder.getSession(), sessionFactory);			
		}
	}
	
	protected void closeSession(Session session, SessionFactory sessionFactory) {
		SessionFactoryUtils.closeSession(session);
	}
	
	protected Session getSession(SessionFactory sessionFactory) throws DataAccessResourceFailureException {
		Session session = SessionFactoryUtils.getSession(sessionFactory, true);
		session.setFlushMode(FlushMode.MANUAL);
		return session;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
