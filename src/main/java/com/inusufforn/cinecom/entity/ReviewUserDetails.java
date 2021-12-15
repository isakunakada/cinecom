/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.entity;

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
    private String authority;
    
    /** 権限リスト */
    private Collection<GrantedAuthority> authorities;
    
    /**
    * @inheritDoc
    */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // (自動生成されたメソッド・スタブを権限リストを返すように変更)
        return authorities;
    }

    /**
    * @inheritDoc
    */
// (パスワードのプロパティ名をpasswordとすれば、最初からメソッドが存在する)
//    @Override
//    public String getPassword() {
//        return this.getPassword();
//    }

    /**
    * @inheritDoc
    */
//    @Override
//    public String getUsername() {
//        // ユーザ名をusernameとすれば、実装の必要なし
//        return this.getUserame();
//    }

    /**
    * @inheritDoc
    */
    @Override
    public boolean isAccountNonExpired() {
        // (このメソッドも含めて、以下のメソッドは全て自動生成されたメソッド・スタブをtrueを返すように変更)
        return true;
    }

    /**
    * @inheritDoc
    */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
    * @inheritDoc
    */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
    * @inheritDoc
    */
    @Override
    public boolean isEnabled() {
        return true;
    }

}
