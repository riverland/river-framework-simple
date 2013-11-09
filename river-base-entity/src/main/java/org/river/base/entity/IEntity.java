package org.river.base.entity;

import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Map;

import java.util.List;

/**
 * entity interface
 * @author river
 * 2010/05/30
 */
public interface IEntity {

    /**
     * Generic getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the value for that field
     * @throws Exception 
     */
    public Object get(String fieldName) throws Exception;

    /**
     * Generic <code>String</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>String</code> value for that field
     * @throws Exception 
     */
    public String getString(String fieldName) throws Exception;

    /**
     * Generic <code>Boolean</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>Boolean</code> value for that field
     * @throws Exception 
     */
    public Boolean getBoolean(String fieldName) throws Exception;

    /**
     * Generic <code>Double</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>Double</code> value for that field
     * @throws Exception 
     */
    public Double getDouble(String fieldName) throws Exception;

    /**
     * Generic <code>Float</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>Float</code> value for that field
     * @throws Exception 
     */
    public Float getFloat(String fieldName) throws Exception;

    /**
     * Generic <code>Long</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>Long</code> value for that field
     * @throws Exception 
     */
    public Long getLong(String fieldName) throws Exception;

    /**
     * Generic <code>BigDecimal</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>BigDecimal</code> value for that field
     * @throws Exception 
     */
    public BigDecimal getBigDecimal(String fieldName) throws Exception;

    /**
     * Generic <code>Timestamp</code> getter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to retrieve
     * @return the <code>Timestamp</code> value for that field
     * @throws Exception 
     */
    public Timestamp getTimestamp(String fieldName) throws Exception;

    /**
     * Generic setter, in case you want to work with the entity as a Map-like construct.
     * @param fieldName the field to set
     * @param value the value to set
     * @throws Exception 
     */
    public void set(String fieldName, Object value) throws Exception;

    /** {@inheritDoc} */
    public boolean equals(Object obj);

    /** {@inheritDoc} */
    public int hashCode();

    /**
     * Converts to a <code>Map</code>.
     * @return a <code>Map</code> associating the field names and their value
     */
    public Map<String, Object> toMap();

    /**
     * Converts to a <code>Map</code> only including a subset of the fields.
     * @param fields the list of field to include in the <code>Map</code>
     * @return a <code>Map</code> associating the field names and their value
     * @throws Exception 
     */
    public Map<String, Object> toMap(Collection<String> fields) throws Exception;

    /**
     * Converts to a <code>Map</code> without the  fields.
     * @return a <code>Map</code> with of this entity without the  fields
     */
    public Map<String, Object> toMapNoStamps();

    /**
     * Converts from a <code>Map</code>.
     * @param mapValue the <code>Map</code> to convert from
     * @throws Exception 
     */
    public void fromMap(Map<String, Object> mapValue) throws Exception;

    /**
     * Converts from another <code>EntityInterface</code>, which may or may not be the same type of entity.
     * @param entity the <code>EntityInterface</code> to convert from
     * @throws Exception 
     */
    public void fromEntity(IEntity entity) throws Exception;

    /**
     * Gets the base entity name.
     * @return the base entity name
     */
    public String getBaseEntityName();

    /**
     * Checks if this entity is a view entity.
     * View entities are an aggregation of various base entities.
     * @return a <code>boolean</code> value
     */
    public boolean isView();

    /**
     * Gets the next guaranteed unique sequence ID for this entity, if the named sequence does not exist it will be created.
     * @return a <code>String</code> value
     */
    public String getNextSeqId();

    /**
     * Sets this entity given sub-sequence field to the next available value.
     * @param sequenceFieldName the field representing the sub-sequence
     * @exception RepositoryException if an error occurs
     */
    public void setNextSubSeqId(String sequenceFieldName) throws Exception;

