package com.github.akizminet.botman.repositories.batis;

import java.util.Optional;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.akizminet.botman.domain.meal.MealId;

@Mapper
public interface MealRowMapper {

    @Select("SELECT * FROM meal WHERE chat_id = #{chatId} AND message_id = #{messageId}")
    public Optional<MealRow> findById(MealId id);

    @Select("""
    INSERT INTO meal(chat_id, message_id, date, breakfast, lunch, dinner, type) 
    VALUES(#{chatId}, #{messageId}, #{date}, #{breakfast}, #{lunch}, #{dinner}, #{type})
    ON CONFLICT (chat_id, message_id)
    DO UPDATE
    SET date = #{date}, breakfast = #{breakfast}, lunch = #{lunch}, dinner = #{dinner}, type = #{type}
    RETURNING *
    """)
    public MealRow save(MealRow mealRow);

}
