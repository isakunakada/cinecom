/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.common.util;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 * @author isaku
 *
 */
public class ConfirmValidator implements ConstraintValidator<Confirm, Object> {

    private String former;
    private String latter;
    private String message;

    /**
    * @inheritDoc
    */
    @Override
    public void initialize(Confirm constraintAnnotation) {
        this.former = constraintAnnotation.former();
        this.latter = constraintAnnotation.latter();
        this.message = constraintAnnotation.message();
    }

    /**
    * @inheritDoc
    */
    @Override
    public boolean isValid(Object form, ConstraintValidatorContext context) {
        BeanWrapper beanWrapper = new BeanWrapperImpl(form);
        String former = (String) beanWrapper.getPropertyValue(this.former);
        String latter = (String) beanWrapper.getPropertyValue(this.latter);

        if (Objects.equals(former, latter)) {
            return true;
        } else {
            // デフォルトの制約違反情報をクリア
            context.disableDefaultConstraintViolation();

            context
                .buildConstraintViolationWithTemplate(message)
                .addPropertyNode(latter)
                .addConstraintViolation();
            return false;
        }
    }

}