    /**
     * Sets this entity given sub-sequence field to the next available value.
     * @param sequenceFieldName the field representing the sub-sequence
     * @param numericPadding the length of the sequence string padded with 0
     * @exception RepositoryException if an error occurs
     */
    public void setNextSubSeqId(String sequenceFieldName, int numericPadding) throws Exception;

    /**
     * Describe <code>setNextSubSeqId</code> method here.
     * @param sequenceFieldName the field representing the sub-sequence
     * @param numericPadding the length of the sequence string padded with 0
     * @param incrementBy the increment for the next sub-sequence compared to the highest found
     * @exception RepositoryException if an error occurs
     */
    public void setNextSubSeqId(String sequenceFieldName, int numericPadding, int incrementBy) throws Exception;


    /**
     * Get the list of fields forming the primary key for this entity.
     * @return the list of fields names forming the primary key
     */
    public List<String> getPrimaryKeyNames();

    /**
     * Get the list of fields not forming the primary key for this entity.
     * @return the list of fields names not forming the primary key
     */
    public List<String> getNonPrimaryKeyNames();

    /**
     * Get the list of fields for this entity.
     * @return the list of fields
     */
    public List<String> getAllFieldsNames();

    /**
     * Sets fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields the fields <code>Map</code> to get the values from
     * @throws Exception 
     */
    public void setAllFields(Map<String, Object> fields) throws Exception;

    /**
     * Sets fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields The fields <code>Map</code> to get the values from
     * @param setIfEmpty Used to specify whether empty/null values in the field <code>Map</code> should over-write non-empty values in this entity
     * @throws Exception 
     */
    public void setAllFields(Map<String, Object> fields, boolean setIfEmpty) throws Exception;

    /**
     * Sets all non PKs fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields the fields <code>Map</code> to get the values from
     */
    public void setNonPKFields(Map<String, Object> fields);

    /**
     * Sets all non PKs fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields the fields <code>Map</code> to get the values from
     * @param setIfEmpty Used to specify whether empty/null values in the field <code>Map</code> should over-write non-empty values in this entity
     */
    public void setNonPKFields(Map<String, Object> fields, boolean setIfEmpty);

    /**
     * Sets all PKs fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields The fields <code>Map</code> to get the values from
     */
    public void setPKFields(Map<String, Object> fields);

    /**
     * Sets all PKs fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields the fields Map to get the values from
     * @param setIfEmpty used to specify whether empty/null values in the field <code>Map</code> should over-write non-empty values in this entity
     */
    public void setPKFields(Map<String, Object> fields, boolean setIfEmpty);

    /**
     * Sets fields on this entity from the <code>Map</code> of fields passed in.
     * @param fields the fields <code>Map</code> to get the values from
     * @param setIfEmpty used to specify whether empty/null values in the field <code>Map</code> should over-write non-empty values in this entity
     * @param pks if null, get all values, if TRUE just get the PKs, if FALSE just get the non-PKs
     */
    public void setAllFields(Map<String, Object> fields, boolean setIfEmpty, Boolean pks);

