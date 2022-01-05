/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom;

import java.nio.charset.StandardCharsets;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * メッセージ・コンフィギュレーター.
 * <p>
 * メッセージ・プロパティファイルの置き場所、多言語化の設定を行う。
 * </p>
 * @author isaku
 *
 */
@Configuration
public class MessageSourceConfig {

    /**
     * mseeages.propatiesのプロパティファイル名でクラススパス直下のi18nフォルダ上に<br>
     * 多言語対応のMessageSourceを生成する.
     * 
     * @return 生成されたMessageSource
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        // ファイルの置き場所とファイル名を設定(複数指定したいときは、「,」(コンマ)で区切って記述)
        messageSource.setBasenames("i18n/messages");
        // 文字コードを設定
        messageSource.setDefaultEncoding(StandardCharsets.UTF_8.name());
        // 指定されたロケールのファイルが存在しなかった場合、(システムロケールの設定に関係なく)デフォルトのファイルを参照する設定
        messageSource.setFallbackToSystemLocale(false);

        return messageSource;
    }

}
