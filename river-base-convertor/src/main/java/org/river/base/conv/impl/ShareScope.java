/*
 * @(#)ThreadScope.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv.impl;

import org.mozilla.javascript.Scriptable;

/**
 * <p>
 * js scope资源
 * @author River
 * @date 2013-8-5
 */
public class ShareScope
{
    private Long key;
    
    private Scriptable scope;  

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
    }

    public Scriptable getScope() {
        return scope;
    }

    public void setScope(Scriptable scope) {
        this.scope = scope;
    }
}
