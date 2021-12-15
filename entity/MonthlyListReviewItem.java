/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.entity;

import java.time.LocalDate;

import org.seasar.doma.Column;
import org.seasar.doma.Entity;

import lombok.Data;

/**
 * @author isaku
 * 手書きSQLでも、DaoImplは自動生成される。
 */
@Data
@Entity
public class MonthlyListReviewItem {

    /** 作品ID */
    @Column(name = "id")
    private int id;

    /** 作品タイトル */
    @Column(name = "title")
    private String title;

    /** 見出しコメント */
    @Column(name = "short_comment")
    private String shortComment;

    /** 鑑賞日 */
    @Column(name = "watched_date")
    private LocalDate watchedDate;

    /** 劇場名 */
    @Column(name = "theater")
    private String theater;
    
}
