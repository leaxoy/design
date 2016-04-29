package com.example.design.mapper;

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
   * insert one recipe into  table cooking.
   */
  @Insert("INSERT INTO `cooking`(`cookingName`, `cookingStyle`, `cookingDate`, `authorId`, "
          + "`cookingPicture`, `cookingIntro`, `tips`, `step`, `ingredient`) VALUES "
          + "(#{cookingName}, #{cookingStyle}, #{cookingDate}, #{authorId}, #{cookingPicture}, "
          + "#{cookingIntro}, #{tips}, #{step}, #{ingredient})")
  int addCooking(Cooking cooking);

  /**
   * select one recipe by cookingId.
   *
   * @return Cooking
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingId` = #{cookingId}")
  Cooking findById(long cookingId);

  /**
   * UPDATE  one recipe's information except authorId,cookingId,state,cookingDate,cookingLikeNum
   */
  @Update("UPDATE `cooking` SET `cookingName` = #{cookingName}, `cookingStyle` = #{cookingStyle},"
          + " `cookingPicture` = #{cookingPicture}, `cookingIntro` = #{cookingIntro}, "
          + "`step` = #{step}, `tips` = #{tips}, `ingredient` = #{ingredient} WHERE "
          + "`cookingId` = #{cookingId} AND `state` = 0")
  int updateCooking(Cooking cooking);

  /**
   * set one recipe's state as "1" which means it has been deleted
   */
  @Update("UPDATE `cooking` SET `state` = 1 WHERE `state` = 0 AND `cookingId` = #{cookingId}")
  int markCookingDelete(long cookingId);

  /**
   * select one user's all recipes
   *
   * @return List<Cooking>
   */
  @Select("SELECT * FROM `cooking` WHERE `authorId` = #{authorId} AND `state` = 0 ")
  List<Cooking> findAllCookingByUserId(@Param("authorId") long userId);

  /**
   * select all cooking by keywords such as ingredient,cookingName, cookingStyleName
   *
   * @return List
   */
  @Select("SELECT * FROM `cooking` WHERE `cookingName` Like %#{keywords}% OR `ingredient` Like "
          + "%#{keywords}% OR `cookingStyle` Like %#{keywords}%  AND `state` = 0")
  List<Cooking> findCookingByKeywords(String keywords);

  /**
   * select all cooking.
   */
  @Select("SELECT * FROM `cooking`")
  List<Cooking> all();

  @Update("UPDATE `cooking` SET `cookingLikeNum` = `cookingLikeNum` + 1 WHERE `cookingId`"
          + " = #{cookingId} AND `state`=0")
  int likeNumIncr(long cookingId);

  @Update("UPDATE `cooking` SET `cookingLikeNum` = `cookingLikeNum` - 1 WHERE `cookingId`"
          + " = #{cookingId} AND `state`=0")
  int likeNumDecr(long cookingId);
}
