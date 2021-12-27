/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.common.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.inusufforn.cinecom.entity.ReviewUser;
import com.inusufforn.cinecom.service.ReviewUserService;

/**
 * @author isaku
 *
 */
public class UnusedUserNameValidator implements ConstraintValidator<UnusedUserName, String> {

    /** レビューユーザ・サービス */
    @Autowired
    ReviewUserService reviewUserService;

    /**
    * @inheritDoc
    */
    @Override
    public void initialize(UnusedUserName constraintAnnotation) {
    }

    /**
    * @inheritDoc
    */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        ReviewUser reviewUser = reviewUserService.getUserByName(value);

        if(reviewUser == null){
            return true;
        }
        return false;
    }

}
