package com.github.akizminet.botman.repositories;

import java.util.Optional;

import com.github.akizminet.botman.domain.meal.Meal;
import com.github.akizminet.botman.domain.meal.MealId;
import com.github.akizminet.botman.repositories.batis.MealRow;
import com.github.akizminet.botman.repositories.batis.MealRowMapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class MealRepo {
    
    @Inject
    MealRowMapper mealRowMapper;

    public Optional<Meal> findById(MealId id) {
        return mealRowMapper.findById(id).map(mr -> mr.toMeal());
    }

    public Meal save(Meal meal) {
        return mealRowMapper.save(MealRow.of(meal)).toMeal();
    }
}
