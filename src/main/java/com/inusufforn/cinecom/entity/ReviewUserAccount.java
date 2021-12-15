/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.entity;

import org.seasar.doma.Entity;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Data
@Entity
public class ReviewUserAccount {

    /** ユーザ名 */
    String username;

    /** パスワード */
    String password;

    /** ユーザ権限 */
    String authority;

}
