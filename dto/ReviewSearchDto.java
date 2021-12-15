/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.dto;

import java.io.Serializable;

import lombok.Data;

/**
 * @author isaku
 *
 */
@Data
public class ReviewSearchDto implements Serializable {
    /** タイトル */
    private String title;
    
}