    /**
     * Gets the entity related to this entity by the given relation.
     * @param relation the name of the relation between the two entities
     * @return an <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public IEntity getRelatedOne(String relation) throws Exception;

    /**
     * Gets the entity related to this entity, where the relation name match the related entity name.
     * @param <T> class of entity to return
     * @param entityName the name the related entity
     * @return an <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> T getRelatedOne(Class<T> entityName) throws Exception;

    /**
     * Gets the entity related to this entity, given both the related entity name and the relation name.
     * @param <T> class of entity to return
     * @param entityName the name the related entity
     * @param relation the name of the relation between the two entities
     * @return an <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> T getRelatedOne(Class<T> entityName, String relation) throws Exception;

    /**
     * Gets the entity related to this entity by the given relation using the cache.
     * @param relation the name of the relation between the two entities
     * @return an <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public IEntity getRelatedOneCache(String relation) throws Exception;

    /**
     * Gets the entity related to this entity using the cache, where the relation name match the related entity name.
     * @param <T> class of entity to return
     * @param entityName the name the related entity
     * @return an <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> T getRelatedOneCache(Class<T> entityName) throws Exception;

    /**
     * Gets the entity related to this entity using the cache, given both the related entity name and the relation name.
     * @param <T> class of entity to return
     * @param entityName the name the related entity
     * @param relation the name of the relation between the two entities
     * @return an <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> T getRelatedOneCache(Class<T> entityName, String relation) throws Exception;

    /**
     * Gets the list of related entities to this entity by the given relation name.
     * @param relation the name of the relation between the two entities
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public List<? extends IEntity> getRelated(String relation) throws Exception;

    /**
     * Gets the list of related entities to this entity, where the relation name match the related entity name.
     * @param <T> class of entity to return
     * @param entityName the name of the related entities
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> List<T> getRelated(Class<T> entityName) throws Exception;

    /**
     * Gets the list of related entities to this entity, where the relation name match the related entity name.
     * @param <T> class of entity to return
     * @param entityName the name of the related entities
     * @param orderBy the fields of the related entity to order the query by; may be null; optionally add a " ASC" for ascending or " DESC" for descending
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> List<T> getRelated(Class<T> entityName, List<String> orderBy) throws Exception;

    /**
     * Gets the list of related entities to this entity, given both the related entity name and the relation name.
     * @param <T> class of entity to return
     * @param entityName the name of the related entities
     * @param relation the name of the relation between the entities
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> List<T> getRelated(Class<T> entityName, String relation) throws Exception;

    /**
     * Gets the list of related entities to this entity, given both the related entity name and the relation name.
     * @param <T> class of entity to return
     * @param entityName the name of the related entities
     * @param relation the name of the relation between the entities
     * @param orderBy the fields of the related entity to order the query by; may be null; optionally add a " ASC" for ascending or " DESC" for descending
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> List<T> getRelated(Class<T> entityName, String relation, List<String> orderBy) throws Exception;

    /**
     * Gets the list of related entities to this entity by the given relation name using the cache.
     * @param relation the name of the relation between the two entities
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public List<? extends IEntity> getRelatedCache(String relation) throws Exception;

    /**
     * Gets the list of related entities to this entity using the cache, where the relation name match the related entity name.
     * @param <T> class of entity to return
     * @param entityName the name of the related entities
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> List<T> getRelatedCache(Class<T> entityName) throws Exception;

    /**
     * Gets the list of related entities to this entity using the cache, given both the related entity name and the relation name.
     * @param <T> class of entity to return
     * @param entityName the name of the related entities
     * @param relation the name of the relation between the entities
     * @return a list of <code>EntityInterface</code> value
     * @throws Exception if an error occurs
     */
    public <T extends IEntity> List<T> getRelatedCache(Class<T> entityName, String relation) throws Exception;
    
    /**
     * Gets the actual implement entity type,which should be
     * @return
     */
    @SuppressWarnings("unchecked")
	public Class getType();
    
    /**
     * <p>
     * Parse {@link IEntity} instance from xml string
     * @param entityXml
     * @return
     * @throws Exception
     */
    public IEntity parse(String entityXml)throws Exception;
    
    /**
     * <p>
     * @param ins
     * Parse {@link IEntity} instance from input stream
     * @return
     * @throws Exception
     */
    public IEntity parse(InputStream ins)throws Exception;
    
    /**
     * <p>
     * generate a version of the entity for backup<br>
     * the version of the entity is the latest deposited version<br>
     * if  none of field value modified, return the latest version
     * @return version id
     */
    public String deposit();
    
    /**
     * <p>
     * check whether field with specified fieldName has ever been changed
     * @param fieldName
     * @return
     * @throws Exception
     */
    public boolean isModified(String fieldName)throws Exception;
    
    /**
     * <p>
     * check whether field with specified fieldName has ever been changed from specified version
     * @param fieldName
     * @param version
     * @return
     * @throws Exception
     */
    public boolean isModified(String fieldName,String version)throws Exception;
}
