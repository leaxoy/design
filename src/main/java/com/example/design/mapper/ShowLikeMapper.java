package com.example.design.mapper;

import com.example.design.model.ShowLike;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * Created by lxh on 16/4/25.
 */
@Repository
public interface ShowLikeMapper {
    /**
     * *if one user click Like,his(her) behavior will be recorded,and set state as 1
     *
     * @param showLike
     * @return
     */
    @Insert("INSERT INTO showlike(showId, userId) VALUES (#{showId}, #{userId})")
    int addShowLikeUser(ShowLike showLike);

    /**
     * select user's one show-like record
     *
     * @param userId
     * @param showId
     * @return ShowLike
     */
    @Select("SELECT * FROM showlike WHERE userId = *{userId} AND showId = #{showId}")
    ShowLike isLike(long userId, long showId);

    /**
     * update showLike's state
     *
     * @param like
     * @param userId
     * @param showId
     * @return
     */
    @Update("UPDATE showlike SET state = #{like} WHERE userId = #{userId} AND showId = #{showId}")
    int markShowLikeState(int like, long userId, long showId);

    /**
     * update show's showLike
     *
     * @param showId
     * @param like
     * @return
     */
    @Update("UPDATE show SET showLikeNum = showLikeNum + #{like} WHERE showId = #{showId}")
    int updateLikeOfShow(long showId, int like);
}
