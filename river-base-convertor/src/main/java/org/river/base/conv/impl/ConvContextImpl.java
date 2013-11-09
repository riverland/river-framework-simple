/*
 * @(#)ConvContextImpl.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.Scriptable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.river.base.conv.IConvContext;
import org.river.base.conv.IHostJs;

/**
 * <p>
 * 动态数据转换上下文接口实现
 * 
 * @author River
 * @date 2013-4-9
 */
public class ConvContextImpl implements IConvContext
{
    private static Logger log = LoggerFactory.getLogger(ConvContextImpl.class);
    private static String LOGGER_NAME="log";
    private static String SHARE_SCOPE_NAME="shareScope";
    /** 共享上下文 **/
    private List<ShareScope> pool;
    private List<ShareScope> idlePool;
    private Map<Long,ShareScope> usedPool;

    private String scopeSize;
    private IHostJs hostJs;

    /*
     * (non-Javadoc)
     * 
     * @see org.river.base.conv.IConvContext#initialize()
     */
    public void initialize() throws Exception {
        this.rebuild();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.river.base.conv.IConvContext#getContext()
     */
    public Context getContext() throws Exception {
        return this.getContext();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.river.base.conv.IConvContext#rebuild()
     */
    public void rebuild() throws Exception {        
        pool=new ArrayList<ShareScope>();
        idlePool=new ArrayList<ShareScope>();
        usedPool=new HashMap<Long,ShareScope>();
        
        for(int i=0;i<Integer.valueOf(scopeSize);i++){
            ShareScope scope=this.buildScope((long)i);
            pool.add(scope);
            idlePool.add(scope);
        }

    }
    
    private ShareScope buildScope(Long i){
        Context context = Context.enter();
        context.setLanguageVersion(Context.VERSION_1_8);
        Scriptable shareScope = context.initStandardObjects(null);
        shareScope.put(LOGGER_NAME, shareScope, log);
        
        Map<String,String> cacheJsMap=this.hostJs.allHostJs();
        Collection<String> cacheJsSet= cacheJsMap.values();
        Iterator<String> iter=cacheJsSet.iterator();
        while(iter.hasNext()){
            String jsSrc=iter.next();
            Script sc=context.compileString(jsSrc, SHARE_SCOPE_NAME, 1, null);
            sc.exec(context, shareScope);            
        } 
        Context.exit();
        
        ShareScope sc=new ShareScope();
        sc.setKey(i);
        sc.setScope(shareScope);
        return sc;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.river.base.conv.IConvContext#getShareScope()
     */
    public ShareScope getShareScope() throws Exception {
        ShareScope scope=null;
        synchronized(idlePool){
            if(idlePool.isEmpty()){
                idlePool.wait();
            }else{                
                scope=idlePool.remove(0);
                usedPool.put(scope.getKey(), scope);
            }
        }
        return scope;
    }
    

    /* (non-Javadoc)
     * @see org.river.base.conv.IConvContext#getShareScope(long)
     */
    @Override
    public ShareScope getShareScope(long timeout) throws Exception {
        ShareScope scope=null;
        synchronized(idlePool){
            if(idlePool.isEmpty()){
                idlePool.wait(timeout);
            }else{
                scope=idlePool.remove(0);
                usedPool.put(scope.getKey(), scope);
            }
        }
        return scope;
    }
    

    /* (non-Javadoc)
     * @see org.river.base.conv.IConvContext#release(org.river.base.conv.impl.ShareScope)
     */
    @Override
    public void release(ShareScope scope) {
        synchronized(idlePool){
            usedPool.remove(scope.getKey());
            idlePool.add(scope);
            idlePool.notifyAll();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.river.base.conv.IConvContext#destroy()
     */
    @Override
    public void destroy() {
        Context.exit();
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.river.base.conv.IConvContext#getFunction(java.lang.String)
     */
    @Override
    public Function getFunction(String name,ShareScope scope) {
        Function func = null;
        if (name != null && !"".equals(name)) {
            func = (Function)scope.getScope().get(name, scope.getScope());
            if(func==null)
                log.warn("the convert function["+name+"] not existed");
        }
        return func;
    }


    /* (non-Javadoc)
     * @see org.river.base.conv.IConvContext#put(java.lang.String, java.lang.Object)
     */
    @Override
    public void put(String name, Object value) {
        synchronized(pool){
            for(ShareScope scope:pool){
                scope.getScope().put(name, scope.getScope(), value);  
            }
        }
              
    }
    
    
    public IHostJs getHostJs() {
        return hostJs;
    }
    public void setHostJs(IHostJs hostJs) {
        this.hostJs = hostJs;
    }
    public String getScopeSize() {
        return scopeSize;
    }

    public void setScopeSize(String scopeSize) {
        this.scopeSize = scopeSize;
    }
    
    public static void main(String args[]){
        System.out.println(String.format("%06d", 123));
        System.out.println(String.format("%-9s", "zhan"));
    }


}
