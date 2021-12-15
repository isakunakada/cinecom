/**
 * 
 */
package com.inusufforn.cinecom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.inusufforn.cinecom.dao.ReviewUserDao;
import com.inusufforn.cinecom.dto.ReviewUserRegisterDto;
import com.inusufforn.cinecom.entity.ReviewUser;

/**
 * @author isaku
 *
 */
@Service
public class ReviewUserServiceImpl implements ReviewUserService {
    @Autowired
    private ReviewUserDao reviewUserDao;

    /**
    * @inheritDoc
    */
    @Override
    public List<ReviewUserRegisterDto> getUserList() {
        // TODO 自動生成されたメソッド・スタブ
        return null;
    }

    /**
    * @inheritDoc
    */
    @Override
    public ReviewUser getUser(Integer id) {
        // TODO 自動生成されたメソッド・スタブ
        return reviewUserDao.selectById(id);
    }

    /**
	* @inheritDoc
	*/
	@Override
	public ReviewUser getUserByName(String name) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

	/**
    * @inheritDoc
    */
    @Override
    public int insert(ReviewUser entity) {
        // 
        return reviewUserDao.insert(entity);
    }

    /**
    * @inheritDoc
    */
    @Override
    public int update(ReviewUser entity) {
        // TODO 自動生成されたメソッド・スタブ
        return 0;
    }

}
