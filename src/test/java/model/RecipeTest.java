package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {
    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
    @Test
    public void instantiatesRecipeObjectsCorrectly_true() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals(true, testRecipe instanceof Recipe);
    }

    @Test
    public void getTitleReturnsCorrectTitle() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals("Biryani", testRecipe.getTitle());
    }
    @Test
    public void getPrepTimeReturnsCorrectTime() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals(20, testRecipe.getPrepTime());
    }
    @Test
    public void getCookTimeReturnsCorrectTime() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals(30, testRecipe.getCookTime());
    }
    @Test
    public void getServingsReturnsCorrectServings() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals(10, testRecipe.getServings());
    }

    @Test
    public void getIngredientsReturnsCorrectIngredients() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals("Rice", testRecipe.getIngredients());
    }
    @Test
    public void getDirectionsReturnsCorrectDirections() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals("Stir", testRecipe.getDirections());
    }
    @Test
    public void getPostedByReturnsCorrectPostedBy() throws Exception {
        Recipe testRecipe = setUpRecipe();
        assertEquals("Blue", testRecipe.getPostedBy());
    }


    //HELPER
    public Recipe setUpRecipe (){
        return new Recipe ("Biryani", 20, 30, 10,"Rice","Stir","Blue");
    }

}