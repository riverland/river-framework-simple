/*
 * @(#)AbstractHostJs.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * js源码接口抽象
 * @author River
 * @date 2013-4-9
 */
public abstract class AbstractHostJs implements IHostJs
{
    private static final Logger log=LoggerFactory.getLogger(AbstractHostJs.class);
    
    /**js源码缓存*/
    private Map<String,String> hostJsCache=new HashMap<String,String>();

    /**
     * <p>
     * 实现加载源码
     * @throws Exception
     */
    protected abstract Map<String,String> doLoad() throws Exception;
    
    /**
     * <p>
     * 初始化
     */
    protected void initialize(){
        this.load();
    }

    /* (non-Javadoc)
     * @see org.river.base.conv.IHostJs#allHostJs()
     */
    public final Map<String, String> allHostJs() {
        return this.hostJsCache;
    }

    /* (non-Javadoc)
     * @see org.river.base.conv.IHostJs#getHostJs(java.lang.String)
     */
    public String getHostJs(String name) {
        return this.hostJsCache.get(name);
    }

    public synchronized void load() {
        Map<String,String> newCache=null;
        try {
            newCache=this.doLoad();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        newCache=this.loadBaseJs(newCache);
        
        if(newCache!=null&&!newCache.isEmpty()){            
            this.hostJsCache=newCache;
        }        
    }  
    
    /**
     * <p>
     * 加载基础源码
     * @param cache
     */
    private Map<String,String> loadBaseJs(Map<String,String> cache){
        if(cache==null){
            cache=new HashMap<String,String>();
        }
        InputStream ins=null;
        
        try{
            ins=AbstractHostJs.class.getResourceAsStream(BASE_JS);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            final int bufferSuze=1024;
            byte buffer[]=new byte[bufferSuze];
            int len=0;
            while((len=ins.read(buffer, 0, bufferSuze))>0){
                baos.write(buffer, 0, len);
            }
            String src=new String(baos.toByteArray());
            cache.put(BASE_JS_NAME, src);
            ins.close();
            ins.close();
        }catch(Throwable e){
            log.error(e.getMessage(), e);
        }finally{
            if(ins!=null){
                try{
                    ins.close();
                }catch(Throwable e){
                    //do nothing
                }
            }
        }
        
        return cache;
    }
}
