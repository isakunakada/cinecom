/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * レビュー登録情報Dto.
 * 
 * @author isaku
 *
 */
@Data
public class ReviewDto implements Serializable {

    /** 作品ID */
    private Integer id;

    /** 作品タイトル */
    @NotBlank
    @Size(max = 64)
    private String title;

    /** 見出しコメント */
    @NotBlank
    @Size(max = 64)
    private String shortComment;

    /** 鑑賞日 */
    @NotNull
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate watchedDate;

    /** 上映時刻 */
    @NotNull
    @DateTimeFormat(pattern="HH:mm")
    private LocalTime watchedTime;

    /** 劇場名 */
    @NotBlank
    @Size(max = 64)
    private String theater;

    /** スクリーン */
    @Size(max = 16)
    private String screen;

    /** コメント本文 */
    @NotBlank
    @Size(max = 800)
    private String mainComment;

    /** 評価 */
    @NotNull
    private Short rating;

    /** 有効フラグ */
    private Short enable;
}
