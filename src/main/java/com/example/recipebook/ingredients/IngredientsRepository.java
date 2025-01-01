package com.example.recipebook.ingredients;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientsRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findIngredientByIdIsIn(List<Long> ids);
}
