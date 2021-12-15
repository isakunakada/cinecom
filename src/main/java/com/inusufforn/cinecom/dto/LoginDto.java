/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Data
public class LoginDto implements Serializable {
    /** タイトル */
    private String title;

    /** ユーザ名 */
    private String username;

    /** パスワード */
    private String password;
    
}
