/**
 * 
 */
package com.inusufforn.cinecom.service;

import java.util.List;

import com.inusufforn.cinecom.dto.ReviewUserRegisterDto;
import com.inusufforn.cinecom.entity.ReviewUser;

/**
 * @author isaku
 *
 */
public interface ReviewUserService {
    /**
     * ユーザのリストを取得.
     * 
     * @return ユーザのリスト
     */
    List<ReviewUserRegisterDto> getUserList();

    /**
     * ユーザIDからユーザ情報を取得.
     * 
     * @param id ユーザID
     * @return ユーザ情報
     */
    ReviewUser getUser(Integer id);

    /**
     * ユーザ名からユーザ情報を取得.
     * 
     * @param name ユーザ名
     * @return ユーザ情報
     */
    ReviewUser getUserByName(String name);

    /**
     * ユーザを作成.
     * 
     * @param entity
     * @return
     */
    int insert(ReviewUser entity);

    /**
     * ユーザを更新.
     * 
     * @param entity
     * @return
     */
    int update(ReviewUser entity);

}
