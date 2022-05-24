package DAO;

import model.Recipe;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

class Sql2oRecipeDaoTest {

    private Sql2oRecipeDaoTest recipeDao;

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void addingRecipeSetsId() throws Exception {
        Recipe recipe = setUpRecipe();
        int originalRecipeId = recipe.getId();
        recipeDao.add(recipe);
        assertNotEquals(originalRecipeId, recipe.getId());
    }

    @Test
    public void existingRecipesCanBeFoundById() throws Exception {
        Recipe recipe = setUpRecipe();
        recipeDao.add(recipe);
        Recipe foundRecipe = recipeDao.findById(recipe.getId());
        assertEquals(recipe, foundRecipe);
    }
    @Test
    public void addedRecipesAreReturnedFromgetAll() throws Exception {
        Recipe recipe = setUpRecipe();
        recipeDao.add(recipe);
        assertTrue(recipeDao.getAll().size()>0);
    }
    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Recipe recipe = setUpRecipe();
        recipeDao.update(recipe.getId(), "Pilau", 15, 30, 5, "Meat", "Boil meat", "Ellah");
        Recipe foundRecipe = recipeDao.findById(recipe.getId());
        assertEquals("Pilau", foundRecipe.getTitle());
        assertEquals(15, foundRecipe.getPrepTime());
        assertEquals(30, foundRecipe.getCookTime());
        assertEquals(5, foundRecipe.getServings());
        assertEquals("Meat", foundRecipe.getIngredients());
        assertEquals("Boil meat", foundRecipe.getDirections());
        assertEquals("Ellah", foundRecipe.getPostedBy());
    }


    @Test
    public void deleteByIdDeletesRecipe() throws Exception {
        Recipe recipe = setUpRecipe();
        recipeDao.add(recipe);
        recipeDao.deleteById(recipe.getId());
        assertEquals(0, recipeDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Recipe recipe = setUpRecipe();
        Recipe recipe2 = setUpRecipe();
        recipeDao.add(recipe);
        recipeDao.add(recipe2);
        recipeDao.clearAllRecipes();
        assertEquals(0, recipeDao.getAll().size());
    }


    public Recipe setUpRecipe (){
        return new Recipe ("Biryani", 20, 30, 10,"Rice","Stir","Blue");
    }

}