package com.inusufforn.cinecom.dao;

import com.inusufforn.cinecom.entity.PersistentLogins;
import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;

/**
 */
@Dao
public interface PersistentLoginsDao {

    /**
     * @param series
     * @return the PersistentLogins entity
     */
    @Select
    PersistentLogins selectById(String series);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    int insert(PersistentLogins entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(PersistentLogins entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(PersistentLogins entity);
}