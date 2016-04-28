package com.example.design.mapper;

import com.example.design.model.Share;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
/**
 * 分享持久化接口. Created by lxh on 4/20/16.
 */
@Repository("shareMapper")
public interface ShareMapper {
    @Insert("INSERT INTO `share` "+
            "VALUES(#{shareId}, #{shareUserId}, #{shareDate}, #{shareType},"+
            " #{itemId}, #{shareContent}, #{state})")
    int addShare(Share share);                   //添加share信息

    @Update("UPDATE `share` SET `shareId`=#{shareId}, `shareUserId`=#{shareUserId}, `shareDate`=#{shareDate}, `shareType`=#{shareType}," +
            "`itemId`=#{itemId}, `shareContent`=#{shareContent}, `state`=#{state} " +
            "WHERE `shareId`=#{shareId}")
    int updateShare(Share share);        //修改分享信息

    @Delete("DELETE FROM `share` WHERE `shareId`=#{shareId}")
    int deleteByShareId(long shareId);       //通过shareId来删除share信息

    @Select("SELECT * FROM `share` WHERE `itemId`=#{itemId} AND `shareType`=#{shareType}")
    List<Share> selectByItemAndType(long itemId, String shareType);     //通过来源Id和来源类型查找用户的分享信息

    @Select("SELECT * FROM `share` WHERE `shareUserId`=#{shareUserId}")
    List<Share> selectByUserId(long shareUserId);     //通过用户Id来查找用户的分享信息

}
