package com.inusufforn.cinecom.dao;

import java.util.List;

import org.seasar.doma.Dao;
import org.seasar.doma.Delete;
import org.seasar.doma.Insert;
import org.seasar.doma.Select;
import org.seasar.doma.Update;
import org.seasar.doma.boot.ConfigAutowireable;

import com.inusufforn.cinecom.entity.MonthlyListReviewItem;
import com.inusufforn.cinecom.entity.Review;

/**
 * 
 * @ConfigAutowireableを設定するときは、「プロジェクトの固有の処理を可能にする」のチェックを外さないとエラーとなる。
 */
@Dao
@ConfigAutowireable
public interface ReviewDao {

    /**
     * 
     * @param limit
     * @param offset
     * @return
     */
    @Select
    List<MonthlyListReviewItem> getLastMonthlyList(Integer limit, Long offset, boolean isEableOnly);

    @Select
    int getCountLastMonth(boolean isEableOnly);

    /**
     * @param id
     * @return the Review entity
     */
    @Select
    Review selectById(Integer id);

    /**
     * 
     * @param title
     * @param limit
     * @param offset
     * @return
     */
    @Select
    List<MonthlyListReviewItem> selectByTitle(String title, Integer limit, Long offset, boolean isEableOnly);

    @Select
    int getCountByTitle(String title, boolean isEableOnly);

    /**
     * 
     * @return
     */
    @Select
    List<String> getYearMonthList(boolean isEableOnly);

    /**
     * 
     * @param yearMonth
     * @param limit
     * @param offset
     * @param isEableOnly
     * @return
     */
    @Select
    List<MonthlyListReviewItem> selectByYearMonth(String yearMonth, Integer limit, Long offset, boolean isEableOnly);

    @Select
    int getCountByYearMonth(String yearMonth, boolean isEableOnly);

    /**
     * @param entity
     * @return affected rows
     * 
     * Insertアノテーションで挿入を除外するカラム(デフォルト値が設定される)を指定する。
     */
    @Insert(exclude = {"id", "created", "updated"})
    int insert(Review entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Update(exclude = {"id", "created", "createdBy"})
    int update(Review entity);

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    int delete(Review entity);
}