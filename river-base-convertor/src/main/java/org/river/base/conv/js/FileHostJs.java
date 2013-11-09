/*
 * @(#)FileHostJs.java	2013
 *
 * Copyright (c) 2013, River and/or its affiliates. All rights reserved.
 * River PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package org.river.base.conv.js;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.river.base.conv.AbstractHostJs;

/**
 * <p>
 * 本地文件JS源码
 * @author River
 * @date 2013-4-9
 */
public class FileHostJs extends AbstractHostJs
{
    private static final Logger log=LoggerFactory.getLogger(FileHostJs.class);
    
    /**js源代码存放路径*/
    private String jsPath="";

    /* (non-Javadoc)
     * @see org.river.base.conv.AbstractHostJs#doLoad()
     */
    @Override
    protected Map<String, String> doLoad() throws Exception {
        if(jsPath==null||"".equals(jsPath)){
            log.error("convert js source path is null");
            throw new Exception("jsPath can't be null");
        }
        Map<String, String> cache=new HashMap<String, String>();
        
        File file=new File(jsPath);
        cache=this.loadDir(file, cache);
        
        return cache;
    }
    
    /**
     * <p>
     * 加载文件
     * @param file
     * @param cache
     * @return
     */
    private Map<String, String> loadFile(File file,Map<String, String> cache){
        if(cache==null)
            cache=new HashMap<String, String>();
        if(file==null||!file.getName().endsWith(SUFFIX_JS))
            return cache;
        
        InputStream ins=null;
        try {
            ins=new FileInputStream(file);
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            final int bufferSuze=1024;
            byte buffer[]=new byte[bufferSuze];
            int len=0;
            while((len=ins.read(buffer, 0, bufferSuze))>0){
                baos.write(buffer, 0, len);
            }
            String src=new String(baos.toByteArray());
            cache.put(file.getName(), src);
            ins.close();
        } catch (Exception e) {
            log.error(e.getMessage(),e);
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
    
    /**
     * <p>
     * 加载路径
     * @param file
     * @param cache
     * @return
     */
    private Map<String, String>  loadDir(File file,Map<String, String> cache){
        if(cache==null)
            cache=new HashMap<String, String>();
        if(file.exists()&&file.isDirectory()){
            File files[]=file.listFiles();
            if(files==null||files.length==0)
                return cache;
            
            for(File tmp:files){
                cache=this.loadDir(tmp, cache);
            }
        }else if(file.exists()){
            this.loadFile(file, cache);
        }
        return cache;
    }
    
    
    public String getJsPath() {
        return jsPath;
    }

    public void setJsPath(String jsPath) {
        this.jsPath = jsPath;
    }

}
