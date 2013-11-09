/*
 * @(#)IHostJs.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv;

import java.util.Map;

/**
 * <p>
 * js源码接口
 * @author River
 * @date 2013-4-8
 */
public interface IHostJs
{
    public static final String SUFFIX_JS=".js";
    public static final String BASE_JS="/com/River/base/conv/js/Base.js";
    public static final String BASE_JS_NAME="BASE";
    
    /**
     * <p>
     * 获取所有js源码
     * @return
     */
    public Map<String,String> allHostJs();
    
    
    /**
     * <p>
     * 获取指定名称源码
     * @param name
     * @return
     */
    public String getHostJs(String name);
    
    /**
     * <p>
     * 加载缓存
     */
    public void load();
}
