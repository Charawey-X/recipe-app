package DAO;

import model.Recipe;
import org.junit.Rule;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Sql2oRecipeDaoTest {

    private Sql2oRecipeDao sql2oRecipeDao;

    @Rule
    public DatabaseRule database = new DatabaseRule();

    @Test
    public void addingRecipeSetsId() throws Exception {
        Recipe recipe = setUpRecipe();
        int originalRecipeId = recipe.getId();
        sql2oRecipeDao.add(recipe);
        assertNotEquals(originalRecipeId, recipe.getId());
    }

    @Test
    public void existingRecipesCanBeFoundById() throws Exception {
        Recipe recipe = setUpRecipe();
        sql2oRecipeDao.add(recipe);
        Recipe foundRecipe = sql2oRecipeDao.findById(recipe.getId());
        assertEquals(recipe, foundRecipe);
    }
    @Test
    public void addedRecipesAreReturnedFromGetAll() throws Exception {
        Recipe recipe = setUpRecipe();
        sql2oRecipeDao.add(recipe);
        assertTrue(sql2oRecipeDao.getAll().size()>0);
    }
    @Test
    public void updateCorrectlyUpdatesAllFields() throws Exception {
        Recipe recipe = setUpRecipe();
        sql2oRecipeDao.update(recipe.getId(),setUpRecipe());
        Recipe foundRecipe = sql2oRecipeDao.findById(recipe.getId());
        assertEquals("Biryani", foundRecipe.getTitle());
        assertEquals(20, foundRecipe.getPrepTime());
        assertEquals(30, foundRecipe.getCookTime());
        assertEquals(10, foundRecipe.getServings());
        assertEquals("Rice", foundRecipe.getIngredients());
        assertEquals("Stir", foundRecipe.getDirections());
        assertEquals("Blue", foundRecipe.getPostedBy());
    }


    @Test
    public void deleteByIdDeletesRecipe() throws Exception {
        Recipe recipe = setUpRecipe();
        sql2oRecipeDao.add(recipe);
        sql2oRecipeDao.deleteById(recipe.getId());
        assertEquals(0, sql2oRecipeDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Recipe recipe = setUpRecipe();
        Recipe recipe2 = setUpRecipe();
        sql2oRecipeDao.add(recipe);
        sql2oRecipeDao.add(recipe2);
        sql2oRecipeDao.clearAllRecipes();
        assertEquals(0, sql2oRecipeDao.getAll().size());
    }


    public Recipe setUpRecipe (){
        return new Recipe ("Biryani", 20, 30, 10,"Rice","Stir","Blue");
    }

}