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
public class PersistentLoginsListener implements EntityListener<PersistentLogins> {

    @Override
    public void preInsert(PersistentLogins entity, PreInsertContext<PersistentLogins> context) {
    }

    @Override
    public void preUpdate(PersistentLogins entity, PreUpdateContext<PersistentLogins> context) {
    }

    @Override
    public void preDelete(PersistentLogins entity, PreDeleteContext<PersistentLogins> context) {
    }

    @Override
    public void postInsert(PersistentLogins entity, PostInsertContext<PersistentLogins> context) {
    }

    @Override
    public void postUpdate(PersistentLogins entity, PostUpdateContext<PersistentLogins> context) {
    }

    @Override
    public void postDelete(PersistentLogins entity, PostDeleteContext<PersistentLogins> context) {
    }
}