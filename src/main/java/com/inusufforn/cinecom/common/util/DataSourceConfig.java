/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.common.util;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Configuration
@ConfigurationProperties(prefix="spring.datasource")
@Data
public class DataSourceConfig {
 
    /** ドライバクラス名 */
    private String driverClassName;
     
    /** URL */
    private String url;
 
    /** ユーザー名 */
    private String username;
 
    /** パスワード */
    private String password;
 
    @Bean
    public DataSource getDataSource() {
        // application.propertiesから接続情報を取得
        // prefixで指定した配下にある設定値であるデータベース接続情報が、url, username等の
        // インスタンス変数に設定される(キャメルケースでも、ハイフンでもOK)
        DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(driverClassName);
        dataSourceBuilder.url(url);
        dataSourceBuilder.username(username);
        dataSourceBuilder.password(password);

        return dataSourceBuilder.build();
    }

}
