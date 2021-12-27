/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.common.util;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author isaku
 *
 */
@Documented
@Constraint(validatedBy = {UnusedUserNameValidator.class})
@Target({FIELD})
@Retention(RUNTIME)
public @interface UnusedUserName {

    String message() default "すでに登録済みのユーザ名です";
    
    Class<?>[] groups() default {};
 
    Class<? extends Payload>[] payload() default {};
 
    @Target({FIELD})
    @Retention(RUNTIME)
    @Documented
    public @interface List {
        UnusedUserName[] value();
    }

}
