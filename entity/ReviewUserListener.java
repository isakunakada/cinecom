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
public class ReviewUserListener implements EntityListener<ReviewUser> {

    @Override
    public void preInsert(ReviewUser entity, PreInsertContext<ReviewUser> context) {
    }

    @Override
    public void preUpdate(ReviewUser entity, PreUpdateContext<ReviewUser> context) {
    }

    @Override
    public void preDelete(ReviewUser entity, PreDeleteContext<ReviewUser> context) {
    }

    @Override
    public void postInsert(ReviewUser entity, PostInsertContext<ReviewUser> context) {
    }

    @Override
    public void postUpdate(ReviewUser entity, PostUpdateContext<ReviewUser> context) {
    }

    @Override
    public void postDelete(ReviewUser entity, PostDeleteContext<ReviewUser> context) {
    }
}