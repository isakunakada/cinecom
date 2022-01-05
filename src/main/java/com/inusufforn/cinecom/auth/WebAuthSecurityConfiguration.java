/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.inusufforn.cinecom.common.util.DataSourceConfig;
import com.inusufforn.cinecom.service.ReviewUserDetailsServiceImpl;

/**
 * @author isaku
 *
 */
@Configuration
@EnableWebSecurity
public class WebAuthSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private ReviewUserDetailsServiceImpl reviewUserService;

    @Autowired
    private DataSourceConfig dataSourceConfig;

    /**
     * @inheritDoc
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/css/**","/js/**","/img/**");
    }

    /**
     * BCｒｙｐｔでパスワードをハッシュ化するエンコーダーを返す.
     * 
     * @return BCｒｙｐｔでパスワードをハッシュ化するエンコーダー
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * @inheritDoc
     */
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(reviewUserService).passwordEncoder(passwordEncoder());
    }

    /**
     * @inheritDoc
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()    // 認可に関する設定
            .antMatchers("/","/login","/detail/**").permitAll();//.anyRequest().denyAll();    //「/」などのパスは全てのユーザに許可

        http.formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/")           // 認証後にユーザーがリダイレクトされる場所を指定
            .usernameParameter("username")    // Usernameのパラメータとして使用する項目のnameを設定
            .passwordParameter("password")    // Passwordのパラメータとして使用する項目のnameを設定
            .permitAll()
            .and()
            .rememberMe()    // ログイン状態を保持
            .tokenRepository(this.createTokenRepository());

            http.logout().logoutSuccessUrl("/login").permitAll();
    }

    /**
     * 
     * @return
     */
    public PersistentTokenRepository createTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSourceConfig.getDataSource());
        // プロジェクトの開始時または開始時以外にトークン情報を保存するためのテーブルを作成しない設定
        // 別途、手動で(persistent_loginsを)生成
        tokenRepository.setCreateTableOnStartup(false);

        return tokenRepository;
    }
}
