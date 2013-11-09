package org.river.base.threads.type;

import java.util.List;
/**
 * 消息对象
 * @author river
 *
 * @param <E>
 */
public class Message<E> {
	public byte [] data;
	public E dataObj;
	public Object arg0;
	public Object arg1;
	public List<?> args;
}
