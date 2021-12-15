package com.inusufforn.cinecom.dao;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.inusufforn.cinecom.entity.ReviewUser;
import com.inusufforn.cinecom.entity.ReviewUserAccount;

/**
 */
@Dao
@ConfigAutowireable
public interface ReviewUserDao {

    /**
     * @param id
     * @return the ReviewUser entity
     */
    @Select
    ReviewUser selectById(Integer id);

    /**
     * 
     * @param name
     * @return
     */
    @Select
    ReviewUserAccount selectByName(String username);

    /**
     * @param entity
     * @return affected rows
     */
    @Insert(exclude = {"id", "created", "updated", "createdBy", "updatedBy"})
    int insert(ReviewUser entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    int update(ReviewUser entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(ReviewUser entity);
}