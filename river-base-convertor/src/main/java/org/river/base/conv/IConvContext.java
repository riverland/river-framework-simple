/*
 * @(#)IConvContext.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;

import org.river.base.conv.impl.ShareScope;

/**
 * <p>
 * 动态数据转换上下文
 * @author River
 * @date 2013-4-8
 */
public interface IConvContext
{
    /**
     * <p>
     * 初始化上下文
     * @throws Exception
     */
    public void initialize() throws Exception;
    
    /**
     * <p>
     * 获取上下文
     * @return
     * @throws Exception
     */
    public Context getContext() throws Exception;
    
    /**
     * <p>
     * 重建上下文
     * @throws Exception
     */
    public void rebuild() throws Exception;
    
    /**
     * <p>
     * 获取共享js上下文
     * @return
     * @throws Exception
     */
    public ShareScope getShareScope() throws Exception;
    
    /**
     * <p>
     * 获取共享js上下文
     * @return
     * @throws Exception
     */
    public ShareScope getShareScope(long timeout) throws Exception;
    
    /**
     * <p>
     * 释放共享js上下文
     * @return
     * @throws Exception
     */
    public void release(ShareScope scope);
    
    /**
     * <p>
     * 清除当前上下文，释放资源
     */
    public void destroy();
    
    /**
     * <p>
     * 获取转换函数
     * @param name
     * @return
     */
    public Function getFunction(String name,ShareScope scope);
    
    
    /**
     * <p>
     * 全局上下文中设置指定变量
     * @param name
     * @param value
     */
    public void put(String name,Object value);
}
