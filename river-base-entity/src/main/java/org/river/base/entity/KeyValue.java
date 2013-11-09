/*
 * @(#)KeyValue.java	2013
 *
 * Copyright (c) 2013, Nantian and/or its affiliates. All rights reserved.
 * NANTIAN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.entity;

/**
 * <p>
 *
 * @author River
 * @date 2013-3-20
 */
public class KeyValue
{
    public String key;
    
    public Object value;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
