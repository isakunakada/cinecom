/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author isaku
 *
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
    * @inheritDoc
    */
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();

        // リクエストパラメーターで、1から始まるページ番号インデックスを公開して想定
        resolver.setOneIndexedParameters(true);
        // 受け入れられる最大ページサイズを設定
        resolver.setMaxPageSize(5);

        resolvers.add(resolver);
    }

}
