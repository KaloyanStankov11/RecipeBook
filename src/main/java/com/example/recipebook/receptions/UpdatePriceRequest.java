package com.example.recipebook.receptions;

import java.math.BigDecimal;

public class UpdatePriceRequest {
    private Long recipeId;
    private BigDecimal newPrice;

    public Long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(Long recipeId) {
        this.recipeId = recipeId;
    }

    public BigDecimal getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(BigDecimal newPrice) {
        this.newPrice = newPrice;
    }
}
