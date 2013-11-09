package org.river.base.entity;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * default implement of {@link IEntity} ,it can be super class of the entity
 * class which need entity interface supporting
 * </p>
 * 
 * @author river 2010/06/01
 */
public abstract class Entity implements IEntity
{

    public String deposit() {
        // TODO Auto-generated method stub
        return null;
    }

    public boolean isModified(String fieldName, String version) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean isModified(String fieldName) throws Exception {
        // TODO Auto-generated method stub
        return false;
    }

    public void fromEntity(IEntity entity) throws Exception {
        this.setAllFields(entity.toMap());
    }

    public void fromMap(Map<String, Object> mapValue) throws Exception {
        this.setAllFields(mapValue);
    }

    public Object get(String fieldName) throws Exception {
        Field field = this.getField(fieldName);
        field.setAccessible(true);
        return field.get(this);
    }

    public List<String> getAllFieldsNames() {
        Class<?> type = this.getType();
        List<String> fieldNames = new ArrayList<String>();
        
        while (!type.equals(Object.class)) {
            Field[] fields = type.getDeclaredFields();
            for (Field tmp : fields) {
                fieldNames.add(tmp.getName());
            }
            type=type.getSuperclass();
        }
        return fieldNames;
    }

    public String getBaseEntityName() {
        Class<?> type = this.getType().getSuperclass();
        return type.getName();
    }

    public BigDecimal getBigDecimal(String fieldName) throws Exception {
        return (BigDecimal) this.get(fieldName);
    }

    public Boolean getBoolean(String fieldName) throws Exception {
        return (Boolean) this.get(fieldName);
    }

    public Double getDouble(String fieldName) throws Exception {
        return (Double) this.get(fieldName);
    }

    public Float getFloat(String fieldName) throws Exception {
        return (Float) this.get(fieldName);
    }

    public Long getLong(String fieldName) throws Exception {
        return (Long) this.get(fieldName);
    }

    public String getNextSeqId() {
        // TODO may be override by sub-entity
        return null;
    }

    public List<String> getNonPrimaryKeyNames() {
        // TODO may be override by sub-entity
        return null;
    }

    public List<String> getPrimaryKeyNames() {
        // TODO may be override by sub-entity
        return null;
    }

    public List<? extends IEntity> getRelated(String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> List<T> getRelated(Class<T> entityName) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> List<T> getRelated(Class<T> entityName, List<String> orderBy) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> List<T> getRelated(Class<T> entityName, String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> List<T> getRelated(Class<T> entityName, String relation, List<String> orderBy) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public List<? extends IEntity> getRelatedCache(String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> List<T> getRelatedCache(Class<T> entityName) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> List<T> getRelatedCache(Class<T> entityName, String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public IEntity getRelatedOne(String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> T getRelatedOne(Class<T> entityName) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> T getRelatedOne(Class<T> entityName, String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public IEntity getRelatedOneCache(String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> T getRelatedOneCache(Class<T> entityName) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public <T extends IEntity> T getRelatedOneCache(Class<T> entityName, String relation) throws Exception {
        // TODO may be override by sub-entity
        return null;
    }

    public String getString(String fieldName) throws Exception {
        return (String) this.get(fieldName);
    }

    public Timestamp getTimestamp(String fieldName) throws Exception {
        return (Timestamp) this.get(fieldName);
    }

    public boolean isView() {
        return false;
    }

    public void set(String fieldName, Object value) throws Exception {
        Field field = this.getField(fieldName);
        field.set(this, value);
    }

    public void setAllFields(Map<String, Object> fields) throws Exception {
        if (fields != null) {
            Iterator<String> iter = fields.keySet().iterator();
            while (iter.hasNext()) {
                String fieldName = (String) iter.next();
                Object value = fields.get(fieldName);
                try {
                    this.set(fieldName, value);
                } catch (Exception e) {
                    throw e;
                }
            }
        } 
    }

    public void setAllFields(Map<String, Object> fields, boolean setIfEmpty) throws Exception {
        if (fields != null) {
            Iterator<String> iter = fields.keySet().iterator();
            while (iter.hasNext()) {
                String fieldName = (String) iter.next();
                Object value = fields.get(fieldName);
                try {
                    if (setIfEmpty) {
                        this.set(fieldName, value);
                    } else if (value != null) {
                        this.set(fieldName, value);
                    }
                } catch (Exception e) {
                    throw e;
                }
            }
        }
    }

    public void setAllFields(Map<String, Object> fields, boolean setIfEmpty, Boolean pks) {
        // TODO may implement by sub entity

    }

    public void setNextSubSeqId(String sequenceFieldName) throws Exception {
        // TODO may implement by sub entity
    }

    public void setNextSubSeqId(String sequenceFieldName, int numericPadding) throws Exception {
        // TODO may implement by sub entity
    }

    public void setNextSubSeqId(String sequenceFieldName, int numericPadding, int incrementBy) throws Exception {
        // TODO may implement by sub entity
    }

    public void setNonPKFields(Map<String, Object> fields) {
        // TODO may implement by sub entity
    }

    public void setNonPKFields(Map<String, Object> fields, boolean setIfEmpty) {
        // TODO may implement by sub entity
    }

    public void setPKFields(Map<String, Object> fields) {
        // TODO may implement by sub entity
    }

    public void setPKFields(Map<String, Object> fields, boolean setIfEmpty) {
        // TODO may implement by sub entity
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getType().getDeclaredFields();
        for (Field tmp : fields) {
            tmp.setAccessible(true);
            try {
                map.put(tmp.getName(), tmp.get(this));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public Map<String, Object> toMap(Collection<String> fields) throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        for (String tmp : fields) {
            Object value;
            try {
                value = this.getField(tmp).get(this);
            } catch (Exception e) {
                throw e;
            }
            map.put(tmp, value);
        }
        return map;
    }

    public Map<String, Object> toMapNoStamps() {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = this.getType().getDeclaredFields();
        for (Field tmp : fields) {
            tmp.setAccessible(true);
            try {
                Object value = tmp.get(this);
                if (!(value instanceof Timestamp)) {
                    map.put(tmp.getName(), tmp.get(this));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public IEntity parse(InputStream ins) throws Exception {
        // TODO may be implemented by Entity which need xml binding supporting
        return null;
    }

    public IEntity parse(String entityXml) throws Exception {
        // TODO may be implemented by Entity which need xml binding supporting
        return null;
    }

    private Field getField(String fieldName) throws Exception {
        Field field = null;
        try {
            Class<?> type=this.getType();
            while(!type.equals(Object.class)){
                try{
                    field = type.getDeclaredField(fieldName);
                    break;
                }catch(Exception e){
                    type=type.getSuperclass();
                }
            }
            field.setAccessible(true);
        } catch (SecurityException e) {
            e.printStackTrace();
        }
        return field;
    }

}
