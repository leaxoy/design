package com.example.design.mapper;

import com.example.design.component.model.Page;
import com.example.design.model.Cooking;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 菜谱持久化接口.
 *
 * @author lxh
 * @version 0.1
 */
@Repository
@Mapper
public interface CookingMapper {
  /**
   * insert one recipe into table cooking.
   */
  @Insert("INSERT INTO `cooking`(`cookingName`, `cookingStyle`, `cookingDate`, `authorId`, "
          + "`cookingPicture`, `cookingIntro`, `tips`, `step`, `ingredient`) VALUES "
          + "(#{cookingName}, #{cookingStyle}, #{cookingDate}, #{authorId}, #{cookingPicture}, "
          + "#{cookingIntro}, #{tips}, #{step}, #{ingredient})")
  int add(Cooking cooking);

  /**
   * select one recipe by cookingId.
   *
   * @return Cooking
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingId` = #{cookingId}")
  Cooking findById(@Param("cookingId") long cookingId);

  @Select("SELECT * FROM `cooking` LIMIT #{offset}, #{limit}")
  List<Cooking> findByPage(Page page);

  /**
   * get top 6.
   *
   * @return cooking list.
   */
  @Select("SELECT * FROM `cooking` ORDER BY `cookingLikeNum` DESC LIMIT 6")
  List<Cooking> top6();

  /**
   * UPDATE  one recipe's information except authorId,cookingId,state,cookingDate,cookingLikeNum.
   */
  @Update("UPDATE `cooking` SET `cookingName` = #{cookingName}, `cookingStyle` = #{cookingStyle},"
          + " `cookingPicture` = #{cookingPicture}, `cookingIntro` = #{cookingIntro}, "
          + "`step` = #{step}, `tips` = #{tips}, `ingredient` = #{ingredient} WHERE "
          + "`cookingId` = #{cookingId} AND `state` = 0")
  int updateCooking(Cooking cooking);

  /**
   * set one recipe's state as "1" which means it has been deleted.
   */
  @Update("UPDATE `cooking` SET `state` = 1 WHERE `state` = 0 AND `cookingId` = #{cookingId}")
  int markDeleted(@Param("cookingId") long cookingId);

  /**
   * select one user's findAll recipes
   *
   * @return List
   */
  @Select("SELECT * FROM `cooking` WHERE `authorId` = #{authorId} AND `state` = 0 ")
  List<Cooking> findByUserId(@Param("authorId") long userId);

  /**
   * select cookings by keywords such as ingredient, cookingName, cookingStyleName.
   *
   * @return List
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingName` Like %#{keywords}% OR `ingredient` Like "
          + "%#{keywords}% OR `cookingStyle` Like %#{keyword}%  AND `state` = 0")
  List<Cooking> findByKeyword(@Param("keyword") String keyword);


  /**
   *
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingStyle` = #{category}")
  List<Cooking> category(@Param("category") String category);

  /**
   * select findAll cooking.
   */
  @Select("SELECT * FROM `cooking`")
  List<Cooking> findAll();

  @Update("UPDATE `cooking` SET `cookingLikeNum` = `cookingLikeNum` + 1 WHERE `cookingId`"
          + " = #{cookingId} AND `state`=0")
  int incrLikeNum(@Param("cookingId") long cookingId);

  @Update("UPDATE `cooking` SET `cookingLikeNum` = `cookingLikeNum` - 1 WHERE `cookingId`"
          + " = #{cookingId} AND `state`=0")
  int decrLikeNum(@Param("cookingId") long cookingId);
}
