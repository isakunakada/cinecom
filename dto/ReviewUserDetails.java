/**
 * 2021 cinecom 映画レビュー Webアプリケーション 
 */
package com.inusufforn.cinecom.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Data
public class ReviewUserDetails implements UserDetails {

    /** ユーザ名 */
    private String username;

    /** パスワード */
    private String password;

    /** 管理権限 */
    private Collection<GrantedAuthority> authorities;

    /**
     * {@inheritDoc}
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 自動生成されたメソッド・スタブを管理権限を返すように修正
        return authorities;
    }

// lombokにより生成されるので記述の必要なし
//    @Override
//    public String getPassword() {
//        // 自動生成されたメソッド・スタブをパスワードを返すように修正
//        return password;
//    }
//
//    @Override
//    public String getUsername() {
//        // 自動生成されたメソッド・スタブをユーザ名を返すように修正
//        return username;
//    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonExpired() {
        // (以下、4メソッドは、自動生成されたメソッド・スタブをtrueを返すように修正)
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
