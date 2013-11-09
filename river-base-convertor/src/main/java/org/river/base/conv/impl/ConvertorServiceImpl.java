/*
 * @(#)ConvertorServiceImpl.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv.impl;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.Undefined;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.river.base.conv.IConvContext;
import org.river.base.conv.IConvertorService;

/**
 * <p>
 * 动态数据转换服务实现
 * @author River
 * @date 2013-4-10
 */
public class ConvertorServiceImpl implements IConvertorService
{
    private static final Logger log=LoggerFactory.getLogger(ConvertorServiceImpl.class);
    
    private IConvContext convContext;

    /* (non-Javadoc)
     * @see org.river.base.conv.IConvertorService#convert(java.lang.String, java.lang.String)
     */
    @Override
    public String convert(String convFuncName,String ... args) {
        
        
        Context ctx = Context.enter();
        ctx.setLanguageVersion(Context.VERSION_1_8);
        String dest="";
        ShareScope sharedScope=null;  
        try{        
            sharedScope=convContext.getShareScope();  
            Scriptable threadScope = ctx.initStandardObjects();
            threadScope.setPrototype(sharedScope.getScope());
            threadScope.setParentScope(null);
            
            Function func=convContext.getFunction(convFuncName,sharedScope);
            if(func==null){
                log.error("Function["+convFuncName+"] is undefined");
            }
            
            Object rt=func.call(ctx, threadScope, func, args);
            if(!(rt instanceof Undefined)){
                dest=(String) rt;
            }
            
        }catch(Throwable e){
            log.error(e.getMessage(),e);
        }finally{
            Context.exit();
            convContext.release(sharedScope);
        }
        
        
        return dest;
    }


    /* (non-Javadoc)
     * @see org.river.base.conv.IConvertorService#getContext()
     */
    @Override
    public IConvContext getConvContext() {
        return convContext;
    }

    public void setConvContext(IConvContext convContext) {
        this.convContext = convContext;
    }

}
