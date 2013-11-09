package org.river.base.db.orm;
/**
 * <p>
 * current thread session register interface
 * @author river
 * @date 20120927
 */
public interface IThreadSessionRegister {
	/**
	 * <p>
	 * bind session to the current thread
	 * @return void
	 */
	public void bind();

	/**
	 * <p>
	 * unbind session from the current thread
	 * @return void
	 */
	public void unbind();
}
