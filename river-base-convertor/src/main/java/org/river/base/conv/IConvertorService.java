/*
 * @(#)IConvertorService.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv;

/**
 * <p>
 * 动态数据转换服务
 * @author River
 * @date 2013-4-8
 */
public interface IConvertorService
{
    /**
     * <p>
     * 转换数据格式
     * @param convJsName 转换函数名称
     * @param convSrc 要转换的数据源
     * @return 返回的已转换数据
     */
    public String convert(String convFuncName,String ... args);

    /**
     * <p>
     * 获取转换上下文
     * @return
     */
    public IConvContext getConvContext();
}
