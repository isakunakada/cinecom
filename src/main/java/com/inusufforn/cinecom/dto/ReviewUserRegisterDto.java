/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.dto;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Data
public class ReviewUserRegisterDto implements Serializable {
    /** ユーザ名 */
    @NotEmpty(message = "ユーザ名を入力してください")
    @Size(min = 8, message = "ユーザ名は8文字以上で入力してください")
    private String username;
    
    /** パスワード */
    @NotEmpty(message = "パスワードを入力してください")
    private String password;
    
    /** パスワード(確認) */
    @NotEmpty(message = "パスワード(確認)を入力してください")
    private String passwordConfirm;

    /** ユーザ権限 */
    private String authority;

    /** ユーザ権限選択肢マップ */
    private Map<String, String> authorities;
    
}
