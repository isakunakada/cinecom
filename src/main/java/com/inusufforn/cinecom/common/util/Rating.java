/**
 * 2021 cinecom 映画レビュー Webアプリケーション
 */
package com.inusufforn.cinecom.common.util;

import lombok.Data;

/**
 * 作品評価.
 * 
 * @author isaku
 *
 */
@Data
public class Rating {

    /**
     * コンストラクタ.
     * 
     * @param value 値
     * @param indication 表示
     */
    public Rating(int value, String indication) {
        this.value = value;
        this.indication = indication;
    }

    /** 値 */
    private int value;

    /** 表示 */
    private String indication;

}
