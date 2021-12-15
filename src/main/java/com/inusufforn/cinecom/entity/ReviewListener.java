package com.inusufforn.cinecom.entity;

import org.seasar.doma.jdbc.entity.EntityListener;
import org.seasar.doma.jdbc.entity.PostDeleteContext;
import org.seasar.doma.jdbc.entity.PostInsertContext;
import org.seasar.doma.jdbc.entity.PostUpdateContext;
import org.seasar.doma.jdbc.entity.PreDeleteContext;
import org.seasar.doma.jdbc.entity.PreInsertContext;
import org.seasar.doma.jdbc.entity.PreUpdateContext;

/**
 * 
 */
public class ReviewListener implements EntityListener<Review> {

    @Override
    public void preInsert(Review entity, PreInsertContext<Review> context) {
    }

    @Override
    public void preUpdate(Review entity, PreUpdateContext<Review> context) {
    }

    @Override
    public void preDelete(Review entity, PreDeleteContext<Review> context) {
    }

    @Override
    public void postInsert(Review entity, PostInsertContext<Review> context) {
    }

    @Override
    public void postUpdate(Review entity, PostUpdateContext<Review> context) {
    }

    @Override
    public void postDelete(Review entity, PostDeleteContext<Review> context) {
    }
}