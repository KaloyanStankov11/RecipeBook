package com.example.recipebook.receptions;

import com.example.recipebook.exceptions.InvalidIdException;
import com.example.recipebook.exceptions.InvalidNameException;
import com.example.recipebook.exceptions.InvalidPriceException;
import com.example.recipebook.exceptions.TextTooLongException;
import com.example.recipebook.ingredients.Ingredient;
import com.example.recipebook.ingredients.IngredientDTO;
import com.example.recipebook.ingredients.IngredientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class RecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IngredientsRepository ingredientsRepository;

    public List<RecipeDTO> getAllReceptionsByPrice(BigDecimal price) throws InvalidPriceException {
        if(!isValidPrice(price)) throw new InvalidPriceException();
        return recipeRepository.findAllByPrice(price).stream().map(Recipe::toRecipeDTO).toList();
    }

    public RecipeDTO updateRecipePrice(UpdatePriceRequest request) throws InvalidIdException, InvalidPriceException {
        if(!isValidPrice(request.getNewPrice())) throw new InvalidPriceException();
        Recipe recipe = recipeRepository.findById(request.getRecipeId()).orElse(null);
        if(recipe == null){
            throw new InvalidIdException("Recipe with ID: " + request.getRecipeId() + " not found!");
        }
        recipe.setPrice(request.getNewPrice());
        return recipeRepository.save(recipe).toRecipeDTO();
    }

    public RecipeDTO addNewRecipe(NewRecipeRequest request) throws InvalidPriceException, InvalidNameException, TextTooLongException {
        if(!isValidPrice(request.getPrice())) throw new InvalidPriceException();
        if(!checkName(request.getName())) throw new InvalidNameException();
        if(!checkText(request.getMethodOfPreparation())) throw new TextTooLongException();
        List<Ingredient> ingredients = ingredientsRepository.findIngredientByIdIsIn(request.getIngredients());
        Recipe recipe = new Recipe();
        recipe.setName(request.getName());
        recipe.setIngredients(ingredients);
        recipe.setPrice(request.getPrice());
        recipe.setMethodOfPreparation(request.getMethodOfPreparation());
        return recipeRepository.save(recipe).toRecipeDTO();
    }

    public List<RecipeDTO> deleteRecipeById(Long id) throws InvalidIdException {
        if(!recipeRepository.existsById(id)){
            throw new InvalidIdException("Recipe with ID: " + id + " does not exist!");
        }
        recipeRepository.deleteById(id);
        return recipeRepository.findAll().stream().map(Recipe::toRecipeDTO).toList();
    }

    private boolean isValidPrice(BigDecimal price){
        return price.compareTo(BigDecimal.ZERO) > -1;
    }

    private boolean checkName(String name){
        return name.length() > 2 && name.length() < 49;
    }

    private boolean checkText(String text){
        return text.length() > 2 && text.length() < 2049;
    }
}
