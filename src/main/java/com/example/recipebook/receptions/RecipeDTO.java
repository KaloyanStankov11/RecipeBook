package com.example.recipebook.receptions;

import com.example.recipebook.ingredients.IngredientDTO;

import java.math.BigDecimal;
import java.util.List;

public class RecipeDTO {
    private Long id;
    private String name;
    private List<IngredientDTO> ingredients;
    private BigDecimal price;
    private String methodOfPreparation;

    public RecipeDTO(Long id, String name, List<IngredientDTO> ingredients, BigDecimal price, String methodOfPreparation) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.price = price;
        this.methodOfPreparation = methodOfPreparation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<IngredientDTO> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientDTO> ingredients) {
        this.ingredients = ingredients;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getMethodOfPreparation() {
        return methodOfPreparation;
    }

    public void setMethodOfPreparation(String methodOfPreparation) {
        this.methodOfPreparation = methodOfPreparation;
    }
}
