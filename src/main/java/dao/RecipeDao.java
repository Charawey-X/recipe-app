package dao;

import java.util.List;

public interface RecipeDao {
    // CREATE
    void add(Recipe recipe);

    // READ
    Recipe findById(int id);

    //LIST
    List<Recipe> getAll();

    //UPDATE
    void update(int id, String title, int preptime, int cooktime, int servings, String ingredients, String directions, String postedby);

    //DELETE
    void deleteById(int id);

    void clearAllTasks();
}
