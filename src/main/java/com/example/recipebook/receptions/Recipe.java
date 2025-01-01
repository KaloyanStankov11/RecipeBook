package com.example.recipebook.receptions;

import com.example.recipebook.ingredients.Ingredient;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @ManyToMany(targetEntity = Ingredient.class, fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Ingredient> ingredients;
    private BigDecimal price;
    @Column(columnDefinition="TEXT", length = 2048)
    private String methodOfPreparation;

    public RecipeDTO toRecipeDTO(){
        return new RecipeDTO(
                this.id,
                this.name,
                this.ingredients.stream().map(Ingredient::toIngredientDTO).toList(),
                this.price,
                this.methodOfPreparation
        );
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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
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
