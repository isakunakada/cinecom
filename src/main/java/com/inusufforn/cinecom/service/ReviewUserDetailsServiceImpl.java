/**
 * 
 */
package com.inusufforn.cinecom.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.inusufforn.cinecom.dao.ReviewUserDao;
import com.inusufforn.cinecom.entity.ReviewUserAccount;
import com.inusufforn.cinecom.entity.ReviewUserDetails;

/**
 * @author isaku
 *
 */
@Component
public class ReviewUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private ReviewUserDao reviewUserDao;

    /**
    * @inheritDoc
    */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // ユーザ情報テーブルをユーザ名で検索
        ReviewUserAccount account = reviewUserDao.selectByName(username);

        if (account == null) {
            // 該当するユーザなし
            throw new UsernameNotFoundException(username);
        }

        ReviewUserDetails userDetails = new ReviewUserDetails();
        userDetails.setUsername(account.getUsername());
        userDetails.setPassword(account.getPassword());
        
        // 取得した権限を編集(本来はカンマ区切りの並びを想定)
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(account.getAuthority()));
        userDetails.setAuthorities(authorities);

        return userDetails;
    }

}
