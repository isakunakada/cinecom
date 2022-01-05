/**
 * 
 */
package com.inusufforn.cinecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.inusufforn.cinecom.dao.ReviewDao;
import com.inusufforn.cinecom.entity.MonthlyListReviewItem;
import com.inusufforn.cinecom.entity.Review;

/**
 * @author isaku
 *
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewDao reviewDao;

    /**
    * @inheritDoc
    */
    @Override
    public Page<MonthlyListReviewItem> getListByLastYearMonth(Pageable pageable, boolean isEableOnly) {
        List<MonthlyListReviewItem> list = reviewDao.getLastMonthlyList(pageable.getPageSize(), pageable.getOffset(), isEableOnly);
        int count = reviewDao.getCountLastMonth(isEableOnly);
        return new PageImpl<MonthlyListReviewItem>(list, pageable, count);
    }

    /**
    * @inheritDoc
    */
    @Override
    public Page<MonthlyListReviewItem> getListByYearMonth(Pageable pageable, String yearMonth, boolean isEableOnly) {
        List<MonthlyListReviewItem> list = reviewDao.selectByYearMonth(yearMonth, pageable.getPageSize(), pageable.getOffset(), isEableOnly);
        int count = reviewDao.getCountByYearMonth(yearMonth, isEableOnly);
        return new PageImpl<MonthlyListReviewItem>(list, pageable, count);
    }

    /**
    * @inheritDoc
    */
    @Override
    public List<String> getYearMonthList(boolean isEableOnly) {
        return reviewDao.getYearMonthList(isEableOnly);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Review getDetail(Integer id) {
        return reviewDao.selectById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int insert(Review entity) {
        return reviewDao.insert(entity);
    }

    /**
     * {@inheritDoc}
     */
    public int update(Review entity) {
        return reviewDao.update(entity);        
    }

    /**
     * {@inheritDoc}
     */
    public Page<MonthlyListReviewItem> getSearchList(Pageable pageable, String title, boolean isEableOnly) {
        List<MonthlyListReviewItem> list = reviewDao.selectByTitle(title, pageable.getPageSize(), pageable.getOffset(),isEableOnly);
        int count = reviewDao.getCountByTitle(title, isEableOnly);
        return new PageImpl<MonthlyListReviewItem>(list, pageable, count);
    }

}
