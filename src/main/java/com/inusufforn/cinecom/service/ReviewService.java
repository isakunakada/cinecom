/**
 * 
 */
package com.inusufforn.cinecom.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.inusufforn.cinecom.entity.MonthlyListReviewItem;
import com.inusufforn.cinecom.entity.Review;

/**
 * 作品レビュー情報サービス
 * 
 * @author isaku
 * Serviceのinterfaceにはアノテーションを記述しない。
 */
public interface ReviewService {

    /**
     * 最新年月の作品リストを取得.
     * 
     * @param pageable
     * @param isEableOnly
     * @return
     */
    Page<MonthlyListReviewItem> getListByLastYearMonth(Pageable pageable, boolean isEableOnly);

    /**
     * 年月の作品リストを取得.
     * 
     * @param pageable
     * @param yearMonth 作品リストを取得したい年月
     * @param isEableOnly
     * @return 取得したい年月の作品リスト
     */
    Page<MonthlyListReviewItem> getListByYearMonth(Pageable pageable, String yearMonth, boolean isEableOnly);

    /**
     * 掲載作品の年月のリストを取得.
     * 
     * @param isEableOnly
     * @return
     */
    List<String> getYearMonthList(boolean isEableOnly);

    /**
     * 作品IDから詳細情報を取得.
     * 
     * @param id 作品ID
     * @return 詳細情報
     */
    Review getDetail(Integer id);

    /**
     * 作品レビューを作成.
     * 
     * @param entity
     * @return
     */
    int insert(Review entity);

    /**
     * 作品レビューを更新.
     * 
     * @param entity
     * @return
     */
    int update(Review entity);

    /**
     * 
     * @param pageable
     * @param title
     * @return
     */
    Page<MonthlyListReviewItem> getSearchList(Pageable pageable, String title, boolean isEableOnly);

}
