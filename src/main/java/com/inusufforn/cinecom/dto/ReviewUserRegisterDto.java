/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.dto;

import java.io.Serializable;
import java.util.Map;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.inusufforn.cinecom.common.util.Confirm;
import com.inusufforn.cinecom.common.util.UnusedUserName;

import lombok.Data;

/**
 * ユーザ情報DTO.
 * 
 * @author isaku
 *
 */
@Data
@Confirm(former="password", latter="passwordConfirm")
public class ReviewUserRegisterDto implements Serializable {
    /** ユーザ名 */
    @Pattern(regexp = "^[a-zA-Z][0-9a-zA-Z]+{6,16}$")
    @UnusedUserName
    private String username;
    
    /** パスワード */
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-!-/:-@\\\\\\[-`{-~])(?=\\S+$).{8,32}$")
    private String password;

    /** パスワード(確認) */
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[-!-/:-@\\\\\\[-`{-~])(?=\\S+$).{8,32}$")
    private String passwordConfirm;

    /** ユーザ権限 */
    @NotNull
    private String authority;

    /** ユーザ権限選択肢マップ */
    private Map<String, String> authorities;
    
}
