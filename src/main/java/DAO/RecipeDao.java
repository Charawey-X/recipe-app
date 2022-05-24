package DAO;

import java.util.List;
import model.Recipe;

public interface RecipeDao {
    // CREATE
    void add(Recipe recipe);

    // READ
    Recipe findById(int id);

    //LIST
    List<Recipe> getAll();

    //UPDATE
    void update(int id, Recipe recipe);

    //DELETE
    void deleteById(int id);
    void clearAllRecipes();
}
