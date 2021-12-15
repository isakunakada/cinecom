/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Data
public class ReviewDto implements Serializable {

    /** 作品ID */
    private Integer id;

    /** 作品タイトル */
    @NotEmpty(message = "作品タイトルを入力してください")
    @Size(max = 64, message = "作品タイトルは64文字以内で入力してください")
    private String title;

    /** 見出しコメント */
    private String shortComment;

    /** 鑑賞日 */
    private LocalDate watchedDate;

    /** 上映時刻 */
    private LocalTime watchedTime;

    /** 劇場名 */
    @NotEmpty(message = "劇場名を入力してください")
    @Size(max = 64, message = "劇場名は64文字以内で入力してください")
    private String theater;

    /** スクリーン */
    private String screen;

    /** コメント本文 */
    private String mainComment;

    /** 評価 */
    @NotEmpty(message = "評価を入力してください")
    @Size(max = 64, message = "評価は64文字以内で入力してください")
    private Short rating;

    /** 有効フラグ */
    private Short enable;
}
