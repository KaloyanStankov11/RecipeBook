package com.example.recipebook.receptions;

import com.example.recipebook.exceptions.InvalidIdException;
import com.example.recipebook.exceptions.InvalidNameException;
import com.example.recipebook.exceptions.InvalidPriceException;
import com.example.recipebook.exceptions.TextTooLongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("api/v1/recipe")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @GetMapping("get-all-by-price/{price}")
    public ResponseEntity<Object> getAllByPrice(@PathVariable BigDecimal price){
        try{
            return ResponseEntity.ok(recipeService.getAllReceptionsByPrice(price));
        } catch (InvalidPriceException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @PatchMapping("update-recipe")
    public ResponseEntity<Object> updateRecipePrice(@RequestBody UpdatePriceRequest request){
        try{
            return ResponseEntity.ok(recipeService.updateRecipePrice(request));
        } catch (InvalidIdException | InvalidPriceException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @PostMapping("/add-recipe")
    public ResponseEntity<Object> addRecipe(@RequestBody NewRecipeRequest request){
        try{
            return ResponseEntity.ok(recipeService.addNewRecipe(request));
        } catch (InvalidPriceException | InvalidNameException | TextTooLongException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{recipeId}")
    public ResponseEntity<Object> deleteRecipe(@PathVariable Long recipeId){
        try{
            return ResponseEntity.ok(recipeService.deleteRecipeById(recipeId));
        } catch (InvalidIdException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(ex.getMessage());
        }
    }

}
